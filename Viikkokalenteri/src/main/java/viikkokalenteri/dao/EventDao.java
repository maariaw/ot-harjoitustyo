
package viikkokalenteri.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import viikkokalenteri.domain.Event;

/**
 * Interface for classes that provide event storage.
 */
public interface EventDao {
    
    /**
     * Stores a new event.
     * 
     * @param   event   Event to be stored
     * 
     * @return  the given event
     */
    Event create(Event event) throws Exception;
    
    /**
     * Retrieves all events stored by the EventDao.
     * 
     * @return a map with dates as keys and lists of the date's events as values
     */
    HashMap<String, List<Event>> getAll();
    
    /**
     * Retrieves the events that are scheduled for the given date.
     * 
     * @param   date    Date of interest
     * @return  list of events that have the specified date
     */
    List<Event> findEventsForDate(String date);
    
    /**
     * Removes the given event from the EventDao.
     *
     * @param   event   Event to be removed
     * @return  removed event
     */
    Event remove(Event event) throws Exception;
}
