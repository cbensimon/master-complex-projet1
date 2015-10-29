package root;
import heuristic.Heuristic;
import java.util.LinkedList;
import java.util.Random;

import dataTypes.Tree;


public class Algos {
	
	public static int[] random(int[][] values) {
		int nTaches = values[0].length;
		int[] result = new int[nTaches];
		boolean[] X = new boolean[nTaches];
		Random rand = new Random();
		for (int i=0 ; i<nTaches; i++) {
			int r;
			do {
				r = rand.nextInt(nTaches);
			} while (X[r]);
			X[r] = true;
			result[i] = r;
		}
		return result;
	}
	
	public static int[] johnson(int[][] values) {
		int nTaches = values[0].length;
		boolean[] X = new boolean[nTaches];
		LinkedList<Integer>
			G = new LinkedList<Integer>(),
			D = new LinkedList<Integer>();
		
		while(!isEmpty(X)) {
			int iStar=0, jStar=0;
			int minValue = Integer.MAX_VALUE;
			for (int i=0 ; i<nTaches ; i++) {
				if (!X[i]) {
					for (int j=0 ; j<2 ; j++) {
						int duree = values[j][i];
						if (duree < minValue) {
							minValue = duree;
							iStar = i;
							jStar = j;
						}
					}
				}
			}
			if (jStar == 0)
				G.add(iStar);
			else
				D.addFirst(iStar);
			X[iStar] = true;
		}
		
		G.addAll(D);
		
		int[] result = new int[nTaches];
		for (int i=0 ; i<nTaches ; i++)
			result[i] = G.get(i);
		return result;
	}
	
	public static int[] exactTree(Instance problem, Heuristic heuristic) {
		
		int[][] values = problem.getValues();
		
		Tree.values = values;
		Tree.N = values[0].length;
		Tree.M = values.length;
		Tree.nbIterations = 0;
		Tree.scoreMin = Integer.MAX_VALUE;
		
		Tree t = new Tree();
		Tree bestLeaf = buildTree2(t, values, heuristic);
		
		problem.setScores(bestLeaf.getScores());
		
		System.out.println("Found in "+ Tree.nbIterations +" iterations");
		return bestLeaf.getPermutation();
		
	}
	
//	private static Tree buildTree(Tree parent, int[][] values, Heuristic heuristic) {
//		
//		Tree.nbIterations++;
//		Tree result = null;
//		int[] childrenPermutation = parent.getRemainingPositions();
//		boolean leaf = parent.areChildrenLeafs();
//		for (int p : childrenPermutation) {
//
//			int[] permutation = parent.getNextPermutation(p);
//			
//			if (leaf) {
//				int score = Main.getScore(Main.applyPermutation(permutation, values));
//				if (score < Tree.scoreMin) {
//					Tree.scoreMin = score;
//					result = new Tree(permutation, parent);
//				}
//			} else {
//				int minorant = heuristic.getMinorant(permutation, values);
//				if (minorant < Tree.scoreMin) {
//					Tree child = new Tree(permutation, parent);
//					Tree currentBest = buildTree(child, values, heuristic);
//					if (	result == null ||
//							(currentBest != null && currentBest.getScore() < result.getScore()))
//						result = currentBest;
//				}
//			}
//			
//		}
//		
//		return result;
//	}
	
	private static Tree buildTree2(Tree node, int[][] values, Heuristic heuristic) {

		Tree.nbIterations++;
		
		int score = node.getScore();
		
		if (node.isLeaf()) {
			Tree.scoreMin = Math.min(Tree.scoreMin, score);
			return node;
		}
		
		int minorant = heuristic.getMinorant(node.getPermutation(), values, node.getScores());
		if (minorant >= Tree.scoreMin)
			return null;
		
		int[] childrenPermutation = node.getRemainingPositions();
		Tree result = null;
		for (int p : childrenPermutation) {
			int[] permutation = node.getNextPermutation(p);
			Tree child = new Tree(permutation, node);
			Tree childResult = buildTree2(child, values, heuristic);
			if (result == null || (childResult != null && childResult.getScore() < result.getScore()))
				result = childResult;
		}
		
		return result;
		
	}
	
	private static boolean isEmpty(boolean[] arr) {
		for (boolean b : arr)
			if (!b)
				return false;
		return true;
	}

}
