package pk1;


import java.util.Random;

public class Position {
	
	public static int minX = 3;
	public static int maxX = -2;
	public static int minY = 3;
	public static int maxY = -2;
	
	public Tupla<Integer, Integer> position;  // cell of tow numbers
	
	
	public Position() {
		this.increase();
		
		int x = new Random().nextInt(minX) + maxX;
		int y = new Random().nextInt(minY) + maxY;
		
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
