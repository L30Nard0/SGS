package pk1;

import java.util.Random;

public class Conqueror  extends Cell {
	
	public Conqueror() {

		Personality = new Tupla<Integer, int[]> (1, God.personality());
		range = 20;
	}

	@Override
	public void behavior() throws InterruptedException {
		if (new Random().nextInt(101)<10) {
			Cell cell = Board.board.get(new Random().nextInt(Board.board.SIZE()));
			cell.Personality.setValue1(1);
		}
	}

	@Override
	public void status() {
		wellness -= 20;
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
