package pk1;
import java.util.ArrayList;

public class Board {
	
	// This class is a model of game board were the SGS's players can born die and make connection whit each others
	
	public static final int age = 10;
	public static int MaxWLevel = 200;   //  level of wellness that a cell must reach to be ready to reproduction
	public static int graveyard = 0;
	public static int births = 0;
	public static CellSet<Cell> board = new CellSet<Cell>();
	
	// This are the list that contains the coordinates of the cells still alive
	
	public static ArrayList<Float> Xaxis = new ArrayList<Float> (); 
	public static ArrayList<Float> Yaxis = new ArrayList<Float> (); 
}
