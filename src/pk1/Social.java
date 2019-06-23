package pk1;

import java.util.Random;
import java.util.stream.IntStream;

public class Social extends Cell {
	
	public Social(String FamilyName) {
		super(FamilyName);
		
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		
		Personality = new Tupla<Integer, int[]> (2, personality );
	}

	@Override
	public int joy(int age) {
		int x = 50 - age/10;
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
