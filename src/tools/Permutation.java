package tools;

public class Permutation {
	
	public static int[] getRemainingPositions(int[] permutation, int N) {
		int[] result = new int[N - permutation.length];
		int index = 0;
		for (int i=0 ; i<N ; i++)
			if (!isInPermutation(i, permutation))
				result[index++] = i;
		return result;
	}
	
	private static boolean isInPermutation(int i, int[] permutation) {
		for (int p : permutation)
			if (p == i)
				return true;
		return false;
	}
	
	public static int[][] applyPermutation(int[] p, int[][] values) {
		
		int n = values.length, m = p.length;
		int[][] result = new int[n][m];
		
		for (int j=0 ; j<m ; j++)
			for (int i=0 ; i<n ; i++)
				result[i][j] = values[i][p[j]];
		return result;
		
	}

}

