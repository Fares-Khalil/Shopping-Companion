package App_Code;
import java.util.Scanner;  // Import the Scanner class
/*The access point of the app*/
public class userInterface {
	String cmd;
	Scanner userIN;
	onlineSearch search;
	userInterface(){
		userIN = new Scanner(System.in);
		search = new onlineSearch();
		cmd = "";
		System.out.println("Welcome to Shopping Companion");
		mainLoop();
	}
	public void mainLoop(){
		while(cmd.toUpperCase() != "Q") {
			System.out.println("Please type the product you want. If you would like to quit please type q or Q");
			cmd = userIN.nextLine();
			if (cmd.toUpperCase() == "Q") {
				closing();
			}
			System.out.println(search.searchItem(cmd));
		}
		
	}
	public void closing(){
		
			System.out.println("Are you sure you want to exit? :'(");
			cmd = userIN.nextLine();
			if(cmd.contains("yes")) {
				System.out.println("Good bye! Have a nice day!");
				System.exit(0);
			}
			else {
				mainLoop();
			}
	}
}
