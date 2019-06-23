package plane;

import javax.swing.SwingUtilities;

import pk1.Tupla;

public class Cartesian {
	
	 public static void main(String[] args) {
		  SwingUtilities.invokeLater(new Runnable() {

		   @Override
		   public void run() {
		    CartesianFrame frame = new CartesianFrame();
		    frame.showUI();
		    
		    frame.panel.drawPoint(Tupla.setRandom());
		   }
		   
		  });
	}

}
