/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viikkokalenteri.domain;

import java.time.LocalDate;
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

    @Test
    public void removeEventRemovesAnEvent() {
        LocalDate paiva = LocalDate.of(2024, 2, 14);
        String kuvaus = "Ystävänpäivä";
        this.service.createEvent(paiva, kuvaus);
        Event event = new Event(paiva.toString(), kuvaus);
        this.service.removeEvent(event);
        List<Event> events = this.service.getEventsForDay(paiva);
        assertThat(events, is(empty()));
    }

    @Test
    public void removeEventRemovesOnlyOneEvent() {
        LocalDate paiva = LocalDate.of(2024, 2, 14);
        String kuvaus1 = "Ystävänpäivä";
        String kuvaus2 = "Syntymäpäivä";
        this.service.createEvent(paiva, kuvaus1);
        this.service.createEvent(paiva, kuvaus2);
        Event event = new Event(paiva.toString(), kuvaus1);
        this.service.removeEvent(event);
        List<Event> events = this.service.getEventsForDay(paiva);
        assertThat(events.size(), is(1));
    }

    @Test
    public void editEventKeepsEventTheSameIfSameParameters() {
        LocalDate date = LocalDate.of(2077, 6, 8);
        String time = "07:07";
        String description = "Cyberpunk";
        boolean timed = true;
        Event event = new Event(date.toString(), time, description, timed);
        service.createEvent(date, time, description, timed);
        service.editEvent(event, date, time, description, timed);
        List<Event> events = this.service.getEventsForDay(date);
        assertThat(events.get(0), is(event));
    }

    @Test
    public void editEventDoesntAddEvents() {
        LocalDate date = LocalDate.of(2077, 6, 8);
        String time = "07:07";
        String description = "Cyberpunk";
        boolean timed = true;
        Event event = new Event(date.toString(), time, description, timed);
        service.createEvent(date, time, description, timed);
        service.editEvent(event, date, time, "Cyberpunk2077", timed);
        List<Event> events = this.service.getEventsForDay(date);
        assertThat(events.size(), is(1));
    }

    @Test
    public void editEventChangesDateCorrectly() {
        LocalDate date = LocalDate.of(2077, 6, 8);
        LocalDate newDate = LocalDate.of(2020, 12, 10);
        String time = "00:00";
        String description = "Cyberpunk";
        boolean timed = true;
        Event event = new Event(date.toString(), time, description, timed);
        service.createEvent(date, time, description, timed);
        service.editEvent(event, newDate, time, description, timed);
        List<Event> events = this.service.getEventsForDay(newDate);
        assertThat(events.size(), is(1));
    }
}
