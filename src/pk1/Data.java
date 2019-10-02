package pk1;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 

public class Data {
	public static String filename = "prova";
	
	public static void setPath() {
		System.out.println("****************************************************************************************\n");
		System.out.println(" Welcome to this Social Game System simulation \n");
		System.out.println(" Data of your execution will be saved in a file in \"C:\\\\Users\\\\Leo\\\\Desktop\\\\SGS_data/\" \n");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print(" Please insert file name and path: ");
		Data.filename = input.nextLine();
		System.out.println("\n\n****************************************************************************************");
	}
	
	public static void SaveData() {
		
		//String path = "C:\\Users\\Leo\\Desktop\\SGS_data/".concat(filename).concat(".csv");
		String path = filename.concat(".csv");

		try {
		File file = new File(path);
		
		if (file.exists())
		System.out.println("\nThe file " + filename + " in " + path + " already exist");
		else if (file.createNewFile()) {
			System.out.println("\nThe file " + filename + " in " + path + " has been created");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Cell cell : Board.board.list) {
				bw.write("\n");
				bw.write(cell.Personality.getValue1().toString().concat(", "));
			}
			bw.flush();
			bw.close();
		}
		else {System.out.println("\nThe file " + filename + " in " + path + " can not be created");}
		
		
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	public static void saveHisto() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (Cell cell : Board.board.list) {
			switch(cell.Personality.getValue1()) {
			
			case 1: Tupla<Integer, Integer> t1 = new Tupla<Integer, Integer>(cell.Personality.getValue1(), ++a);
					Board.histo.add(t1);
					
			case 2: Tupla<Integer, Integer> t2 = new Tupla<Integer, Integer>(cell.Personality.getValue1(), ++b);
					Board.histo.add(t2);
					
			case 3: Tupla<Integer, Integer> t3 = new Tupla<Integer, Integer>(cell.Personality.getValue1(), ++c);
					Board.histo.add(t3);
					
			case 4: Tupla<Integer, Integer> t4 = new Tupla<Integer, Integer>(cell.Personality.getValue1(), ++d);
					Board.histo.add(t4);
					
			}
		}
		
	}

}
