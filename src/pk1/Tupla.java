package pk1;

public class Tupla <T, E> {
	private final T x;
	private final E y;
	
	public Tupla ( T x, E y){
		this.x = x;
		this.y = y;
	}
	
	public T getValue1() {
		return x;
	}
	
	public E getValue2() {
		return y;
	}
	
	public void printT() {
		System.out.println(" x: " + x + " y: " + y);
	}
}
