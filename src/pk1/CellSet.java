package pk1;
import java.util.*;


public class CellSet<E> {

    public ArrayList <Cell> list = new ArrayList<Cell>();
    
    
    public synchronized boolean isEmpty () {
        return list.isEmpty();
    }
    
    public synchronized void insert (Cell cell) {
    	if (isEmpty())
    		notifyAll();  	
    	list.add(cell);
    	Collections.shuffle(list); 
    	Board.arena.add(cell);

    }
    
    public synchronized Cell get (int i) throws InterruptedException {
        while (isEmpty())
            wait();
    	return list.get(i);
    }
    
    public synchronized Cell remove (int i) throws InterruptedException {
        while (isEmpty())
            wait();
    	return list.remove(i);
    }
    
    public synchronized int indexOf (Cell cell) throws InterruptedException {
        while (isEmpty())
            wait();
    	return list.indexOf(cell);
    }
    
    public synchronized int SIZE() {
    	return list.size();
    }
    
    
}
    
    
