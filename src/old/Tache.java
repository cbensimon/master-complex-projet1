package old;
import java.util.LinkedList;

public class Tache {
	
	private LinkedList<Integer> durees;

	public Tache(LinkedList<Integer> durees) {
		this.durees = durees;
	}
	
	public boolean doOne() {
		int duree = durees.getFirst();
		duree--;
		durees.set(0, duree);
		if (duree == 0) {
			durees.removeFirst();
			return true;
		}
		return false;
	}
	
	public int getDuree(int i) {
		return this.durees.get(i);
	}
	
	public String toString() {
		String result = "";
		for (Integer i : durees)
			result += i + " ";
		return result;
	}
	
	@Override
	public Tache clone() {
		return new Tache(new LinkedList<Integer>(this.durees));
	}
}
