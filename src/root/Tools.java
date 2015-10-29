package root;

public class Tools {
	
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

}
