package pk1;

import java.util.*;

import com.vdurmont.emoji.EmojiParser;


public abstract class Cell  extends Thread {
	
	protected String name;    // cannot override the final method from Thread
	protected boolean state;
	protected int wellness;
	protected int range;
	
	
	protected Tupla<Integer, int[]> Personality; 	
	protected HashMap<Cell, Integer> connections = new HashMap<Cell, Integer>();
	private static final String deadCellEmoji = ":white_large_square:";
    private static final String aliveCellEmoji = ":black_large_square:";
	 
	
	public Cell() {
		 
		name = "Cell".concat(getName().substring(6));
		state = true;
		wellness = 1;
		
	}
	
    public String getStateEmoji() {

        String emojiStr = state ? aliveCellEmoji : deadCellEmoji;
        return EmojiParser.parseToUnicode(emojiStr);

    }
    
	
	//
	
	//		RUN		//

	public void run() {
		try {
			
			for (int i = 0; i < Board.life; i++) {
				if (Game.stop) this.interrupt();
				if (isInterrupted())  throw new InterruptedException();
				
                sleep(100);
				
    			CellSet<Cell> temp;
                temp = Board.board;
				find(temp); 

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
        state = false;
	}
	
	
	public void forget() {
        Iterator<Cell> it = connections.keySet().iterator();
        while (it.hasNext()) {
        	Cell key = it.next();
        	if ( key.name == null) { 
        		it.remove();
        	}
        }
	}
	
	
	public void find(CellSet<Cell> board) throws InterruptedException {
        int M = board.list.indexOf(this) + range;
        if (M > board.list.size()) M = board.SIZE() - 1;
        int m  = board.list.indexOf(this) - range;
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
		if (!connections.keySet().contains(cell))   
			if (this.isCompatible(cell) != 0)
				connections.put(cell, this.isCompatible(cell) + RandFactor());
	}
	
	
	public  void editStatus() throws InterruptedException {
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
			connections.put(son, 5);
			son.connections.put(this, 5);
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
	
	public abstract String getType(); //
	
	public abstract int RandFactor(); //
	
	public abstract void behavior() throws InterruptedException;
	
	public abstract void status() throws InterruptedException;

}
