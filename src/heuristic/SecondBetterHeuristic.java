package heuristic;

public class SecondBetterHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tAPerm = currentScores[0];
		int tBPerm = currentScores[1];
		
		int tRemain = 0;
		int tMin = Integer.MAX_VALUE;
		int tMinA = Integer.MAX_VALUE;
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions) {
			tRemain += values[1][v];
			tMin = Math.min(tMin, values[2][v]);
			tMinA = Math.min(tMinA, values[0][v]);
		}
		
		return Math.max(tBPerm, tAPerm + tMinA) + tRemain + tMin;
	}

}