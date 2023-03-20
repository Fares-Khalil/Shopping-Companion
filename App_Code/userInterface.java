package App_Code;
import java.util.Scanner;  // Import the Scanner class
/*The access point of the app*/
public class userInterface {
	String cmd;
	Scanner userIN;
	onlineSearch[] search = new onlineSearch[3];
	userInterface(){
		userIN = new Scanner(System.in);
		search[0] = new Dollarama();
		search[1] = new Costco();
		search[2] = new WholesaleClub();
		cmd = "";
		System.out.println("Welcome to Shopping Companion");
		mainLoop();
	}
	public void mainLoop(){
		while(!cmd.toUpperCase().equals("Q")) {
			System.out.println("Please type the product you want. If you would like to quit please type q or Q");
			cmd = userIN.nextLine();
			if (cmd.toUpperCase().equals("Q")) {
				closing();
			}else {
				for(onlineSearch store : search) {
				String[] item = store.searchItem(cmd);
				System.out.println(store.getClass().getName().substring(store.getClass().getName().indexOf(".")+1)+": " +item[0]+": " + item[1]);
			}
		}
			
	}
		
}
	public void closing(){
		
			System.out.println("Are you sure you want to exit? :'(");
			cmd = userIN.nextLine();
			if(cmd.contains("y")) {
				System.out.println("Good bye! Have a nice day!");
				System.exit(0);
			}
			else {
				mainLoop();
			}
	}
}
