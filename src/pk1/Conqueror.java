package pk1;


public class Conqueror  extends Cell {
	
	public Conqueror() {

		Personality = new Tupla<Integer, int[]> (1, God.personality());
		range = 30;
	}

	@Override
	public void behavior() {
		if (age > 5) this.interrupt();
		
	}

	@Override
	public void status() {
		wellness-=100;
		
	}
}
