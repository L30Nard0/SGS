package pk1;
import java.util.ArrayList;

public class Board {
	
	// This class is a model of game board were the SGS's players can born die and make connection whit each others
	
	public static final int age = 10; 							
	public static int graveyard = 0;
	public static int births = 0;
	public static CellSet<Cell> board = new CellSet<Cell>();
	
	public static ArrayList<Integer> Xaxis = new ArrayList<Integer> (); 
	public static ArrayList<Integer> Yaxis = new ArrayList<Integer> (); 
}
