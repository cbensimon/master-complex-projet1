package root;
import heuristic.*;


public class Main {
	
	public static void main(String[] args) {
		
		//Lecture ou génération des valeurs
		
//		Instance problem = new Instance("Problem.txt");
		
		int nMachines = 3;
		int nTaches = 10;
		Instance problem = new Instance(tools.Generators.correlatedOnTasks(nMachines, nTaches));
		
		int[][] values = problem.getValues();
		
		int[][] orderedValues;
		
		System.out.println("=== Initial");
		tools.Values.printValues(values);
		System.out.println(tools.Values.getScore(values));
		
//		System.out.println("\n=== Random");
//		orderedValues = tools.Permutation.applyPermutation(Algos.random(values), values);
//		tools.Values.printValues(orderedValues);
//		System.out.println(tools.Values.getScore(orderedValues));
		
		System.out.println("\n=== Johnson");
		orderedValues = tools.Permutation.applyPermutation(Algos.johnson(values), values);
		tools.Values.printValues(orderedValues);
		System.out.println(tools.Values.getScore(orderedValues));
		
//		System.out.println("\n=== Tree Naif");
//		problem.treeSolve(new NaifHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
//		
//		System.out.println("\n=== Tree First");
//		problem.treeSolve(new FirstHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
//		
//		System.out.println("\n=== Tree Second");
//		problem.treeSolve(new SecondHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
//		
//		System.out.println("\n=== Tree Third");
//		problem.treeSolve(new ThirdHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
//		System.out.println("\n=== Tree ABCMax");
//		problem.treeSolve(new ABCMaxHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
//		
//		System.out.println("\n=== Tree General");
//		problem.treeSolve(new GeneralHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
//		System.out.println("\n=== Tree Second Better");
//		problem.treeSolve(new SecondBetterHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
//		System.out.println("\n=== Tree Third Better");
//		problem.treeSolve(new ThirdBetterHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
//		System.out.println("\n=== Tree ABCMaxBetter");
//		problem.treeSolve(new ABCMaxBetterHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
		System.out.println("\n=== Tree B2");
		problem.treeSolve(new B2Heuristic());
		tools.Values.printValues(problem.getOrderedValues());
		System.out.println(problem.getScore());
		
//		System.out.println("\n=== Tree AB2CMaxBetter");
//		problem.treeSolve(new AB2CMaxBetterHeuristic());
//		tools.Values.printValues(problem.getOrderedValues());
//		System.out.println(problem.getScore());
		
		System.out.println("\n=== Tree B3");
		problem.treeSolve(new B3Heuristic());
		tools.Values.printValues(problem.getOrderedValues());
		System.out.println(problem.getScore());
		
	}

}
