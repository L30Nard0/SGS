package pk1;

import java.util.*;
import java.util.stream.IntStream;


public class Cell  extends Thread {
	
	protected String name;
	protected boolean status = true;
	private int wellness = 1;
	
	public Tupla<Integer, int[]> Personality; // personality
	public Tupla<Float, Float> Position; 	// coordinates (x,y) that represent the position of a cell in space
	public HashMap<String, Integer> connections = new HashMap<String, Integer>(); 
	// map of connections between this Cell and the others
	 
	
	
	// Constructor of a generic Cell 
	
	public Cell(String FamilyName) {
		 
		name = "Cell".concat(getName().substring(6)).concat(FamilyName.substring(4));
		
		status = true;
		
		int[] personality = new int[5];
		IntStream.range(1,6).forEach(val -> personality[val-1] = new Random().nextInt(2));
		
		Personality = new Tupla<Integer, int[]> (new Random().nextInt(4) + 1, personality );
		
		Position = Coordinates.getPosition();
	}
	//
	
	//		RUN		//

	public void run() {
		try {
			
			ArrayList<Cell> temp;
			// the "for" loop goes on until reach the maximum age value then the cell/thread will be interrupt
			for (int i = 0; i < Board.age; i++) {
				if (Game.stop) Thread.currentThread().interrupt();
				if (isInterrupted()) throw new InterruptedException();
                sleep(50);
                temp = Board.board.list;
               
       
                Iterator<String> it = connections.keySet().iterator();
                while (it.hasNext()) {
                	String key = it.next();
                	if (connections.get(key) == null) {
                		it.remove();
                	}
                }
                
                // try to edit using get
                
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
                //
                
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
            name = null;
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
		
		int percent = (int)((double)(this.Personality.getValue1()*100/cell.Personality.getValue1()));
		if (percent > 100) percent = percent/10;
		
		if (new Random().nextInt(101) <= percent) {
			for (int i = 0; i<this.Personality.getValue2().length; i++) {
				if (this.Personality.getValue2()[i] == cell.Personality.getValue2()[i]) CompLevel++;
			} 
		}
		return CompLevel;
	}
	
	public boolean isReady() {
		if (wellness >= Board.MaxWLevel) return true;
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
	    for (Integer TypeOfBond : connections.values()) {
	    	wellness += TypeOfBond; 
	    }
	    
	    if (connections.entrySet().size() > 10) wellness++;
	    else wellness--;
	    if (connections.entrySet().size() > 20) wellness -= 20;
	}
	
	public  void reproduction() {
		if (this.isReady()) { 
			Cell son = new Cell(name);   //reproductionOf(this) return Cell 1,2,3 or 4
			connections.put(son.getName(), 5);
			son.connections.put(this.getName(), 5);
			Board.board.insert(son);
			Board.births++;
			Board.MaxWLevel++;
			System.out.println("A new life is born!!! Its name is: " + son.name);
			if (!Game.stop) son.start();
		}
	}

}
