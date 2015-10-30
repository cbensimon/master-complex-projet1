package root;

import java.util.HashMap;
import java.util.LinkedList;

import heuristic.*;

public class MainEvaluation {

	public static void main(String[] args) {
		
		int dMin = 2, dMax = 100;
		int nMachines = 3;
		int nbInstances = 50;
		int maxDuration = 30; //in seconds
		
		int[][][][] uncorrelatedSet = new int[nbInstances][dMax][][];
		int[][][][] correlatedOnTasksSet = new int[nbInstances][dMax][][];
		int[][][][] correlatedOnMachinesSet = new int[nbInstances][dMax][][];
		
		for (int i=0 ; i<nbInstances ; i++) {
			for (int d=dMin ; d<dMax ; d++) {
				uncorrelatedSet[i][d] = tools.Generators.uncorrelated(nMachines, i, 0, 100);
				correlatedOnTasksSet[i][d] = tools.Generators.correlatedOnTasks(nMachines, i);
				correlatedOnMachinesSet[i][d] = tools.Generators.correlatedOnMachines(nMachines, i);
			}
		}
		
		LinkedList<Heuristic> heuristics = new LinkedList<Heuristic>();
		HashMap<Heuristic, Float> uncorrelatedResults = new HashMap<Heuristic, Float>();
		HashMap<Heuristic, Float> correlatedOnTasksResults = new HashMap<Heuristic, Float>();
		HashMap<Heuristic, Float> correlatedOnMachinesResults = new HashMap<Heuristic, Float>();
		
		heuristics.add(new NaifHeuristic());
		heuristics.add(new ABCMaxHeuristic());
		heuristics.add(new ABCMaxBetterHeuristic());
		heuristics.add(new B1B2Heuristic());
		
		for (Heuristic h : heuristics) {
			
			float[] uncorrelatedResultsH = new float[nbInstances];
			float[] correlatedOnTasksResultsH = new float[nbInstances];
			float[] correlatedOnMachinesResultsH = new float[nbInstances];
			
			for (int i=0 ; i<nbInstances ; i++) {

				uncorrelatedResultsH[i] = dMin - 1;
				correlatedOnTasksResultsH[i] = dMin - 1;
				correlatedOnMachinesResultsH[i] = dMin - 1;

				for (int d=dMin ; d<dMax ; d++) {
					
					Instance uncorrelatedProblem = new Instance(uncorrelatedSet[i][d]);
					Instance correlatedOnTasksProblem = new Instance(correlatedOnTasksSet[i][d]);
					Instance correlatedOnMachinesProblem = new Instance(correlatedOnMachinesSet[i][d]);
					
					if (uncorrelatedResultsH[i] == d-1)
						if (uncorrelatedProblem.treeSolveMaxTime(h, maxDuration))
							uncorrelatedResultsH[i] = d;

					if (correlatedOnTasksResultsH[i] == d-1)
						if (correlatedOnTasksProblem.treeSolveMaxTime(h, maxDuration))
							correlatedOnTasksResultsH[i] = d;

					if (correlatedOnMachinesResultsH[i] == d-1)
						if (correlatedOnMachinesProblem.treeSolveMaxTime(h, maxDuration))
							correlatedOnMachinesResultsH[i] = d;
					
				}
			}
			
			System.out.println("Heuristic " + h.getClass().getName());
			
			uncorrelatedResults.put(h, mean(uncorrelatedResultsH));
			correlatedOnTasksResults.put(h, mean(correlatedOnTasksResultsH));
			correlatedOnMachinesResults.put(h, mean(correlatedOnMachinesResultsH));
			
		}

	}

	private static float mean(float[] values) {
		float result = 0;
		for (float value : values)
			result += value;
		return result / values.length;
	}

}
