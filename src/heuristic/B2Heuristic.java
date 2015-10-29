package heuristic;

public class B2Heuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int tAPerm = currentScores[0];
		
		int ASum = 0;
		int CSum = 0;
		
		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		for (int v : remainingPositions) {
			
			if (values[0][v] <= values[2][v])
				ASum += values[0][v];
			else
				CSum += values[2][v];
			
		}
		
		int kMax = Integer.MIN_VALUE;
		for (int k : remainingPositions) {
			
			int dAk = values[0][k];
			int dBk = values[1][k];
			int dCk = values[2][k];
			int dACk = dAk > dCk ? dCk : dAk;
			kMax = Math.max(kMax, dBk + dACk);
			
		}
		
		return tAPerm + kMax + ASum + CSum;
		
	}

}
