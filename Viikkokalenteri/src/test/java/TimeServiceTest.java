
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import viikkokalenteri.domain.TimeService;


public class TimeServiceTest {
    private TimeService service;
    
    @Before
    public void setUp() {
        this.service = new TimeService();
    }
    
    @Test
    public void nextWeekAddsOneToWeekNumberWhenMiddleOfYear() {
        this.service.setTime(2020, 8, 8);
        int before = this.service.getWeek();
        this.service.nextWeek();
        int after = this.service.getWeek();
        assertEquals(before + 1, after);
    }
    
    @Test
    public void nextWeekPutsWeekNumberToOneWhenEndOfYear() {
        this.service.setTime(2020, 11, 30);
        this.service.nextWeek();
        int after = this.service.getWeek();
        assertEquals(1, after);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
