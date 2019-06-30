package pk1;

import java.util.Scanner;

import plane.Cartesian;


public class Main {
	
	public static void main(String[] args) throws InterruptedException{
		
		Data.setPath();
		
		@SuppressWarnings("resource")
		Scanner input2 = new Scanner(System.in);
		System.out.print("\nInsert the start number of cells: ");
		int StartNum = input2.nextInt();
		Board.StartNumber = StartNum;
		
		God.Creation(StartNum);
		
		Game.Killer.start();
		
		Game.startGame();
		Thread.sleep(Board.board.SIZE()*110);
		
		new Game().printCell();
		
		Data.saveHisto();
		
		Cartesian.plane();
		
		Data.SaveData();
		
		System.out.println("The END");
	}

}
