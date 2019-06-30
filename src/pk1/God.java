package pk1;

import java.util.Random;
import java.util.stream.IntStream;

public class God {
	private static final int X = 8;
	private static final int Y = 8;
	
	
	public static void Creation(int n) {
		for (int i=0; i<n; i++ ) {
			Cell x = God.newCell();
			Board.board.insert(x);
			System.out.print(x.name + " \"I'm alive\" " + x.Personality.getValue1() +"\n");			
			x.start();
		}
	}
	
	
	public static Tupla<Float, Float> setRandCoordinates() {
		float xValue = new Random().nextFloat() + new Random().nextInt(X);
		float yValue = new Random().nextFloat() + new Random().nextInt(Y);
		return new Tupla<Float, Float> (xValue, yValue);
		
	}
	
	
	public static int[] personality() {
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		return personality;
	}
	

	// This are the static methods used to generate new Cells
	public static Cell newCell() {
		switch(new Random().nextInt(4) + 1) {
		
			case 1: return new Conqueror();
		
			case 2: return new Social();
		
			case 3: return new Diplomat();
			
			case 4: return new Hermit();
		}
		return null;
	}
	
	
	public static Cell reproductionOf(Cell cell) {
		switch(cell.Personality.getValue1()) {
		
			case 1: return new Conqueror();
					
			case 2: return new Social();
			
			case 3: return new Diplomat();
				
			case 4: return new Hermit();
		}
		return null;
	}

}
