package pk1;

import java.util.Random;
import java.util.stream.IntStream;

public class Hermit extends Cell {
	
	public Hermit(String FamilyName) {
		super(FamilyName);
		
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		
		Personality = new Tupla<Integer, int[]> (4, personality );
	}

	@Override
	public int joy(int age) {
		int x = 10 - age/2;
		return x;
	}

	@Override
	public void behavior() {
		
	}

	@Override
	public void status() {
		wellness += 10;
		// TODO Auto-generated method stub
		
	}

}
