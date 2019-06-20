package pk1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Data {
	public static String filename = "prova";
	
	public static void SaveData() {
		
		String path = "C:\\Users\\Leo\\Desktop\\SGS_data/".concat(filename).concat(".txt");
		String pathXY = "C:\\Users\\Leo\\Desktop\\SGS_data/".concat(filename).concat("_xy.csv");


		try {
		File file = new File(path);
		File fileXY = new File(pathXY);
		
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
		else {System.out.println("The file " + filename + " in " + path + " can not be created");}
		
		FileWriter fwXY = new FileWriter(fileXY);
		BufferedWriter bwXY = new BufferedWriter(fwXY);
		for (Float x : Board.Xaxis) {
			bwXY.write(" ");
			bwXY.write(x.toString().concat(","));
		}
		bwXY.write(";");
		bwXY.write("\r\n");
		bwXY.flush();

		for (Float y : Board.Yaxis) {
			bwXY.write(" ");
			bwXY.write(y.toString().concat(","));
		}
		bwXY.flush();
		bwXY.close();
		
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

}
