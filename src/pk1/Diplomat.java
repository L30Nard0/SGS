package pk1;

import java.util.Random;

public class Diplomat extends Cell {

	int age = 0;
	
	public Diplomat() {
		
		Personality = new Tupla<Integer, int[]> (3, God.personality());
		range = 30;
	}

	@Override
	public void behavior() {
		age++;
	}

	@Override
	public void status() throws InterruptedException {
		if (age == Board.life-1)
			this.reproduction();
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
