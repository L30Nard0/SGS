package pk1;

import java.util.Random;

public class Connection {
		
	protected double distance;
	private Cell Me;
	private Cell Others;
	public int TypeOfBond;
	
	public Connection(Cell Me) {
		this.Me = Me;
	}
	
	public Connection(Cell Me, Cell Other, int bond) {
		
		this.Me = Me; 
		this.Others = Other; 
		this.TypeOfBond = bond;
		//this.distance = Math.sqrt(distance);
	}
}
