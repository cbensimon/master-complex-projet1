package dataTypes;

import java.util.Arrays;

public class Tree {
	
	public static int nbIterations;
	public static int[][] values;
	public static int N;
	public static int M;
	public static int scoreMin;
	private int[] permutation;
	private int score;
	private int[] scores;
	private Tree parent;
	
	public Tree() {
		this.permutation = new int[0];
		this.score = -1;
		this.score = this.computeScores();
	}
	
	public Tree(int[] permutation, Tree parent) {
		this.permutation = permutation;
		this.parent = parent;
		this.score = this.computeScores();
	}

	public int getLevel() {
		return permutation.length;
	}
	
	public int[] getRemainingPositions() {
		return tools.Permutation.getRemainingPositions(permutation, N);
	}
	
	public int getScore() {
		return score;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public int computeScore(int[][] values) {
		return this.score = tools.Values.getScore(tools.Permutation.applyPermutation(permutation, values));
	}
	
	public int computeScores() {
		
		if (parent == null) {
			int[] result = new int[M];
			Arrays.fill(result, 0);
			this.scores = result;
			return 0;
		}
		
		int[] oldScores = this.parent.getScores();
		int[] newScores = new int[M];
		
		int lastTask = permutation[permutation.length - 1];
		
		newScores[0] = oldScores[0] + values[0][lastTask];
		for (int i=1 ; i<M ; i++) {
			int diff = newScores[i-1] - oldScores[i];
			newScores[i] = oldScores[i] + values[i][lastTask];
			newScores[i] += diff > 0 ? diff : 0;
		}
		
		this.scores = newScores;
		
		return newScores[M - 1];
	}
	
	public int[] getPermutation() {
		return this.permutation;
	}
	
	public int[] getNextPermutation(int np){
		int level = getLevel();
		int[] result = new int[level + 1];
		for (int i=0 ; i<level ; i++)
			result[i] = permutation[i];
		result[level] = np;
		return result;
	}
	
	public boolean areChildrenLeafs() {
		return (getLevel()+1) == N;
	}
	
	public boolean isLeaf() {
		return getLevel() == N;
	}
	
	@Override
	public String toString() {
		String result = "[ ";
		for (int v : permutation)
			result += v +" ";
		result += "] -> " + score;
		return result;
	}
	
	public void displayScores() {
		System.out.print("( ");
		for (int v : this.scores)
			System.out.print(v +" ");
		System.out.println(")");
	}
}
