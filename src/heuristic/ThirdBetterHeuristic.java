package heuristic;

public class ThirdBetterHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tAPerm = currentScores[0];
		int tBPerm = currentScores[1];
		int tCPerm = currentScores[2];
		
		int tRemain = 0;
		int tBMin = Integer.MAX_VALUE;
		int tABMin = Integer.MAX_VALUE;
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions) {
			tRemain += values[2][v];
			tBMin = Math.min(tBMin, values[1][v]);
			tABMin = Math.min(tABMin, values[0][v] + values[1][v]);
		}

		return Math.max(tCPerm, Math.max(tBPerm + tBMin, tAPerm + tABMin)) + tRemain;
	
	}

}