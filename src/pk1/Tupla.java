package pk1;


public class Tupla <T, E> {
	
	protected T x;
	protected E y;
	
	public Tupla ( T x, E y){
		this.x = x;
		this.y = y;
	}
	
	public T getValue1() {
		return x;
	}
	
	public void setValue1(T x) {
		this.x = x;
	}
	
	public E getValue2() {
		return y;
	}
	
	public void setValue2(E y) {
		this.y = y;
	}
	
	public void printT() {
		System.out.println(" x: " + x + " y: " + y);
	}
	
}
