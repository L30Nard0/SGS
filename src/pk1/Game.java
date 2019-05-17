package pk1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Game {
	public static boolean stop = false;
	private static String filename = "prova";

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		System.out.println("The data of S.G.S. will be saved on a file");
		Scanner in = new Scanner(System. in);
		System.out.print("Please insert file name: ");
		filename = in.nextLine();
		
		Scanner input = new Scanner(System.in);
		System.out.print("Insert the start number of cells: ");
		int StartNum = input.nextInt();
		in.close();
		input.close();
		
		for (int i=0; i<StartNum; i++ ) {
			Cell x = new Cell(1);
			Board.board.insert(x);
		}

		System.out.println("The Board SIZE is: " + Board.board.SIZE() + "\n");
		
				
		for (Cell cell : Board.board.list) {
			System.out.print(cell.name + " \"I'm alive\"");
			cell.position.printT();
			System.out.println();
			//if (!cell.isAlive())					
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

		Thread.sleep(10000);
		
		stop = true;
		
		Thread.sleep(StartNum*1500);
	
		System.out.println();
		System.out.println("who is alive? ");
		for (Cell cell : Board.board.list)
			//System.out.println(cell.name + " " + cell.generation +"°  generation" + " personality type: " + cell.P.getValue1());
			{System.out.print(cell.name); cell.position.printT();}
		
		System.out.println("\nNumber of births: " + Board.births );
		System.out.println("The Board SIZE is: " + Board.board.SIZE());
		System.out.println("The Graveyard SIZE is: " + Board.graveyard);
		System.out.println("\nHeyyyy! Heyyyy! Heyyyy! Heyyyy!\n");
		
		
		String path = "C:\\Users\\Leo\\Desktop\\SGS_data/".concat(filename).concat(".txt");
		try {
		File file = new File(path);
		if (file.exists())
		System.out.println("The file " + filename + " in " + path + " already exist");
		else if (file.createNewFile()) {
			System.out.println("The file " + filename + " in " + path + " has been created");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Cell cell : Board.board.list) {
				bw.write("\n");
				bw.write(cell.P.getValue1().toString());
			}
			bw.flush();
			bw.close();
		}
		else
		System.out.println("The file " + filename + " in " + path + " can not be created");
		
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
}
