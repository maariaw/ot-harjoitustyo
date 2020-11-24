/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viikkokalenteri.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import viikkokalenteri.domain.Event;

/**
 *
 * @author mawahlst
 */
public class FileEventDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File eventFile;
    EventDao dao;
    
    @Before
    public void setUp() throws Exception {
        eventFile = testFolder.newFile("testfile_events.txt");
        try (FileWriter file = new FileWriter(eventFile.getAbsolutePath())) {
            file.write("This-Is-aDate;This Is an Event\n");
        }

        dao = new FileEventDao(eventFile.getAbsolutePath());
    }

    @Test
    public void eventsAreFoundFromFile() {
        HashMap<String, List<Event>> events = dao.getAll();
        assertThat(events, not(equalTo(Collections.EMPTY_MAP)));
    }
    
    @Test
    public void eventListsAreNotEmpty() {
        HashMap<String, List<Event>> events = dao.getAll();
        List<Event> oneDay = events.get("This-Is-aDate");
        assertThat(oneDay, not(empty()));
    }
    
    @Test
    public void eventListsAreReadCorrectly() {
        HashMap<String, List<Event>> events = dao.getAll();
        List<Event> oneDay = events.get("This-Is-aDate");
        Event oneEvent = oneDay.get(0);
        assertThat(oneEvent.getDescription(), is(equalTo("This Is an Event")));
    }

    @Test
    public void createdEventsAreListed() throws Exception {
        dao.create(new Event("Ano-ther-Date", "Another Event"));
        HashMap<String, List<Event>> events = dao.getAll();
        List<Event> oneDay = events.get("Ano-ther-Date");
        Event oneEvent = oneDay.get(0);
        assertThat(oneEvent.getDescription(), is(equalTo("Another Event")));
    }
    
    @After
    public void tearDown() {
        eventFile.delete();
    }
}
