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

    @Override
    public Event create(Event event) throws Exception {
        events.getOrDefault(event.getDate(), new ArrayList<>()).add(event);
        return event;
    }

    @Override
    public HashMap<String, List<Event>> getAll() {
        return events;
    }
    
}
