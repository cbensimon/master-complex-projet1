package heuristic;

public class ABCMaxBetterHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		Heuristic A = new FirstHeuristic();
		Heuristic B = new SecondBetterHeuristic();
		Heuristic C = new ThirdBetterHeuristic();
		
		int tA = A.getMinorant(permutation, values, currentScores);
		int tB = B.getMinorant(permutation, values, currentScores);
		int tC = C.getMinorant(permutation, values, currentScores);
		
		return Math.max(tA, Math.max(tB, tC));
	}

}
