package pk1;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	// This class is a model of game board were the SGS's players can born die and make connection whit each others
	
	public static final int life = 10;
	public static int MaxWLevel = 100;   //  level of wellness that a cell must reach to be ready to reproduction
	public static int StartNumber;
	public static int graveyard = 0;
	public static int births = 0;
	//private static Cell[][] arena;
	public static CellSet<Cell> board = new CellSet<Cell>();
	
	// This are the list that contains the coordinates of the cells still alive
	
	public static ArrayList<Tupla<Float, Float>> Axis = new ArrayList<Tupla<Float,Float>> (); 
	
	// This are the static methods used to generate new Cells
	
	public static Cell newCell(String FamilyName) {
		switch(new Random().nextInt(4) + 1) {
		
			case 1: return new Diplomat(FamilyName);
				
			case 2: return new Conqueror(FamilyName);
		
			case 3: return new Hermit(FamilyName);
			
			case 4: return new Social(FamilyName);
		}
		return null;
	}
	
	public static Cell reproductionOf(Cell cell) {
		switch(cell.Personality.getValue1()) {
		
			case 1: return new Diplomat(cell.name);
					
			case 2: return new Conqueror(cell.name);
			
			case 3: return new Hermit(cell.name);
				
			case 4: return new Social(cell.name);
		}
		return null;
	}

}
