package heuristic;

public class FirstHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tAPerm = currentScores[0];
		
		int tRemain = 0;
		int tMin = Integer.MAX_VALUE;
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions) {
			tRemain += values[0][v];
			tMin = Math.min(tMin, values[1][v] + values[2][v]);
		}
		
		return tAPerm + tRemain + tMin;
	}

}
