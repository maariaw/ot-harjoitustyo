
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
        List<Event> eventsForDay = this.eventDao
                .findEventsForDate(date.toString());
        Collections.sort(eventsForDay, comparator);
        return eventsForDay;
    }
    
    /**
     * Creates an Event object and saves it to the EventDao.
     *
     * @param   date        Date of the event
     * @param   time        Time of the event
     * @param   description Description of the event
     * @param   timed       True if time was set by user
     *
     * @see     viikkokalenteri.dao.FileEventDao
     *          #create(viikkokalenteri.domain.Event)
     *
     * @return  true if saving event is successful
     */
    public boolean createEvent(LocalDate date, String time, String description,
            boolean timed) {
        Event event = new Event(date.toString(), time, description, timed);
        try {   
            return eventDao.create(event);
        } catch (Exception ex) {
            return false;
        }
    }
    
    /**
     * Creates a non-timed Event object and saves it to the EventDao.
     *
     * For testing purposes.
     *
     * @param   date        Date of the event
     * @param   description Description of the event
     *
     * @see     viikkokalenteri.domain.EventService
     *          #createEvent(java.time.LocalDate, java.lang.String,
     *          java.lang.String, boolean)
     *
     * @return  true if saving event is successful
     */
    public boolean createEvent(LocalDate date, String description) {
        return this.createEvent(date, "00:00", description, false);
    }

    /**
     * Creates a new event if the given parameters differ from the given event,
     * and then removes the old event. Does nothing if the event is not changed.
     *
     * @see     viikkokalenteri.domain.EventService
     *          #createEvent(java.time.LocalDate, java.lang.String,
     *          java.lang.String, boolean)
     * @see     viikkokalenteri.domain.EventService
     *          #removeEvent(viikkokalenteri.domain.Event)
     *
     * @param   event       The old event
     * @param   date        The desired date of the event
     * @param   time        The desired time of the event
     * @param   description The desired description of the event
     * @param   timed       True if time has been set by user
     */
    public void editEvent(Event event, LocalDate date, String time,
            String description, boolean timed) {
        if (createEvent(date, time, description, timed)) {
            removeEvent(event);
        }
    }

    /**
     * Returns an instance of Event with given date, default time, blank
     * description and false for timed tag
     *
     * @param   date    Date for the event
     * @return  a blank event with specified date
     */
    public Event eventTemplate(LocalDate date) {
        return new Event(date.toString(), "");
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
