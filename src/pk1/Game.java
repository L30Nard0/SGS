package pk1;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import plane.*;


public class Game {
	public static boolean stop = false;
	public static boolean currentStatus = false;
	
	static Thread Killer = new Thread() {
		public void run() {
			Scanner input2 = new Scanner(System.in);
			if (Board.board.SIZE() != 0) {
				String exit = "false";
				exit =  input2.nextLine();
				if (exit != "false") stop = true; 
			}
			input2.close();
		}
	};
	
    public static void tick(){
    	//System.out.print("\033[H\033[2J");
    	System.out.flush();
        System.out.print(Info() + "\n"); //(clearConsole() + Info());
    }
    
    
    public static String Info() {
        return
                "Alive: " + Board.board.SIZE() + "\n" +
                //"Dead: " + currentDead + "\n" +
                "Births: " + Board.births + "\n" +
                "Deaths: " + Board.graveyard + "\n";
    }
    
    public static void startGame(){
        System.out.print(Info() + "\n");
        while (true){
        	if (Game.stop) break;
            try {
                Thread.sleep(1000);
                tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

	public static void main(String[] args) throws InterruptedException{
		
		System.out.println("S.G.S. data will be saved on a file");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System. in);
		System.out.print("Please insert file name: ");
		Data.filename = input.nextLine();
		System.out.print("Insert the start number of cells: ");
		int StartNum = input.nextInt();
		Board.StartNumber = StartNum;
		
		for (int i=0; i<StartNum; i++ ) {
			Cell x = Board.newCell("0000-0");
			Board.board.insert(x);
			System.out.print(x.name + " \"I'm alive\" " + x.Personality.getValue1() +"\n");			
			x.start();
		}
		
		Thread.currentThread();
		
		System.out.println();
		while(!currentStatus) {
			Thread.sleep(2000);
			System.out.print(". ");
		}
		System.out.println("\n");
	
		
		Killer.start();
		
		Game.startGame();
		Thread.sleep(Board.board.SIZE()*110);
		
		for (Cell cell : Board.board.list) {
			System.out.println(cell.name + " personality type: " + cell.Personality.getValue1());
			Board.Axis.add(cell.Position);	
		}
		
		System.out.println("\n"+Game.Info());
		
		Thread.sleep(1000);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CartesianFrame frame = new CartesianFrame();
			    frame.showUI();
			    
             	synchronized(Board.board.list) {
    			    Board.Axis.forEach(p -> frame.panel.drawPoint(p));
              	}
			}
			   
		});
		
		Data.SaveData();	
	}
}
