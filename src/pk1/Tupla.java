package pk1;

public class Tupla <T, E> {
	public final T x;
	public final E y;
	
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
}
