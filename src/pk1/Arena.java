package pk1;

import java.util.Random;

public class Arena {
	
    public Cell[][] matrix;
    public static int i = 0;
    public static int j = 0;
    
    public Arena() {
        matrix = new Cell[Board.space][Board.space+10];
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                matrix[i][j] = new Null();
            }
        }
    }
    
    public synchronized Cell get(int i, int j) {
		return matrix[i][j];
    }
    
    public synchronized void addi(Cell cell) {
    	matrix[i][j] = cell;
    	j++;
    	if (j==Board.space+10) {
    		j=0; i++;
    		if (i > Board.space) {
        		System.err.println("\nSpaceOverflow\n");
        		Game.stop = true;
        		Game.Killer.interrupt();
    		}
    	}
    	shuffle(this.matrix);
    }
    
	static void shuffle(Cell[][] a) {
	    Random random = new Random();

	    for (int i = a.length - 1; i > 0; i--) {
	        for (int j = a[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            Cell temp = a[i][j];
	            a[i][j] = a[m][n];
	            a[m][n] = temp;
	        }
	    }
	}
    
    public synchronized int size() {
		return Board.space*Board.space;
    }
    
    public synchronized int hight() {
		return matrix.length;
    }
    
    public synchronized int length() {
		return matrix[1].length;
    }
    
    public synchronized void add(Cell cell) {
    	if (matrix[cell.coordinates.getValue1()][cell.coordinates.getValue2()].name != null)
    		matrix[cell.coordinates.getValue1()][cell.coordinates.getValue2()] = cell;
    	else {
    		cell.setCoordinates();
    		add(cell);
    	}
    	shuffle(this.matrix);
    }

}
