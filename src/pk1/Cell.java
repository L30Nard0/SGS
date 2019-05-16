package pk1;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Cell  extends Thread{
	
	public String name;
	public int generation;
	//public Tupla<Integer, Integer> position;
	public int wellness = 1;
	public static int MaxWLevel = 200;   //  level of wellness that a cell must reach to be ready to reproduction
	public Tupla<Integer, int[]> P; // personality
	public HashMap<String, Connection> connections = new HashMap<String, Connection>();	
	
	
	
	// Constructor of a Cell with a name and only one bond with himself
	
	public Cell(int gen) {
		 
		name = "Cell".concat(getName().substring(6));
		
		int[] personality = new int[5];
		for (int i = 0; i < personality.length; i++) 
			personality[i] = new Random().nextInt(2);
		
		P = new Tupla<Integer, int[]>(new Random().nextInt(4) + 1, personality );
		
		this.generation = gen;
		
		//position = new Tupla<Integer, Integer>(new Random().nextInt(3),new Random().nextInt(3));
		
	}
	//
	
	//		RUN		//
	ArrayList<Cell> temp;
	
	public void run() {
		try {
			
			for (int i = 0; i < Board.age; i++) {
                sleep(50);
                temp = Board.board.list;   
             	for (ListIterator<Cell> it = Board.board.list.listIterator(); it.hasNext();) {
             		  Cell value = it.next();
             		  this.addCells(value);
             	}
        
             	synchronized(Board.board.list) {
             		sleep(100);
           		  	this.editW();
           		  	if (isReady()) this.reproduction();
              	}
           	}
			sleep(20);
            int index = Board.board.list.indexOf(this);
            Board.board.list.remove(index);
            this.interrupt();
            System.out.print("\n" + this.name + ": \"I'm dead ");
            if (isInterrupted()) throw new InterruptedException();
            
        } catch (InterruptedException e) {
        	System.out.println("soo long and thanks for all the fish\""  + "\n");
        	Board.graveyard++;
        }
    }
	
	//			//
	
	
	// Cell State
	
	public boolean hasConnection(String name) {
		return connections.keySet().contains(name);
	}
	
	public double getDistance(String name) {
		return connections.get(name).distance;
	}
	
	public int isCompatible(Cell cell) {
		int CompLevel = 0;
		
		int percent = (int)((double)(this.P.getValue1()*100/cell.P.getValue1()));
		if (percent > 100) percent = percent/10;
		
		if (new Random().nextInt(101) <= percent) {
			for (int i = 0; i<this.P.getValue2().length; i++) {
				if (this.P.getValue2()[i] == cell.P.getValue2()[i]) CompLevel++;
			} 
		}
		return CompLevel;
	}
	
	public boolean isReady() {
		if (wellness >= MaxWLevel) return true;
		return false;
	}
	
	public int TypeOfConn(Cell cell) {
		if (hasConnection(cell.getName()))
			return connections.get(cell.getName()).TypeOfBond;
		return 0;
	}
	//	
	
	
	// Cell Action 
	
	public void addCells(Cell cell) {
		if (!connections.keySet().contains(cell.getName()))
			if (this.isCompatible(cell) != 0)
				connections.put(cell.getName(), new Connection(this, cell, this.isCompatible(cell)));
	}
	
	public void editW() {
	    for (Connection connect : connections.values()) {
	    	wellness += connect.TypeOfBond; 
	    }
	    if (connections.entrySet().size() > 10) wellness++;
	    else wellness--;
	    if (connections.entrySet().size() > 20) wellness -= 20;
	}
	
	public  void reproduction() {
		if (this.isReady()) { 
			Cell son = new Cell(generation++);
			connections.put(son.getName(), new Connection(this, son, 5));
			son.connections.put(this.getName(), new Connection(son, this, 5));
			Board.board.list.add(son);
			Board.births++;
			MaxWLevel++;
			System.out.println("A new life is born!!! Its name is: " + son.name);
			if (!Game.stop) son.start();
		}
	}

}
