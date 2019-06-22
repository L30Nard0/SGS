package pk1;

import java.util.Random;
import java.util.stream.IntStream;

public class Conqueror  extends Cell {
	
	public Conqueror(String FamilyName) {
		super(FamilyName);
		
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		
		Personality = new Tupla<Integer, int[]> (2, personality );
	}
}
