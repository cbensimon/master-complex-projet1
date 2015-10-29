package tools;

import java.util.Random;

public class Generators {
	
	public static int[][] uncorrelated(int n, int m, int a, int b) {
		int[][] result = new int[n][m];
		Random rand = new Random();
		for (int i=0 ; i<n ; i++)
			for (int j=0 ; j<m ; j++)
				result[i][j] = rand.nextInt(b - a) + a + 1;
		return result;
	}
	
	public static int[][] correlatedOnTasks(int n, int m) {
		int[][] result = new int[n][m];
		Random rand = new Random();
		for (int j=0 ; j<m ; j++) {
			int rj = rand.nextInt(5);
			int a = 20*rj;
			int b = 20*rj + 20;
			for (int i=0 ; i<n ; i++)
				result[i][j] = rand.nextInt(b - a) + a + 1;
		}
		return result;
	}
	
	public static int[][] correlatedOnMachines(int n, int m) {
		int[][] result = new int[n][m];
		Random rand = new Random();
		for (int i=0 ; i<n ; i++) {
			int a = 15*i;
			int b = 15*i + 100;
			for (int j=0 ; j<m ; j++)
				result[i][j] = rand.nextInt(b - a) + a + 1;
		}
		return result;
	}

}
