package pk1;

import java.util.Random;
import java.util.stream.IntStream;

public class Conqueror  extends Cell {
	
	public Conqueror(String FamilyName) {
		super(FamilyName);
		
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		
		Personality = new Tupla<Integer, int[]> (1, personality );
	}

	@Override
	public int joy(int age) {
		int x = 30 - age/2;
		return x;
	}

	@Override
	public void behavior() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void status() {
		// TODO Auto-generated method stub
		
	}
}