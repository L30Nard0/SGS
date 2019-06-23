package plane;

import javax.swing.JFrame;

public class CartesianFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CartesianPanel panel;

	public CartesianFrame() {
		
		panel = new CartesianPanel();
		add(panel);
	 }

	 public void showUI() {
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Cartesian");
		 setSize(700, 700);
		 setVisible(true);
	 }
   }
