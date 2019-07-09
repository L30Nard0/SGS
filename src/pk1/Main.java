package pk1;

import java.util.Scanner;

import plane.Cartesian;


public class Main {
	
	public static void main(String[] args) throws InterruptedException{
		
		Data.setPath();
		
		@SuppressWarnings("resource")
		Scanner input2 = new Scanner(System.in);
		System.err.print("\n Insert the start number of cells: ");
		int StartNum = input2.nextInt();
		System.out.println();
		Board.StartNumber = StartNum;
		
		God.Creation(StartNum);
		
		Game.Killer.start();
		
		Game.startGame();
		Thread.sleep(Board.board.SIZE()*110);
		
		System.out.print(Game.clearConsole(40) + Game.Info());
		
		
		Cartesian.plane();
		
		Data.SaveData();
		
		System.err.println("\n\n			THE END");
	}

}
