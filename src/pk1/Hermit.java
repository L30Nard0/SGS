package pk1;


public class Hermit extends Cell {
	
	public Hermit() {

		Personality = new Tupla<Integer, int[]> (4, God.personality());
		range = 20;
	}

	@Override
	public void behavior() {
		
	}

	@Override
	public void status() {
		wellness += 20;
		// TODO Auto-generated method stub
		
	}

}
