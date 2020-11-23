package viikkokalenteri.domain;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TimeServiceTest {
    private TimeService service;
    private FakeEventDao eventDao;
    
    @Before
    public void setUp() {
        this.eventDao = new FakeEventDao();
        this.service = new TimeService(eventDao);
    }
    
    @Test
    public void getWeekGivesPossibleWeekWhenCalendarCreated() {
        int week = this.service.getWeek();
        assertThat(week, is(both(greaterThan(0)).and(lessThan(54))));
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
    public void nextWeekPutsWeekNumberToOneWhenDec28() {
        this.service.setTime(2028, 11, 28);
        this.service.nextWeek();
        int week = this.service.getWeek();
        assertEquals(1, week);
    }
    
    @Test
    public void lastWeekDeductsOneFromWeekNumberWhenMiddleOfYear() {
        this.service.setTime(1983, 8, 8);
        int before = this.service.getWeek();
        this.service.lastWeek();
        int after = this.service.getWeek();
        assertEquals(before - 1, after);
    }
    
    @Test
    public void lastWeekPutsWeekNumberTo52or53WhenJan4() {
        this.service.setTime(1965, 0, 4);
        this.service.lastWeek();
        int week = this.service.getWeek();
        assertThat(week, greaterThan(51));
    }
    
//    @Test
//    public void timeIsNowWhenCreated() {
//        Long now = System.currentTimeMillis();
//        Calendar calendar = this.service.getCalendar();
//        assertThat(calendar.getTimeInMillis(), is(both(greaterThan(now - 10000)).and(lessThan(now + 1))));
//    }
    
    @Test
    public void dateIsTodayWhenCreated() {
        Date expectedDate = new Date();
        Calendar calendar = this.service.getCalendar();
        Date actualDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat();
        String expectedString = format.format(expectedDate);
        String actualString = format.format(actualDate);
        assertThat(actualString, is(equalTo(expectedString)));
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
