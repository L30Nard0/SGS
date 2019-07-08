package pk1;

import java.util.Random;

import java.util.stream.IntStream;



public class God {
	
	public static void Creation(int n) {
		for (int i=0; i<n; i++ ) {
			Cell x = God.newCell();
			x.start();
			Board.board.insert(x);
		}
		System.out.print(Game.clearConsole(4)+ Game.Info());
	}
	
	
	public static int[] personality() {
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		return personality;
	}
	

	// This are the static methods used to generate new Cells
	public static Cell newCell() {
		switch(new Random().nextInt(4) + 1) {
		
			case 1:
				Board.Conqueror++;
				return new Conqueror();
		
			case 2:
				Board.Social++;
				return new Social();
		
			case 3:
				Board.Diplomat++;
				return new Diplomat();
			
			case 4:
				Board.Hermit++;
				return new Hermit();
		}
		return null;
	}
	
	
	public static Cell reproductionOf(Cell cell) {
		switch(cell.Personality.getValue1()) {
		
			case 1:
				Board.Conqueror++;
				return new Conqueror();
	
			case 2:
				Board.Social++;
				return new Social();
	
			case 3:
				Board.Diplomat++;
				return new Diplomat();
		
			case 4:
				Board.Hermit++;
				return new Hermit();
		}
		return null;
	}

}
