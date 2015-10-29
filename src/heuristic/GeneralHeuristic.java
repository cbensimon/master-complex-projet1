package heuristic;

public class GeneralHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		
		int result = Integer.MIN_VALUE;

		int[] remainingPositions = tools.Permutation.getRemainingPositions(permutation, values[0].length);
		
		int nMachines = currentScores.length;
		for (int m=0 ; m<nMachines ; m++) {
			
			int tPerm = currentScores[m];
			int tRemain = 0;
			int tMin = Integer.MAX_VALUE;
			
			for (int p : remainingPositions) {
				tRemain += values[m][p];
				int sum = 0;
				for (int k=(m+1) ; k<nMachines ; k++)
					sum += values[k][p];
				tMin = Math.min(tMin, sum);
			}

			int total = tPerm + tRemain + tMin;
			result = Math.max(result, total);
			
		}
		
		return result;
	}

}
