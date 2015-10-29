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
		
		int[][][][] uncorrelatedSet = new int[dMax][nbInstances][][];
		int[][][][] correlatedOnTasksSet = new int[dMax][nbInstances][][];
		int[][][][] correlatedOnMachinesSet = new int[dMax][nbInstances][][];
		
		for (int d=dMin ; d<dMax ; d++) {
			for (int i=0 ; i<nbInstances ; i++) {
				uncorrelatedSet[d][i] = tools.Generators.uncorrelated(nMachines, i, 0, 100);
				correlatedOnTasksSet[d][i] = tools.Generators.correlatedOnTasks(nMachines, i);
				correlatedOnMachinesSet[d][i] = tools.Generators.correlatedOnMachines(nMachines, i);
			}
		}
		
		LinkedList<Heuristic> heuristics = new LinkedList<Heuristic>();
		HashMap<Heuristic, float[]> uncorrelatedResults = new HashMap<Heuristic, float[]>();
		HashMap<Heuristic, float[]> correlatedOnTasksResults = new HashMap<Heuristic, float[]>();
		HashMap<Heuristic, float[]> correlatedOnMachinesResults = new HashMap<Heuristic, float[]>();
		
		heuristics.add(new NaifHeuristic());
		heuristics.add(new ABCMaxHeuristic());
		heuristics.add(new ABCMaxBetterHeuristic());
		heuristics.add(new B1B2Heuristic());
		
		for (Heuristic h : heuristics) {
			
			float[] uncorrelatedResult = new float[dMax];
			float[] correlatedOnTasksResult = new float[dMax];
			float[] correlatedOnMachinesResult = new float[dMax];
			
			for (int d=dMin ; d<dMax ; d++) {

				for (int i=0 ; i<nbInstances ; i++) {
					
					Instance uncorrelatedProblem = new Instance(uncorrelatedSet[d][i]);
					Instance correlatedOnTasksProblem = new Instance(correlatedOnTasksSet[d][i]);
					Instance correlatedOnMachinesProblem = new Instance(correlatedOnMachinesSet[d][i]);
					
					uncorrelatedResult[d] += uncorrelatedProblem.treeSolve(h);
					correlatedOnTasksResult[d] += correlatedOnTasksProblem.treeSolve(h);
					correlatedOnMachinesResult[d] += correlatedOnMachinesProblem.treeSolve(h);
					
				}

				uncorrelatedResult[d] /= (float)nbInstances;
				correlatedOnTasksResult[d] /= (float)nbInstances;
				correlatedOnMachinesResult[d] /= (float)nbInstances;
			}
			
			System.out.println("Heuristic " + h.getClass().getName());
			
			uncorrelatedResults.put(h, uncorrelatedResult);
			correlatedOnTasksResults.put(h, correlatedOnTasksResult);
			correlatedOnMachinesResults.put(h, correlatedOnMachinesResult);
			
		}

	}

}
