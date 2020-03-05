package event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import warehouse.MySystem;

public class EventProcessor {

  /** Map for all the commands. */
  private static final Map<String, Event> EVENT_MAP = new HashMap<>();

  /**
   * Constructor set up a event processor with order list.
   * 
   * @throws IOException throw the exceptions just in case
   */
  public EventProcessor(String fileName) throws IOException {
    FileHelper.readFile(fileName);
  }

  /**
   * Dynamically new an event
   * 
   * @param command the command read from myorders.txt
   * @return Event
   * @throws EventNotFoundException throw the exceptions just in case
   */
  public Event createEvent(String command) throws EventNotFoundException {
    String[] sublines = command.split(" ");
    String eventName = sublines[0];
    String packageName = "event";
    try {
      Class<?> eventClass = Class.forName(packageName + "." + eventName + "Event");
      Event event = (Event) eventClass.newInstance();
      return event;
    } catch (Exception exception) {
      throw new EventNotFoundException("Cannot create event");
    }
  }

  /**
   * process each command when reading the myorders.txt
   * 
   * @param command each line read from myorders.txt
   * @param system the system that process all the commands
   * @throws Exception throw the exceptions just in case
   */
  public void process(String command, MySystem system) throws Exception {
    String sub = command.substring(0, command.indexOf(" "));
    if (EVENT_MAP.containsKey(sub)) {
      EVENT_MAP.get(sub).processEvent(command, system);
    } else {
      EVENT_MAP.put(sub, this.createEvent(command));
      EVENT_MAP.get(sub).processEvent(command, system);
    }
  }
}
