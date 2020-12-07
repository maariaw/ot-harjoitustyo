
package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.List;
import viikkokalenteri.dao.EventDao;

/**
 * Class for getting events to and from an EventDao.
 */
public class EventService {
    private EventDao eventDao;
    
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    
    /**
     * Returns the events previously created for the given date.
     * 
     * @param   date    The date of interest
     * 
     * @return  a list of events scheduled for the given date
     */
    public List<Event> getEventsForDay(LocalDate date) {
        return this.eventDao.findEventsForDate(date.toString());
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
    public boolean createEvent(LocalDate date, String description) {
        Event event = new Event(date.toString(), description);
        try {   
            eventDao.create(event);
        } catch (Exception ex) {
            return false;
        }
        
        return true;
    }
}
