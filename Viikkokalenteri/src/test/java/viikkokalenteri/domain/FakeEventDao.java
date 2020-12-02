/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viikkokalenteri.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import viikkokalenteri.dao.EventDao;

/**
 *
 * @author mawahlst
 */
public class FakeEventDao implements EventDao {
    private HashMap<String, List<Event>> events;

    public FakeEventDao() {
        events = new HashMap<>();
    }

    @Override
    public Event create(Event event) throws Exception {
        String date = event.getDate();
        List<Event> daysEvents = events.getOrDefault(date, new ArrayList<>());
        if (!daysEvents.contains(event)) {
            daysEvents.add(event);
        }
        events.put(event.getDate(), daysEvents);
        return event;
    }

    @Override
    public HashMap<String, List<Event>> getAll() {
        return events;
    }

    @Override
    public List<Event> findEventsForDate(String date) {
        return events.getOrDefault(date, new ArrayList<>());
    }
    
}
