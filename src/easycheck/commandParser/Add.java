package easycheck.commandParser;

/**
 * ADD Command Type represents a parsed command for Easy Check application.
 * To be called:
 * getTaskName()
 * getStart()
 * getEnd() 
 * 
 * @author A0126989H
 */

public class Add extends Command {
	private String TaskName; 
	private String Start;
	private String End;

	public Add(String commandType, String[] arguments) {
		super(commandType, arguments);
		TaskName = arguments[0];
		if (arguments.length==2)
			Start = arguments[1];
		if (arguments.length==3)
			End = arguments[2];
	}
	public String getTaskName(){
		return TaskName;
	}
	public String getStart(){
		return Start;
	}
	public String getEnd(){
		return End;
	}
}