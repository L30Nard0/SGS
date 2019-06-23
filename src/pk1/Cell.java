package pk1;

import java.util.*;


public abstract class Cell  extends Thread {
	
	protected String name;    // cannot override the final method from Thread
	protected boolean status;
	protected int wellness;
	private int age;
	
	public Tupla<Float, Float> Position;     
	public Tupla<Integer, int[]> Personality; 	
	public HashMap<String, Integer> connections = new HashMap<String, Integer>(); 
	 
	
	public Cell(String FamilyName) {
		 
		name = "Cell".concat(getName().substring(6)).concat(FamilyName.substring(4));
		status = true;
		wellness = 1;
		Position = Tupla.setRandom();
	}
	//
	
	//		RUN		//

	public void run() {
		try {
			
			CellSet<Cell> temp;
			for (int i = 0; i < Board.life; i++) {
				if (Game.stop) Thread.currentThread().interrupt();
				if (isInterrupted()) { death(); throw new InterruptedException();}
                sleep(70); age++;
                temp = Board.board;
               
                Iterator<String> it = connections.keySet().iterator();
                while (it.hasNext()) {
                	String key = it.next();
                	if (connections.get(key) == null) {
                		it.remove();
                	}
                }
                
                find(temp);
                
             	synchronized(Board.board.list) {
             		sleep(100);
           		  	this.editW();
           		  	if (isReady()) this.reproduction();
              	}
        
             	if(Game.stop) this.interrupt();
           	}
			sleep(20);
            death();
            this.interrupt();
            name = null;
            if (isInterrupted()) throw new InterruptedException();
            
        } catch (InterruptedException e) {
        	if (Game.stop) System.out.println("soo long and thanks for all the fish\""  + "\n");
        }
    }
	//			
	
	
	public boolean hasConnection(String name) {
		return connections.keySet().contains(name);
	}
	
	
	public boolean isReady() {
		if (wellness >= Board.MaxWLevel) return true;
		return false;
	}
	
	
	public void death() throws InterruptedException {
		int index = Board.board.indexOf(this);
        Board.board.remove(index);
        Board.graveyard++;
        status = false;
	}
	
	
	public void find(CellSet<Cell> board) throws InterruptedException {
        int M = board.indexOf(this) + this.joy(age);
        if (M > board.SIZE()) M = board.SIZE();
        int m  = board.indexOf(this) - this.joy(M);
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
	
	public void editW() {
		behavior();
		status();
	    for (Integer TypeOfBond : connections.values()) {
	    	wellness += TypeOfBond; 
	    }

	}
	
	
	public  void reproduction() throws InterruptedException {
		if (this.isReady()) { 
			Cell son = Board.reproductionOf(this);
			connections.put(son.name, 5);
			son.connections.put(this.name, 5);
			Board.board.insert(son);
			Board.births++;
			Board.MaxWLevel += Board.board.SIZE()/10;
			if (Board.births % 10 == 0) Board.MaxWLevel -= 10;
			Game.currentStatus = true;
			sleep(20);
			if (!Game.stop) son.start();
		}
	}
	
	// abstract methods
	
	public abstract int joy(int age);
	
	public abstract void behavior();
	
	public abstract void status();

}
