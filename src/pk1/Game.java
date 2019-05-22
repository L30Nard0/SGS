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
		
		Scanner input2 = new Scanner(System.in);
		System.out.print("Insert the start number of cells: ");
		int StartNum = input2.nextInt();

		
		for (int i=0; i<StartNum; i++ ) {
			Cell x = new Cell(1);
			Board.board.insert(x);
		}

		System.out.println("The Board SIZE is: " + Board.board.SIZE() + "\n");
		
				
		for (Cell cell : Board.board.list) {
			System.out.print(cell.name + " \"I'm alive\"");
			cell.position.printT();
			System.out.println(  );				
			cell.start();
		}
		
		Thread.currentThread();
		
		Thread.sleep(2000);
		System.out.print("\n.");
		Thread.sleep(2000);
		System.out.print(" .");
		Thread.sleep(2000);
		System.out.print(" .");
		Thread.sleep(2000);
		System.out.print(" .\n\n");

		Thread.sleep(50000);
		
		stop = true;
		
		String exit = "false";
		Scanner input3 = new Scanner(System. in);
		while (true) {

			exit =  input3.nextLine();
			if (exit != "false") break;
		}
		
		input.close();
		input2.close();
		input3.close();
		
		//Thread.sleep((int)(StartNum*0.85) *10000);
	
		System.out.println();
		System.out.println("who is alive? ");
		for (Cell cell : Board.board.list)
			System.out.println(cell.name + " " + cell.generation +"°  generation" + " personality type: " + cell.P.getValue1());
		
		System.out.println("\nNumber of births: " + Board.births );
		System.out.println("The Board SIZE is: " + Board.board.SIZE());
		System.out.println("The Graveyard SIZE is: " + Board.graveyard);
		System.out.println("\nHeyyyy! Heyyyy! Heyyyy! Heyyyy!\n");
		
		Data.SaveData();	

	}
}
