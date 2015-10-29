package heuristic;

public class ThirdHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tCPerm = currentScores[2];
		
		int tRemain = 0;
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions)
			tRemain += values[2][v];

		return tCPerm + tRemain;
	
	}

}