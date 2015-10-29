package heuristic;

public class ABCMaxHeuristic implements Heuristic {

	@Override
	public int getMinorant(int[] permutation, int[][] values, int[] currentScores) {
		Heuristic A = new FirstHeuristic();
		Heuristic B = new SecondHeuristic();
		Heuristic C = new ThirdHeuristic();
		
		int tA = A.getMinorant(permutation, values, currentScores);
		int tB = B.getMinorant(permutation, values, currentScores);
		int tC = C.getMinorant(permutation, values, currentScores);
		
		return Math.max(tA, Math.max(tB, tC));
	}

}
