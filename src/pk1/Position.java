package pk1;


import java.util.Random;

public class Position {
	
	private Tupla<Float, Float> position;  // cell of tow numbers
	
	
	@SuppressWarnings("unlikely-arg-type")
	public Position() {
		
		int signX = -1;
		if (new Random().nextInt(2) == 1) signX = 1;
		int signY = -1;
		if (new Random().nextInt(2) == 1) signY = 1;
		
		float xValue = new Random().nextFloat() + new Random().nextInt(20);
		while (Board.Xaxis.contains(xValue)) xValue = new Random().nextFloat() + new Random().nextInt(20);
		float yValue = new Random().nextFloat() + new Random().nextInt(20);
		while (Board.Xaxis.contains(yValue)) xValue = new Random().nextFloat() + new Random().nextInt(20);
		
		float x = xValue*signX;

		float y = yValue*signY;
		
		position = new Tupla<Float, Float>(x, y);
	}
	
	public Tupla<Float, Float> getPosition(){
		return position;
	}

}
