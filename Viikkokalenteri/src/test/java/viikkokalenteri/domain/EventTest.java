
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
    public void EqualWhenSameDateSameDescription() {
        Event event1 = new Event("date", "thing");
        Event event2 = new Event("date", "thing");
        assertThat(event1, is(equalTo(event2)));
    }
    
}
