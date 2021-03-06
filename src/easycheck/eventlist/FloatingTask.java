package easycheck.eventlist;
/*
 * Class for tasks that do not have a start or end date
 * @@author A0145668R
 */

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FloatingTask extends Event {
	private static final String JSON_TYPE = "type";
	private static final String JSON_EVENT_INDEX = "index";
	private static final String JSON_EVENT_NAME = "name";
	private static final String MESSAGE_JSON_STRING_ERROR = "Error in toJsonString method, most likely coding error";
	private static final String YELLOW = "@|yellow %s|@";
	private static final String GREEN = "@|green %s|@";
	
	private boolean complete;
	
	public FloatingTask() {}
	
	/**
	 * Event constructor
	 * @param eventIndex, the index of the event
	 * @param eventDesc, the description of the event
	 */
	public FloatingTask(int eventIndex, String eventName) {
		super(eventIndex, eventName);
		this.complete = false;
	}
	
	public FloatingTask(FloatingTask e) {
		this.setEventIndex(e.getEventIndex());
		this.setEventName(e.getEventName());
		this.complete = e.isDone();
	}
	
	public FloatingTask(JSONObject jsonObj){
		Long eventIndex = (Long) jsonObj.get(JSON_EVENT_INDEX);
		String eventName = (String) jsonObj.get(JSON_EVENT_NAME);
		this.setEventIndex(eventIndex.intValue());
		this.setEventName(eventName);
	}
	
	public void markComplete() {
		complete = true;
	}
	
	public void unMarkComplete() {
		complete = false;
	}
	
	public boolean isDone() {
		return complete;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(isDone()) {
			return String.format(GREEN, this.getEventIndex() + ". " + this.getEventName() + "\n");
		} else {
			return String.format(YELLOW, this.getEventIndex() + ". " + this.getEventName() + "\n");
		}
	}
	
	public String toPrintGroupString() {
		return this.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	@Override
	public int compareTo(Event e) {
		if(e instanceof FloatingTask) {
			return this.getEventName().compareTo(e.getEventName());
		} else if (e instanceof CalendarEvent) {
			return -1;
		} else if (e instanceof ToDoEvent) {
			return -1;
		}
		return 0;
	}
	
	
	public String toJsonString() {
		Map obj = new LinkedHashMap();
		obj.put(JSON_TYPE, "floating");
		obj.put(JSON_EVENT_INDEX, new Integer(this.getEventIndex()));
		obj.put(JSON_EVENT_NAME, this.getEventName());
		
		StringWriter out = new StringWriter();
	    try {
			JSONValue.writeJSONString(obj, out);
		} catch (IOException e) {
			System.out.println(MESSAGE_JSON_STRING_ERROR);
		}
	    return out.toString();
	}
	
	public Event createCopy() {
		return new FloatingTask(this);
	}
	
	// @@author A0126989H
	public void setDone(){
		this.complete = true;
	}
	
	public void setUndone(){
		this.complete = false;
	}
	
	
}
