package pk1;


import java.util.Random;

public class Position {
	
	private static int minX = 10;
	private static int maxX = -9;
	private static int minY = 10;
	private static int maxY = -9;
	
	private Tupla<Integer, Integer> position;  // cell of tow numbers
	
	
	public Position() {
		this.increase();
		
		int signX = -1;
		if (new Random().nextInt(2) == 1) signX = 1;
		int signY = -1;
		if (new Random().nextInt(2) == 1) signY = 1;
		
		
		int x = (new Random().nextInt(minX) + maxX) * signX;
		int y = (new Random().nextInt(minY) + maxY) * signY;
		
		position = new Tupla<Integer, Integer>(x, y);
	}
	
	public synchronized void increase() {
		maxX += 3; 
		maxY += 3; 
	}
	
	public Tupla<Integer, Integer> getPosition(){
		return position;
	}

}
