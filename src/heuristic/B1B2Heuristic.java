package heuristic;

public class B1B2Heuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		Heuristic A = new ABCMaxBetterHeuristic();
		Heuristic B = new B2Heuristic();
		
		int tA = A.getMinorant(permutation, values, currentScores);
		int tB = B.getMinorant(permutation, values, currentScores);
		
		return Math.max(tA, tB);
	}
	

}
