package pk1;
import java.util.ArrayList;

public class Board {
	
	public static final int age = 10; 							// maximum age of a cell 
	public static int graveyard = 0;
	public static int births = 0;
	public static CellSet<Cell> board = new CellSet<Cell>();
	
	public static ArrayList<Integer> Xaxis = new ArrayList<Integer> (); 
	public static ArrayList<Integer> Yaxis = new ArrayList<Integer> (); 
}
