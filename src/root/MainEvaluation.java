package root;

import java.util.HashMap;
import java.util.LinkedList;

import heuristic.*;

public class MainEvaluation {

	public static void main(String[] args) {
		
		int dMin = 2, dMax = 1000;
		int nMachines = 3;
		int nbInstances = 10;
		int maxDuration = 1; //in seconds
		
		int[][][][] uncorrelatedSet = new int[nbInstances][dMax][][];
		int[][][][] correlatedOnTasksSet = new int[nbInstances][dMax][][];
		int[][][][] correlatedOnMachinesSet = new int[nbInstances][dMax][][];
		
		for (int i=0 ; i<nbInstances ; i++) {
			for (int d=dMin ; d<dMax ; d++) {
				uncorrelatedSet[i][d] = tools.Generators.uncorrelated(nMachines, d, 0, 100);
				correlatedOnTasksSet[i][d] = tools.Generators.correlatedOnTasks(nMachines, d);
				correlatedOnMachinesSet[i][d] = tools.Generators.correlatedOnMachines(nMachines, d);
			}
		}
		
		LinkedList<Heuristic> heuristics = new LinkedList<Heuristic>();
		HashMap<Heuristic, Float> uncorrelatedResults = new HashMap<Heuristic, Float>();
		HashMap<Heuristic, Float> correlatedOnTasksResults = new HashMap<Heuristic, Float>();
		HashMap<Heuristic, Float> correlatedOnMachinesResults = new HashMap<Heuristic, Float>();
		
		heuristics.add(new B1B2Heuristic());
		heuristics.add(new ABCMaxBetterHeuristic());
		heuristics.add(new ABCMaxHeuristic());
		heuristics.add(new NaifHeuristic());
		
		for (Heuristic h : heuristics) {
			
			System.out.println("=== HEURISTIC "+ h.getClass().getName() +" ===");
			
			float[] uncorrelatedResultsH = new float[nbInstances];
			float[] correlatedOnTasksResultsH = new float[nbInstances];
			float[] correlatedOnMachinesResultsH = new float[nbInstances];
			
			for (int i=0 ; i<nbInstances ; i++) {
				
				System.out.println("=== INSTANCE "+ i +" ===");

				uncorrelatedResultsH[i] = dMin - 1;
				correlatedOnTasksResultsH[i] = dMin - 1;
				correlatedOnMachinesResultsH[i] = dMin - 1;

				for (int d=dMin ; d<dMax ; d++) {
					
					Instance uncorrelatedProblem = new Instance(uncorrelatedSet[i][d]);
					Instance correlatedOnTasksProblem = new Instance(correlatedOnTasksSet[i][d]);
					Instance correlatedOnMachinesProblem = new Instance(correlatedOnMachinesSet[i][d]);
					
					if (uncorrelatedResultsH[i] == d-1) {
						System.out.print("uncorrelated    \t"+ d +" ... ");
						if (uncorrelatedProblem.treeSolveMaxTime(h, maxDuration)) {
							System.out.println("OK");
							uncorrelatedResultsH[i] = d;
						} else {
							System.out.println("MaxTime");
						}
					}

					if (correlatedOnTasksResultsH[i] == d-1) {
						System.out.print("correlatedOnTasks\t"+ d +" ... ");
						if (correlatedOnTasksProblem.treeSolveMaxTime(h, maxDuration)) {
							System.out.println("OK");
							correlatedOnTasksResultsH[i] = d;
						} else {
							System.out.println("MaxTime");
						}
					}

					if (correlatedOnMachinesResultsH[i] == d-1) {
						System.out.print("correlatedOnMachines\t"+ d +" ... ");
						if (correlatedOnMachinesProblem.treeSolveMaxTime(h, maxDuration)) {
							System.out.println("OK");
							correlatedOnMachinesResultsH[i] = d;
						} else {
							System.out.println("MaxTime");
						}
					}

				}
			}
			
			uncorrelatedResults.put(h, mean(uncorrelatedResultsH));
			correlatedOnTasksResults.put(h, mean(correlatedOnTasksResultsH));
			correlatedOnMachinesResults.put(h, mean(correlatedOnMachinesResultsH));
			
		}
		
		System.out.println("=== RESULTS ===");
		
		for (Heuristic h : heuristics) {
			String score = "(";
			score += uncorrelatedResults.get(h) +", ";
			score += correlatedOnTasksResults.get(h) +", ";
			score += correlatedOnMachinesResults.get(h) +")";
			System.out.println(h.getClass().getName() +" : "+ score);
		}

	}

	private static float mean(float[] values) {
		float result = 0;
		for (float value : values)
			result += value;
		return result / values.length;
	}

}
