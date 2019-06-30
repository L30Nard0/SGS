package pk1;

import java.util.Scanner;
//import java.util.stream.IntStream;


public class Game {
	
	public static boolean stop = false;
	public static boolean wait = true;
	
	static Thread Killer = new Thread() {
		public void run() {
			Scanner input2 = new Scanner(System.in);
			if (Board.board.SIZE() != 0) {
				String exit = "false";
				exit =  input2.nextLine();
				if (exit != "false") { wait = false; stop = true; } 
			}
			input2.close();
		}
	};

    
    public static String Info() {
        return
                "Alive: " + Board.board.SIZE() + "\n" +
                "Births: " + Board.births + "\n" +
                "Deaths: " + Board.graveyard + "\n";
    }
    
    
    public static String clearConsole(){
        StringBuilder backSpace = new StringBuilder();
        for (int i=0; i<Board.StartNumber+25; i++){
        	backSpace.append("\n"); 
        }
        return backSpace.toString();
    }
    
    
    public static void  Wait() throws InterruptedException {
    	Thread.currentThread();
    	System.out.println();
		while(wait) {
			Thread.sleep(1000);
			System.out.print(". ");
			if (Board.board.SIZE() == 0) break;
		}
		System.out.println("\n");
    }
    
    
    public void printCell() {
    	System.out.println(clearConsole());
    	//IntStream.range(0, Board.board.SIZE()).forEach(val -> (val%10 == 0) ? System.out.print("#") : System.out.println("\n#"));
    	for (int i = 1; i < Board.board.SIZE()+1; i++) {
    		System.out.print("|*|");
			if (i%10 == 0 ) System.out.println();
		}
    	System.out.println();
    	System.out.println("\n" +Info());
    }
    
    
    public static void startGame() throws InterruptedException {
		Game.Wait();
        while (true) {
        	if (Game.stop) break;
            Thread.sleep(1000);
            System.out.flush();
            if (!Game.stop) new Game().printCell();
        }
    }
    
}
