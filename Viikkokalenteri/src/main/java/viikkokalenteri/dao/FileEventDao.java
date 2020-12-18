
package viikkokalenteri.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import viikkokalenteri.domain.Event;

/**
 * Implementation of the EventDao interface, that allows persistent storage of events in a file.
 */
public class FileEventDao implements EventDao {
    private String file;
    private HashMap<String, List<Event>> events;
    
    public FileEventDao(String file) throws Exception {
        this.events = new HashMap<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String date = parts[0];
                List<Event> daysEvents = events.getOrDefault(date, new ArrayList<>());
                daysEvents.add(new Event(date, parts[1], parts[2], Boolean.parseBoolean(parts[3])));
                events.put(date, daysEvents);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    /**
     * Writes the contents of the events map to the file.
     */
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (String date : events.keySet()) {
                for (Event event : events.get(date)) {
                    writer.write(event.getDate() + ";" + event.getTime() + ";"
                            + event.getDescription() + ";"
                            + String.valueOf(event.isTimed()) + "\n");
                }
            }
        } 
    }
    
    /**
     * Finds a list of events from the events map by using the
     * event's date as keyword, then adds this event to the list and saves
     * the updated map to file.
     * 
     * @param   event   Event to be added to the dao
     * 
     * @see     #save() 
     * 
     * @return  the added event
     */
    @Override
    public boolean create(Event event) throws Exception {
        String date = event.getDate();
        List<Event> daysEvents = events.getOrDefault(date, new ArrayList<>());
        if (!daysEvents.contains(event)) {
            daysEvents.add(event);
        } else {
            return false;
        }
        events.put(event.getDate(), daysEvents);
        save();
        return true;
    }
    
    /**
     * Returns the list of events corresponding to the given date.
     * 
     * @param   date    Date of interest
     * 
     * @return  list of dates scheduled for the date
     */
    @Override
    public List<Event> findEventsForDate(String date) {
        return events.getOrDefault(date, new ArrayList<>());
    }
    
    /**
     * Returns all the saved events as a HashMap.
     * 
     * @return  a HashMap of lists of events by date.
     */
    @Override
    public HashMap<String, List<Event>> getAll() {
        return events;
    }

    /**
     * Removes the given event from the event map and saves the updated map
     * to file.
     *
     * @param   event   The event to be removed
     * @return  the removed event
     * @throws Exception
     */
    @Override
    public Event remove(Event event) throws Exception {
        events.get(event.getDate()).remove(event);
        save();
        return event;
    }
}
