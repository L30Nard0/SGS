package plane;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import pk1.Tupla;
import pk1.Board;;

public class CartesianPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// x-axis coord constants
	public static final int X_AXIS_FIRST_X_COORD = 50;
	public static final int X_AXIS_SECOND_X_COORD = 600;
	public static final int X_AXIS_Y_COORD = 600;

	// y-axis coord constants
	public static final int Y_AXIS_FIRST_Y_COORD = 50;
	public static final int Y_AXIS_SECOND_Y_COORD = 600;
	public static final int Y_AXIS_X_COORD = 50;

	//arrows of axis are represented with "hypotenuse" of 
	//triangle
	// now we are define length of cathodes of that triangle
	public static final int FIRST_LENGHT = 6;
	public static final int SECOND_LENGHT = 6;

	// size of start coordinate length
	public static final int ORIGIN_COORDINATE_LENGHT = 1;

	// distance of coordinate strings from axis
	public static final int AXIS_STRING_DISTANCE = 30;
	
	public void drawPoint(Tupla<Integer, Integer> p) {
	    repaint();
	}

	private void drawPointOnPanel(Tupla<Integer, Integer> p, Graphics g) {
		int xCoordNumbers = 10;
		int yCoordNumbers = 10;
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ xCoordNumbers;
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ yCoordNumbers;
	    final int pointDiameter = 5;
	    final int x = (int) (X_AXIS_FIRST_X_COORD + (p.getValue1() * xLength) - pointDiameter / 2);
	    final int y = (int) (Y_AXIS_SECOND_Y_COORD - (p.getValue2() * yLength) - pointDiameter / 2);
	    g.fillOval(x, y, pointDiameter, pointDiameter);
	}


	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	// x-axis
		g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
				X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
	// y-axis
		g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
				Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

	  // draw origin Point
		g2.fillOval(
				X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2), 
				Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

	 // numerate axis
		int xCoordNumbers = 10;
		int yCoordNumbers = 10;
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)/ xCoordNumbers;
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)/ yCoordNumbers;

	 // draw x-axis numbers
		for(int i = 1; i < xCoordNumbers; i++) {
			g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
					X_AXIS_Y_COORD - SECOND_LENGHT,
					X_AXIS_FIRST_X_COORD + (i * xLength),
					X_AXIS_Y_COORD + SECOND_LENGHT);
			g2.drawString(Integer.toString(i), 
					X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
					X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		}

	 //draw y-axis numbers
		for(int i = 1; i < yCoordNumbers; i++) {
			g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
					Y_AXIS_SECOND_Y_COORD - (i * yLength), 
					Y_AXIS_X_COORD + SECOND_LENGHT,
					Y_AXIS_SECOND_Y_COORD - (i * yLength));
			g2.drawString(Integer.toString(i), 
					Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, 
					Y_AXIS_SECOND_Y_COORD - (i * yLength));
		}
		
		Board.histo.forEach(p -> drawPointOnPanel(p, g));
		//Board.board.list.forEach(p -> drawPointOnPanel(p.Position, g));
	}
}
