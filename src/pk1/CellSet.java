package pk1;
import java.util.*;

public class CellSet<E> {
	
    //protected HashMap<String, E> map = new HashMap<String, E>() ;
    protected ArrayList <E> list = new ArrayList<E>();
    
    public synchronized boolean isEmpty () {
        return list.isEmpty();
    }
    
    public synchronized void insert (E cell) {
    	if (isEmpty())
    		notifyAll();  	
    	list.add(cell);
    }

    public synchronized E get (int i) throws InterruptedException {
        while (isEmpty())
            wait();
    	return list.get(i);
    }
    
    public synchronized E remove (int i) throws InterruptedException {
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
    
    
