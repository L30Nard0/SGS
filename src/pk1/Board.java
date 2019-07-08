package pk1;

import java.util.ArrayList;
//import java.util.Random;

public class Board {
	
	// This class is a model of game board were the SGS's players can born die and make connection whit each others
	
	public static final int life = 8;
	protected static int space = 20;
	public static int MaxWLevel = 100;   //  level of wellness that a cell must reach to be ready to reproduction
	public static int StartNumber;
	public static int graveyard = 0;
	public static int births = 0;
	public static CellSet<Cell> board = new CellSet<Cell>();
	public static ArrayList<Tupla<Integer, Integer>> histo = new ArrayList<Tupla<Integer, Integer>>();
    public static Arena arena = new Arena();
	
	public static int Conqueror;
	public static int Social;
	public static int Diplomat;
	public static int Hermit;
	

}
