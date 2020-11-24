
package viikkokalenteri.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import viikkokalenteri.domain.Event;

/**
 *
 * @author mawahlst
 */
public interface EventDao {
    
    Event create(Event event) throws Exception;
    
    HashMap<String, List<Event>> getAll();
    
    List<Event> findEventsForDate(String date);
    
}