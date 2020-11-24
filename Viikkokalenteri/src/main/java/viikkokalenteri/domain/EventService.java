
package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import viikkokalenteri.dao.EventDao;

/**
 *
 * @author mawahlst
 */
public class EventService {
    private EventDao eventDao;
    
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    
    public List<Event> getEventsForDay(LocalDate date) {
        return this.eventDao.findEventsForDate(date.toString());
    }
    
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
