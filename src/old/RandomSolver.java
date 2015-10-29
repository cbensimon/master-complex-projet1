package old;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Function;


public class RandomSolver implements Function<LinkedList<Tache>, LinkedList<Integer>>{

	@Override
	public LinkedList<Integer> apply(LinkedList<Tache> taches) {
		int nTaches = taches.size();
		LinkedList<Integer> result = new LinkedList<Integer>();
		boolean[] X = new boolean[nTaches];
		Random rand = new Random();
		for (int i=0 ; i<nTaches; i++) {
			int r;
			do {
				r = rand.nextInt(nTaches);
			} while (X[r]);
			X[r] = true;
			result.add(r);
		}
		return result;
	}

}
