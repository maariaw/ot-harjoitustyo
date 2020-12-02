/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viikkokalenteri.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mawahlst
 */
public class EventServiceTest {
    private EventService service;
    
    @Before
    public void setUp() {
        this.service = new EventService(new FakeEventDao());
    }
    
    @Test
    public void getEventsForDayReturnsCreatedEvents() {
        LocalDate pikkujoulu = LocalDate.of(2021, 12, 12);
        this.service.createEvent(pikkujoulu, "Pikkujoulut");
        List<Event> events = this.service.getEventsForDay(pikkujoulu);
        assertThat(events, is(not(empty())));
    }
    
    @Test
    public void getEventsForDayReturnsNoEventsWhenNoneCreated() {
        List<Event> events = this.service.getEventsForDay(LocalDate.of(2020, 9, 11));
        assertThat(events, is(empty()));
    }
    
    @Test
    public void createEventDoesntCreateDuplicates() {
        LocalDate paiva = LocalDate.of(2021, 5, 1);
        String kuvaus = "Vappu";
        this.service.createEvent(paiva, kuvaus);
        this.service.createEvent(paiva, kuvaus);
        List<Event> events = this.service.getEventsForDay(paiva);
        assertThat(events.size(), is(1));
    }
    
}
