package old;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Function;

public class Chaine {
	
	private final LinkedList<Tache> taches;
	private LinkedList<Tache> tachesOrdered;
	private Machine machines;
	public int n;
	
	public Chaine(LinkedList<Tache> taches, int n) {
		this.taches = taches;
		this.n = n;
		this.construct();
	}
	
	public Chaine(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner((new FileReader(fileName)));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int nTaches = sc.nextInt();
		int nMachines;
		LinkedList<LinkedList<Integer>> machinesRead = new LinkedList<LinkedList<Integer>>();
		LinkedList<Tache> taches = new LinkedList<Tache>();
		while (sc.hasNextInt()) {
			LinkedList<Integer> values = new LinkedList<Integer>();
			for (int i=0 ; i<nTaches ; i++)
				values.add(sc.nextInt());
			machinesRead.add(values);
		}
		nMachines = machinesRead.size();
		for (int j=0 ; j<nTaches ; j++) {
			LinkedList<Integer> durations = new LinkedList<Integer>();
			for (int i=0 ; i<nMachines ; i++)
				durations.add(machinesRead.get(i).get(j));
			taches.add(new Tache(durations));
		}
		this.taches = taches;
		this.n = nMachines;
		this.construct();
		
	}
	
	private void construct() {
		if (this.tachesOrdered == null)
			this.tachesOrdered = this.taches;
		LinkedList<Tache> clone = new LinkedList<Tache>();
		for (Tache t : this.tachesOrdered)
			clone.add(t.clone());
		
		this.machines = new Machine(clone, n);
	}
	
	public void solve(Function<LinkedList<Tache>, LinkedList<Integer>> f) {
		LinkedList<Integer> p = f.apply(taches);
		LinkedList<Tache> pTasks = new LinkedList<Tache>();
		
		for (int numTask : p)
			pTasks.add(this.taches.get(numTask));
		
		this.tachesOrdered = pTasks;
	}
	
	public int getDateFin() {
		this.construct();
		int cpt = 0;
		while (!work())
			cpt++;
		return cpt;
	}
	
	public boolean work() {
		boolean over = true;
		for (Machine m = machines ; m != null ; m = m.next) {
			if (m.hasTache()) {
				over = false;
				Tache t = m.work();
				if (t != null) {
					if (m.next != null)
						m.next.ajouterTache(t);
				}
			}
		}
		return over;
	}
	
	public LinkedList<Tache> getTaches() {
		return taches;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Machine m = machines ; m != null ; m = m.next) {
			result += m.toString() + "\n";
		}
		return result;
	}
}
