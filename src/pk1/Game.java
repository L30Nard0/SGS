package pk1;

import java.util.Scanner;


public class Game {
	public static boolean stop = false;

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		System.out.println("The data of S.G.S. will be saved on a file");
		Scanner input = new Scanner(System. in);
		System.out.print("Please insert file name: ");
		Data.filename = input.nextLine();
		
		System.out.print("Insert the start number of cells: ");
		int StartNum = input.nextInt();
		
		for (int i=0; i<StartNum; i++ ) {
			Cell x = new Cell("0000-0");
			Board.board.insert(x);
			System.out.print(x.name + " \"I'm alive\"");
			x.position.printT();
			System.out.println();				
			x.start();
		}

		System.out.println("The Board SIZE is: " + Board.board.SIZE() + "\n");
		
		Thread.currentThread();
		
		System.out.println();
		for (int i = 0; i <4; i++) {
			Thread.sleep(2000);
			System.out.print(". ");
		}
		System.out.println("\n");
		
		String exit = "false";
		Scanner input2 = new Scanner(System.in);
		while (true) {
			exit =  input2.nextLine();
			if (exit != "false" || Board.board.SIZE() == 0) stop = true; break;
		}

		input.close();
		input2.close();
		
		Thread.sleep(Board.board.SIZE()*100);
		System.out.println();
		System.out.println("who is alive? ");
		for (Cell cell : Board.board.list) {
			System.out.println(cell.name + " personality type: " + cell.P.getValue1());
			Board.Xaxis.add(cell.position.getValue1());
			Board.Xaxis.add(cell.position.getValue2());		
		}
		
		
		System.out.println("\nNumber of births: " + Board.births );
		System.out.println("The Board SIZE is: " + Board.board.SIZE());
		System.out.println("The Graveyard SIZE is: " + Board.graveyard);
		System.out.println("\nHeyyyy! Heyyyy! Heyyyy! Heyyyy!\n");
		
		Data.SaveData();	

	}
}
