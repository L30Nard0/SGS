package pk1;


import java.util.Scanner;


public class Game {
	
	public static boolean stop = false;
	public static boolean wait = true;

	
	static Thread Killer = new Thread() {
		public void run() {
			Scanner input2 = new Scanner(System.in);
			if (Board.board.SIZE() != 0) {
				String exit = "false";
				exit =  input2.nextLine();
				if (exit != "false") {
					Data.saveHisto();
					wait = false;
					stop = true; 
				} 
			}
			input2.close();
		}
	};

    
    public static String Info() {
        return  "		ARENA \n\n" + new Game().printCell() + "\n\n"+
        		"Space: " + (Board.arena.size() - Board.board.SIZE()) + "			Conquerors: " +  Board.Conqueror + "\n" +
                "Alive: " + Board.board.SIZE() +  "			Socials: " +  Board.Social + "\n" + 
                "Births: " + Board.births + "			Diplomats: " +  Board.Diplomat + "\n" + 
                "Deaths: " + Board.graveyard + "			Hermits: " +  Board.Hermit + "\n";
    }

    
    
    public static String clearConsole(int n){
        StringBuilder backSpace = new StringBuilder();
        for (int i=0; i<n; i++){
        	backSpace.append("\n"); 
        }
        return backSpace.toString();
    }
    
    
    public static void  Wait() throws InterruptedException {
    	Thread.currentThread();
    	System.out.println();
		while(wait) {
			Thread.sleep(500);
			System.out.print(". ");
			if (Board.board.SIZE() == 0) break;
		}
		System.out.println("\n");
    }
    
    
    public String printCell() {
    	
        StringBuilder boardString = new StringBuilder();
        for (int i=0; i<Board.arena.hight(); i++){
            for (int j=0; j<Board.arena.length(); j++){
                boardString.append(Board.arena.get(i, j).getStateEmoji());
            }
            boardString.append("\n");
        }
        return boardString.toString();
        
    }
  
    
    public static void startGame() throws InterruptedException {
    	
		Game.Wait();
        while (true) {
        	if (Game.stop) break;
            Thread.sleep(1000);
            System.out.flush();
            if (!Game.stop) {
            	System.out.print(clearConsole(40) + Info());
            }
        }
    }
    
}
