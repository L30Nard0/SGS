package pk1;

import java.util.Scanner;

import plane.Cartesian;


public class Main {
	
	public static String s = "This is a simulation of a Social Game System (S.G.S) that show the interactions between 4 different kind of independent “players”, conquerors,"
			+ "\nsocial, diplomats and hermit."
			+ "\nEach player is implemented as an independent Thread, this makes the players somehow unpredictable, especially for what concern execution time. \r\n" + 
			"Every player shares some common rules (or methods such as run method and death method) and the final scope to preserve their species. "
			+ "\nThe way they do that is thought reproductions that could be achieved only when a player reaches a certain level of wellness. "
			+ "\nThe increasing or decreasing of the wellness status is determinate by the bonds that player forms with each other’s.\r\n" + 
			"The success of a bonding attempt, between two players, is defined by player’s personal factors as type of character and personality.\r\n\n" + 
			"To make a bond and manage its wellness level a single player, depending on which type it is, will behave differently: \r\n\n" + 
			"	A Conqueror would try to make the other players conquerors as him so his heritage would be much larger but that has a cost "
			+ "\n		in terms of personal wellness;\r\n" + 
			"	A Social would increase its wellness level a lot if it manages to make lots of bonds and in revers decrees that level "
			+ "\n		if at some point of its life it has less than 10 bonds;\r\n" + 
			"	A Diplomat is a sort of conciliator who deal with everyone in the same way;\r\n" + 
			"	A Hermit would act in the opposite way than the Social;\r\n\n" + 
			"When the game start an Arena will show the currents alive players and their evolution trough time.\r\n\n" + 
			"Pease insert the initial number of players: \r\n\n" + 
			"" ;
	
	public static void main(String[] args) throws InterruptedException{
		
		Data.setPath();
		
		@SuppressWarnings("resource")
		Scanner input2 = new Scanner(System.in);
		System.out.print("\n" + s);
		int StartNum = input2.nextInt();
		System.out.println();
		Board.StartNumber = StartNum;
		
		God.Creation(StartNum);
		
		Game.Killer.start();
		
		Game.startGame();
		Thread.sleep(Board.board.SIZE()*110);
		
		System.out.print(Game.clearConsole(40) + Game.Info());
		
		
		Cartesian.plane();
		
		Data.SaveData();
		
		System.err.println("\n\n			THE END");
	}

}
