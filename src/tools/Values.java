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
	
	public static int evalScore(int[][] orderedValues){
		
		int result = 0;
		
		int nMachines = orderedValues.length;
		int nTasks = orderedValues[0].length;
		int[] endDates = new int[nMachines];
		Arrays.fill(endDates, 0);
		

		for (int i=0; i<nTasks; i++){
			endDates[0] += orderedValues[0][i];
			for(int m=1; m<nMachines; m++){
				int diff = endDates[m-1] - endDates[m];
				endDates[m] += orderedValues[m][i];
				endDates[m] += diff > 0 ? diff : 0;
			}
			
		}
				
		result = endDates[nMachines-1];
		return result;
		
	}
	
	public static int evalScore2(int[][] values, int[] permutation) {
		
		int result = 0;

		int nMachines = values.length;
		int[] endDates = new int[nMachines];
		Arrays.fill(endDates, 0);
		
		for (int p : permutation)
			endDates = evalNextScore(values, p, endDates);
		
		result = endDates[nMachines-1];
		return result;
		
	}
	
	public static int[] evalNextScore(int values[][], int task, int lastEndDates []){
		
		int[] result = new int[lastEndDates.length];
		
		int nMachines = lastEndDates.length;
		result[0] = lastEndDates[0] + values[0][task];
		for (int i=1; i<nMachines; i++){
			
			int diff = result[i-1] - lastEndDates[i];
			result[i] = lastEndDates[i] + values[i][task];
			result[i] += diff>0 ? diff : 0;
		}
		
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
