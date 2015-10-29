package tools;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Values {
	
	public static int[][] readValues(String fileName) {
		
		int[][] result;
		ArrayList<int[]> resultList = new ArrayList<int[]>();
		
		Scanner sc = null;
		try {
			sc = new Scanner((new FileReader(fileName)));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int nTaches = sc.nextInt();
		while (sc.hasNextInt()) {
			int[] machine = new int[nTaches];
			for (int j=0 ; j<nTaches ; j++) {
				machine[j] = sc.nextInt();
			}
			resultList.add(machine);
		}
		
		sc.close();
		
		int nMachines = resultList.size();
		result = new int[nMachines][nTaches];
		for (int i=0 ; i<nMachines ; i++)
			for (int j=0 ; j<nTaches ; j++)
				result[i][j] = resultList.get(i)[j];
		return result;
		
	}

//	NOT ALWAYS WORKING
	public static int getScore(int[][] values) {
		
		values = cloneValues(values);
		int result = 0;
		
		int nM = values.length, nT = values[0].length;
		int[] indexes = new int[nM];
		Arrays.fill(indexes, -1);
		indexes[0] = 0;
		
		boolean over = false;
		while (!over) {
			int minValue = Integer.MAX_VALUE;
			for (int i=0 ; i<nM ; i++) {
				int index = indexes[i];
				if (index > -1)
					if (values[i][index] > 0 && values[i][index] < minValue)
						minValue = values[i][index];
			}
			result += minValue;
			for (int i=0 ; i<nM ; i++) {
				int index = indexes[i];
				if (index > -1) {
					if (values[i][index] > 0)
						values[i][index] -= minValue;
					if (values[i][index] == 0) {
						if (index == nT-1)
							indexes[i] = -2;
						else if (i == 0 || indexes[i-1] > index+1 || indexes[i-1] == -2)
							indexes[i]++;
					}
				} else if (i > 0 && indexes[i-1] > 0) {
					indexes[i] = 0;
				}
			}
			boolean maybeOver = true;
			for (int i=0 ; i<nM ; i++)
				if (indexes[i] != -2)
					maybeOver = false;
			over = maybeOver;
		}
		return result;
	}
	
	public static int[][] cloneValues(int[][] values) {
		int n = values.length, m = values[0].length;
		int[][] result = new int[n][m];
		for (int i=0 ; i<n ; i++)
			for (int j=0 ; j<m ; j++)
				result[i][j] = values[i][j];
		return result;
	}
	
	public static void printValues(int[][] values) {
		for (int[] vs : values) {
			for (int v : vs)
				System.out.print(v +"\t");
			System.out.println();
		}
	}

}
