package heuristic;

public class B3Heuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tBPerm = currentScores[0];
		
		int BSum = 0;
		int CSum = 0;
		
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions) {
			
			if (values[1][v] <= values[2][v])
				BSum += values[1][v];
			else
				CSum += values[2][v];
			
		}
		
		int kMax = Integer.MIN_VALUE;
		for (int k : remainingPositions) {
			
			int dAk = values[1][k];
			int dBk = values[0][k];
			int dCk = values[2][k];
			int dACk = dAk > dCk ? dCk : dAk;
			kMax = Math.max(kMax, dBk + dACk);
			
		}
		
		return tBPerm + kMax + BSum + CSum;
		
	}

}
