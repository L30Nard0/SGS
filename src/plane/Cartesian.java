package plane;

import javax.swing.SwingUtilities;

import pk1.Board;

public class Cartesian {
	
	 public static void main(String[] args) {
		  SwingUtilities.invokeLater(new Runnable() {

		   @Override
		   public void run() {
		    CartesianFrame frame = new CartesianFrame();
		    frame.showUI();
		    
		    //frame.panel.drawPoint(new Tupla (2.0 , 4.0));
		   }
		   
		  });
	}
	 
	 public static void plane() {
		 
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					CartesianFrame frame = new CartesianFrame();
				    frame.showUI();
				    
	             	synchronized(Board.histo) {
	    			    Board.histo.forEach(p -> frame.panel.drawPoint(p));
	              	}
				}
				   
			}); 
	 }

}
