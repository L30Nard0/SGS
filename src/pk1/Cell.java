package pk1;

import java.util.*;


public class Cell  extends Thread {
	
	public String name;
	public int generation; 		// dynamic field increase from parent to child
	
	private int wellness = 1;
	public Tupla<Integer, int[]> P; // personality
	private static int MaxWLevel = 200;   //  level of wellness that a cell must reach to be ready to reproduction
	
	public Tupla<Float, Float> position; 	// coordinates (x,y) that represent the position of a cell in space
	public HashMap<String, Integer> connections = new HashMap<String, Integer>(); 
	// map of connections between this Cell and the others
	 
	
	
	// Constructor of a generic Cell 
	
	public Cell(int gen) {
		 
		name = "Cell".concat(getName().substring(6));
		
		int[] personality = new int[5];
		for (int i = 0; i < personality.length; i++) 
			personality[i] = new Random().nextInt(2);
		
		P = new Tupla<Integer, int[]>(new Random().nextInt(4) + 1, personality );
		
		this.generation = gen;
		
		position = Position();
		
		synchronized(Board.Xaxis) {
			Board.Xaxis.add(position.getValue1());
		}
		synchronized(Board.Yaxis) {
			Board.Yaxis.add(position.getValue2());
		}
	}
	
	public static Tupla<Float, Float> Position() {
		
		int signX = -1;
		if (new Random().nextInt(2) == 1) signX = 1;
		int signY = -1;
		if (new Random().nextInt(2) == 1) signY = 1;
		
		float xValue = new Random().nextFloat() + new Random().nextInt(20);
		while (Board.Xaxis.contains(xValue)) xValue = new Random().nextFloat() + new Random().nextInt(20);
		float yValue = new Random().nextFloat() + new Random().nextInt(20);
		while (Board.Xaxis.contains(yValue)) xValue = new Random().nextFloat() + new Random().nextInt(20);
		
		float x = xValue*signX;

		float y = yValue*signY;
		
		return new Tupla<Float, Float>(x, y);
}
	//
	
	//		RUN		//
	
	ArrayList<Cell> temp;
	
	public void run() {
		try {
			
			// the "for" loop goes on until reach the maximum age value then the cell/thread will be interrupt
			for (int i = 0; i < Board.age; i++) {
				if (isInterrupted()) throw new InterruptedException();
                sleep(30);
                temp = Board.board.list;
                
                
                int M = temp.indexOf(this) + 30;
                if (M > temp.size()) M = temp.size();
                int m  = temp.indexOf(this) - 30;
                if (m < 0) m = 0;
                
                for (int j = temp.indexOf(this); j<M; j++ ) {
                	this.addCells(temp.get(j));
                }
                
                for (int j = temp.indexOf(this); j>m; j--) {
                	this.addCells(temp.get(j));
                }
                
             	synchronized(Board.board.list) {
             		sleep(100);
           		  	this.editW();
           		  	if (isReady()) this.reproduction();
              	}
             	
             	if(Game.stop) this.interrupt();
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
	
	//			
	
	// Cell State
	
	public boolean hasConnection(String name) {
		return connections.keySet().contains(name);
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
	//	
	
	
	// Cell Action 
	
	public void addCells(Cell cell) {
		if (!connections.keySet().contains(cell.getName()))
			if (this.isCompatible(cell) != 0)
				connections.put(cell.getName(), this.isCompatible(cell));
	}
	
	public void editW() {
		// personal method
		
	    for (Integer TypeOfBond : connections.values()) {
	    	wellness += TypeOfBond; 
	    }
	    if (connections.entrySet().size() > 10) wellness++;
	    else wellness--;
	    if (connections.entrySet().size() > 20) wellness -= 20;
	}
	
	public  void reproduction() {
		if (this.isReady()) { 
			Cell son = new Cell(generation++);
			connections.put(son.getName(), 5);
			son.connections.put(this.getName(), 5);
			Board.board.list.add(son);
			Board.births++;
			MaxWLevel++;
			System.out.println("A new life is born!!! Its name is: " + son.name);
			if (!Game.stop) son.start();
		}
	}

}
