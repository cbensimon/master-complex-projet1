//package root;
//
//import heuristic.*;
//
//public class MainProblemSearch {
//	
//	public static int recursiveScore;
//
//	public static void main(String[] args) {
//		
//		boolean problem = false;
//		
//		while (!problem) {
//			
//			int n = 3;
//			int m = 2;
//			int[][] values = tools.Generators.correlatedOnMachines(n, m);
//			int[][] orderedValues = tools.Permutation.applyPermutation(root.Algos.exactTree(values, new ABCMaxHeuristic()), values);
//			int score = tools.Values.getScore(orderedValues);
//			
//			if (score != recursiveScore) {
//				
//				System.out.println(score + " : " + recursiveScore + "\n");
//				
//				problem = true;
//				tools.Values.printValues(values);
//				System.out.println("\n");
//				tools.Values.printValues(orderedValues);
//			}
//			
//		}
//
//	}
//
//}
