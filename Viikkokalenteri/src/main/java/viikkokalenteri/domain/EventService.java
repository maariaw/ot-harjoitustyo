
package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import viikkokalenteri.dao.EventDao;

/**
 * Class for getting events to and from an EventDao.
 */
public class EventService {
    private EventDao eventDao;
    private Comparator<Event> comparator;
    
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
        this.comparator = Comparator
                .comparing(Event::isTimed)
                .thenComparing(Event::getTime);
    }
    
    /**
     * Returns the events previously created for the given date.
     * 
     * @param   date    The date of interest
     * 
     * @return  a list of events scheduled for the given date
     */
    public List<Event> getEventsForDay(LocalDate date) {
        List<Event> eventsForDay = this.eventDao.findEventsForDate(date.toString());
        Collections.sort(eventsForDay, comparator);
        return eventsForDay;
    }
    
    /**
     * Creates an Event object and saves it to the EventDao.
     * 
     * @param   date        Date of the event
     * @param   description Description of the event
     * 
     * @see     viikkokalenteri.dao.FileEventDao#create(viikkokalenteri.domain.Event) 
     * 
     * @return  true if saving event is successful
     */
    public boolean createEvent(LocalDate date, String time, String description, boolean timed) {
        Event event = new Event(date.toString(), time, description, timed);
        try {   
            eventDao.create(event);
        } catch (Exception ex) {
            return false;
        }
        
        return true;
    }

    /**
     * Removes the given event object from the eventDao.
     *
     * @param   event The event that is to be removed
     * @return  true if the eventDao was accessed successfully
     */
    public boolean removeEvent(Event event) {
        try {
            eventDao.remove(event);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
