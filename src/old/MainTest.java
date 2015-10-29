package old;
public class MainTest {

	public static void main(String[] args) {
		Chaine c = new Chaine("ExempleInstance.txt");
		System.out.println("=== Initial");
		System.out.println(c.getDateFin());
		
		System.out.println("=== Johnson");
		c.solve(new JohnsonSolver());
		System.out.println(c.getDateFin());
		
		int N = 100;
		System.out.println("=== MIN BY RANDOM "+ N +" times");
		int scoreMin = Integer.MAX_VALUE;
		while (N-->0) {
			c.solve(new RandomSolver());
			int score = c.getDateFin();
			scoreMin = Math.min(score, scoreMin);
		}
		System.out.println(scoreMin);
	}
	
}
