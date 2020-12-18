
package viikkokalenteri.domain;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mawahlst
 */
public class EventTest {
    
    @Test
    public void notEqualWhenDifferentDate() {
        Event event1 = new Event("date1", null);
        Event event2 = new Event("date2", null);
        assertThat(event1, is(not(event2)));
    }

    @Test
    public void notEqualWhenDifferentDescription() {
        Event event1 = new Event(null, "thing");
        Event event2 = new Event(null, "thingy");
        assertThat(event1, is(not(event2)));
    }

    @Test
    public void notEqualWhenDifferentTime() {
        Event event1 = new Event(null, "1", null, true);
        Event event2 = new Event(null, "2", null, true);
        assertThat(event1, is(not(event2)));
    }

    @Test
    public void notEqualIfOneTimedOneNot() {
        Event event1 = new Event(null, null, null, true);
        Event event2 = new Event(null, null, null, false);
        assertThat(event1, is(not(event2)));
    }

    @Test
    public void EqualWhenSameDateSameDescriptionNotTimed() {
        Event event1 = new Event("date", "thing");
        Event event2 = new Event("date", "thing");
        assertThat(event1, is(equalTo(event2)));
    }

    @Test
    public void EqualWhenSameDateSameDescriptionSameTime() {
        Event event1 = new Event("date", "time", "description", true);
        Event event2 = new Event("date", "time", "description", true);
        assertThat(event1, is(equalTo(event2)));
    }
}
