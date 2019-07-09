package pk1;

import java.util.Random;

public class Social extends Cell {
	
	int age = 0;
	
	public Social() {
		
		Personality = new Tupla<Integer, int[]> (2, God.personality());
		range = 50;
	}


	@Override
	public void behavior() {
		age++;
		if (connections.size() < 5  && age > 3) {
			range += 30;
			int[] arr = {0,0,1,1,0};
			this.Personality.setValue2(arr);
		}
	}

	@Override
	public void status() {
		if (connections.size() < 10 && age > 3) wellness--;
		else wellness += 20;
	}


	@Override
	public int RandFactor() {
		return new Random().nextInt(this.Personality.getValue1() + 1);
	}


	@Override
	public String getType() {
		return this.getClass().toString().substring(10);
	}

}
