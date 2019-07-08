package pk1;


import java.util.Random;

public class Hermit extends Cell {
	
	public Hermit() {

		Personality = new Tupla<Integer, int[]> (4, God.personality());
		range = 20;
	}
	

	@Override
	public void behavior() throws InterruptedException {
		if (connections.size() > 10) {
			Cell cell = Board.board.get(new Random().nextInt(11));
			connections.remove(cell);
		}
	}

	@Override
	public void status() {
		if (connections.size() > 10) wellness--;
		wellness += 30;
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
