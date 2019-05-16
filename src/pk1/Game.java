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

		System.out.println("The Board SIZE is: " + Board.board.SIZE());
		System.out.println();
		
				
		for (Cell cell : Board.board.list) {
			System.out.println(cell.name + " \"I'm alive\"");
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
		
		Thread.sleep(StartNum*1000);
	
		System.out.println();
		System.out.println("who is alive? ");
		for (Cell cell : Board.board.list)
			System.out.println(cell.name + " generation: " + cell.generation +"°" + " personality type: " + cell.P.getValue1()); 
		
		System.out.println("Number of births: " + Board.births );
		System.out.println("The Board SIZE is: " + Board.board.SIZE());
		System.out.println("The Grave SIZE is: " + Board.graveyard);
		System.out.println("Heyyyy! Heyyyy! Heyyyy! Heyyyy!");
		System.out.println();
		
		
		String path = "C:\\Users\\Leo\\Desktop\\SGS_data/".concat(filename).concat(".txt");
		try {
		File file = new File(path);
		if (file.exists())
		System.out.println("Il file " + path + " esiste");
		else if (file.createNewFile()) {
			System.out.println("Il file " + path + " è stato creato");
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
		System.out.println("Il file " + path + " non può essere creato");
		
		} catch (IOException e) {
		e.printStackTrace();
		}
		
	}
}
