package easycheck.userInterface;

import java.util.Scanner;

import easycheck.logicController.LogicController;

/*
* User Interface to take in User input and pass to Logic Controller.
*/

public class UserInterface {
	private static final String MESSAGE_ENTER_COMMAND = "enter command: ";
	public String userName;
	
	private Scanner sc = new Scanner(System.in);
	private LogicController logicController;

	public static void main(String[] args){
		String fileName;
		if(args.length == 0) {
			fileName = "myeasycheck.txt";
		} else {
			fileName = args[0];
		}
		UserInterface ui = new UserInterface(fileName);
		String userInput = "";
		String commandResponse = "";
		
		while(true) {
			ui.display(MESSAGE_ENTER_COMMAND);
			userInput = ui.getInput();
			ui.executeCommand(userInput);
			ui.display(commandResponse);
		}
	}
	
	public UserInterface(String easyCheckFileName) {
		logicController = new LogicController(easyCheckFileName);
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getInput() {
		String line = sc.nextLine();
		return line;
	}
	
	public String executeCommand(String userInput) {
		return logicController.executeCommand(userInput);
	}
	
	public void display(String msg) {
		System.out.print(msg);
	}
}