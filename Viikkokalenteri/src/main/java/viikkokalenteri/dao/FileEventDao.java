
package viikkokalenteri.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import viikkokalenteri.domain.Event;

/**
 *
 * @author mawahlst
 */
public class FileEventDao implements EventDao{
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
                daysEvents.add(new Event(date, parts[1]));
                events.put(date, daysEvents);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (String date : events.keySet()) {
                for (Event event : events.get(date)) {
                    writer.write(event.getDate() + ";" + event.getDescription() + "\n");
                }
            }
        } 
    }
    
    @Override
    public Event create(Event event) throws Exception {
        String date = event.getDate();
        List<Event> daysEvents = events.getOrDefault(date, new ArrayList<>());
        if (!daysEvents.contains(event)) {
            daysEvents.add(event);
        }
        events.put(event.getDate(), daysEvents);
        save();
        return event;
    }
    
    @Override
    public List<Event> findEventsForDate(String date) {
        return events.getOrDefault(date, new ArrayList<>());
    }
    
    @Override
    public HashMap<String, List<Event>> getAll() {
        return events;
    }
    
}
