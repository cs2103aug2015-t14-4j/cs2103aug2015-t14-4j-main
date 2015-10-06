package easycheck.eventlist;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class ToDoEvent extends Event {
	private static final int EVENT_INDEX_ARRAY_INDEX = 1;
	private static final int EVENT_NAME_ARRAY_INDEX = 2;
	private static final int EVENT_DUE_DATE_ARRAY_INDEX = 3;
	
	private static final String DATE_AND_TIME_FORMAT = "E MM.dd.yyyy 'at' hh:mm:ss a zzz";
	private static final String MESSAGE_JSON_INPUT_ERROR = "Error parsing date string from File at event: ";
	private static final String MESSAGE_DUE_DATE_INPUT_ERROR = "Error while parsing new due date and time";
	private static final String MESSAGE_JSON_STRING_ERROR = "Error in toJsonString method, most likely coding error";
	private static final String MESSAGE_TO_STRING_TEMPLATE = "%s due on %s";
	
	private static final String JSON_TYPE = "type";
	private static final String JSON_EVENT_INDEX = "index";
	private static final String JSON_EVENT_NAME = "name";
	private static final String JSON_DUE_DATE = "due";
	
	private Date dueDateAndTime;
	
	public ToDoEvent(int eventIndex, String eventName, String dueDateAndTimeString) {
		
	}
	
	public ToDoEvent(String jSonString) {
		SimpleDateFormat ft = 
			      new SimpleDateFormat (DATE_AND_TIME_FORMAT);
		Object obj = JSONValue.parse(jSonString);
		JSONArray array=(JSONArray)obj;
		Integer eventIndex = (Integer) array.get(EVENT_INDEX_ARRAY_INDEX);
		String eventName = (String) array.get(EVENT_NAME_ARRAY_INDEX);
		try {
			dueDateAndTime = ft.parse((String)array.get(EVENT_DUE_DATE_ARRAY_INDEX));
		} catch(ParseException e) {
			System.out.println(MESSAGE_JSON_INPUT_ERROR + eventIndex);
		}
		
		this.setEventIndex(eventIndex.intValue());
		this.setEventName(eventName);
	}
	
	/**
	 * Returns the due date and time of this event
	 * @return Date dueDateAndTime
	 */
	public Date getDueDateAndTime() {
		return dueDateAndTime;
	}
	
	/**
	 * Sets a new due date and time
	 * @param newDateString in format of "E MM.dd.yyyy 'at' hh:mm:ss a zzz"
	 * See Documentation for java.text.SimpleDateFormat
	 */
	public void setDueDateAndTime(String newDateString) {
		SimpleDateFormat ft = 
			      new SimpleDateFormat (DATE_AND_TIME_FORMAT);
		try {
			dueDateAndTime = ft.parse(newDateString);
		} catch(ParseException e) {
			System.out.println(MESSAGE_DUE_DATE_INPUT_ERROR);
		}
	}
	
	/**
	 * Returns the string form of this calendar event
	 */
	public String toString() {
		SimpleDateFormat ft = 
			      new SimpleDateFormat(DATE_AND_TIME_FORMAT);
		return String.format(
				MESSAGE_TO_STRING_TEMPLATE, this.getEventName(), ft.format(dueDateAndTime));
	}
	
	/**
	 * Returns the JSON string to write to the file for this calendar object
	 * @return jsonString
	 */
	public String toJsonString() {
		SimpleDateFormat ft = 
			      new SimpleDateFormat(DATE_AND_TIME_FORMAT);
		Map obj=new LinkedHashMap();
		obj.put(JSON_TYPE, "calendar");
		obj.put(JSON_EVENT_INDEX, new Integer(this.getEventIndex()));
		obj.put(JSON_EVENT_NAME, this.getEventName());
		obj.put(JSON_DUE_DATE, ft.format(dueDateAndTime));
		
		StringWriter out = new StringWriter();
	    try {
			JSONValue.writeJSONString(obj, out);
		} catch (IOException e) {
			System.out.println(MESSAGE_JSON_STRING_ERROR);
		}
	    return out.toString();
	}
	
	// TODO override equals method
	public boolean equals(Object obj) {
		return false;
	}
	
	// TODO override compareTo method
	public int compareTo(Event e) {
		return 0;
	}
}