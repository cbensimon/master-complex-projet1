package root;

import heuristic.Heuristic;

public class Instance {
	
	private int nMachines;
	private int nTaches;
	private int[][] values;
	private int[][] orderedValues;
	private int[] scores;
	
	public Instance(String fileName) {
		this.values = tools.Values.readValues(fileName);
		this.nMachines = values.length;
		this.nTaches = values[0].length;
	}
	
	public Instance(int[][] values) {
		this.values = values;
		this.nMachines = values.length;
		this.nTaches = values[0].length;
	}
	
	public int treeSolve(Heuristic h) {
		int[] p = root.Algos.exactTree(this, h);
		this.orderedValues = tools.Permutation.applyPermutation(p, values);
		
		return getScore();
	}
	
	public int[][] getValues() {
		return this.values;
	}
	
	public int[][] getOrderedValues() {
		return this.orderedValues;
	}
	
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	
	public int getScore() {
		return this.scores[nMachines - 1];
	}

}
