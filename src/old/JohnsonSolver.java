package old;
import java.util.LinkedList;
import java.util.function.Function;


public class JohnsonSolver implements Function<LinkedList<Tache>, LinkedList<Integer>>{

	@Override
	public LinkedList<Integer> apply(LinkedList<Tache> taches) {
		boolean[] X = new boolean[taches.size()];
		LinkedList<Integer>
			G = new LinkedList<Integer>(),
			D = new LinkedList<Integer>();
		
		while(!isEmpty(X)) {
			int iStar=0, jStar=0;
			int minValue = Integer.MAX_VALUE;
			for (int i=0 ; i<taches.size() ; i++) {
				if (!X[i]) {
					for (int j=0 ; j<2 ; j++) {
						int duree = taches.get(i).getDuree(j);
						if (duree < minValue) {
							minValue = duree;
							iStar = i;
							jStar = j;
						}
					}
				}
			}
			if (jStar == 0)
				G.add(iStar);
			else
				D.addFirst(iStar);
			X[iStar] = true;
		}
		
		G.addAll(D);
		return G;
	}
	
	public static boolean isEmpty(boolean[] arr) {
		for (boolean b : arr)
			if (!b)
				return false;
		return true;
	}

}