package pk1;

import java.util.*;


public abstract class Cell  extends Thread {
	
	protected String name;    // cannot override the final method from Thread
	protected boolean Status;
	protected int wellness;
	protected int age;
	protected int range;
	
	public Tupla<Float, Float> Position;     
	public Tupla<Integer, int[]> Personality; 	
	public HashMap<String, Integer> connections = new HashMap<String, Integer>(); 
	 
	
	public Cell() {
		 
		name = "Cell".concat(getName().substring(6));
		Status = true;
		wellness = 1;
		Position = God.setRandCoordinates();
	}
	//
	
	//		RUN		//

	public void run() {
		try {
			
			CellSet<Cell> temp;
            temp = Board.board;
            find(temp);
            
			for (int i = 0; i < Board.life; i++) {
				if (Game.stop) this.interrupt();
				if (isInterrupted())  throw new InterruptedException();
				
                sleep(70); 

                forget();
                
                editStatus();

           	}
			sleep(20);
            death();
            if (isInterrupted()) throw new InterruptedException();
            
        } catch (InterruptedException e) {
        	if (Game.stop) System.out.print("#");
        }
    }		
	
	
	public boolean hasConnection(String name) {
		return connections.keySet().contains(name);
	}
	
	
	public boolean isReady() {
		boolean ready = false;
		if (wellness >= Board.MaxWLevel) ready = true;
		return ready;
	}
	
	
	public void death() throws InterruptedException {
        this.interrupt();
        name = null;
		int index = Board.board.indexOf(this);
        Board.board.remove(index);
        Board.graveyard++;
        Status = false;
	}
	
	
	public void forget() {
        Iterator<String> it = connections.keySet().iterator();
        while (it.hasNext()) {
        	String key = it.next();
        	if (connections.get(key) == null) {
        		it.remove();
        	}
        }
	}
	
	
	public void find(CellSet<Cell> board) throws InterruptedException {
        int M = board.indexOf(this) + range;
        if (M > board.SIZE()) M = board.SIZE();
        int m  = board.indexOf(this) - range;
        if (m < 0) m = 0;
        
        for (int j = board.indexOf(this); j<M; j++ ) {
        	this.tryToBond(board.get(j));
        }
        
        for (int j = board.indexOf(this); j>m; j--) {
        	this.tryToBond(board.get(j));
        }
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
	
	
	public void tryToBond(Cell cell) {
		if (!connections.keySet().contains(cell.getName()))   
			if (this.isCompatible(cell) != 0)
				connections.put(cell.name, this.isCompatible(cell));
	}
	
	public  void editStatus() throws InterruptedException {
		age ++;
     	synchronized(Board.board.list) {
     		sleep(100);
    		behavior();
    		status();
   		  	if (isReady()) this.reproduction();
      	}
     	
	    for (Integer TypeOfBond : connections.values()) {
	    	wellness += TypeOfBond; 
	    }

	}
	
	
	public  void reproduction() throws InterruptedException {
		if (this.isReady()) { 
			Cell son = God.reproductionOf(this);
			connections.put(son.name, 5);
			son.connections.put(this.name, 5);
			Board.board.insert(son);
			Board.births++;
			Board.MaxWLevel += Board.board.SIZE()/15;
			if (Board.births % 10 == 0) Board.MaxWLevel -= 10;
			Game.wait = false;
			sleep(20);
			if (!Game.stop) son.start();
		}
	}
	
	// abstract methods
	
	public abstract void behavior();
	
	public abstract void status();

}
