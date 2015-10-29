package old;
import java.util.LinkedList;

public class Machine {
	
	private LinkedList<Tache> taches;
	public Machine next = null;
	public int n;
	
	public Machine(LinkedList<Tache> taches, int n) {
		this.taches = taches;
		this.n = n;
		this.next = new Machine(n-1);
	}
	
	public Machine(int n) {
		this.n = n;
		taches = new LinkedList<Tache>();
		if (n > 1)
			this.next = new Machine(n-1);
		else
			this.next = null;
	}

	public void ajouterTache(Tache t) {
		taches.add(t);
	}
	
	public Tache work() {
		if (!taches.isEmpty()) {
			Tache current = taches.getFirst();
			if (current.doOne()) {
				taches.removeFirst();
				return current;
			}
		}
		return null;
	}
	
	public boolean hasTache() {
		return !this.taches.isEmpty();
	}
	
	public String toString() {
		String result = this.n + ": ";
		for (Tache t : taches) {
			result +=  "[" + t.toString() + "]";
		}
		return result;
	}
}
