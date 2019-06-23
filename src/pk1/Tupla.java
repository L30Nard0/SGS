package pk1;

import java.util.Random;

public class Tupla <T, E> {
	private static final int X = 8;
	private static final int Y = 8;
	
	protected T x;
	protected E y;
	
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
	
	public static Tupla<Float, Float> setRandom() {
		float xValue = new Random().nextFloat() + new Random().nextInt(X);
		float yValue = new Random().nextFloat() + new Random().nextInt(Y);
		return new Tupla<Float, Float> (xValue, yValue);
		
	}
}
