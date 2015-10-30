package root;

import heuristic.*;

public class MainCPUTime {

	public static void main(String[] args) {
		
		int nMachines = 3;
		int nTaches = 5;
		Instance problem = new Instance("ExempleInstance.txt");
		
		System.out.println((problem.treeSolveCPUTime(new B1B2Heuristic()) / 1000000.0) +" ms");

	}

}
