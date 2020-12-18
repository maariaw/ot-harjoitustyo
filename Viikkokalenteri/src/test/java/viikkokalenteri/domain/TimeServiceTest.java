package viikkokalenteri.domain;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TimeServiceTest {
    private TimeService service;
    
    @Before
    public void setUp() {
        this.service = new TimeService();
    }

    @Test
    public void getWeekGivesPossibleWeekWhenCalendarCreated() {
        int week = this.service.getWeek();
        assertThat(week, is(both(greaterThan(0)).and(lessThan(54))));
    }

    @Test
    public void nextWeekAddsOneToWeekNumberWhenMiddleOfYear() {
        this.service.setDate(2020, 8, 8);
        int before = this.service.getWeek();
        this.service.nextWeek();
        int after = this.service.getWeek();
        assertEquals(before + 1, after);
    }

    @Test
    public void nextWeekPutsWeekNumberToOneWhenDec28() {
        this.service.setDate(2028, 12, 28);
        this.service.nextWeek();
        int week = this.service.getWeek();
        assertEquals(1, week);
    }

    @Test
    public void lastWeekDeductsOneFromWeekNumberWhenMiddleOfYear() {
        this.service.setDate(1983, 8, 8);
        int before = this.service.getWeek();
        this.service.lastWeek();
        int after = this.service.getWeek();
        assertEquals(before - 1, after);
    }

    @Test
    public void lastWeekPutsWeekNumberTo52or53WhenJan4() {
        this.service.setDate(1965, 1, 4);
        this.service.lastWeek();
        int week = this.service.getWeek();
        assertThat(week, greaterThan(51));
    }

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

    @Test
    public void setDateSetsCorrectDate() {
        int year = 2025;
        int month = 6;
        int day = 17;
        LocalDate date = LocalDate.of(year, month, day);
        this.service.setDate(year, month, day);
        assertThat(service.getDate(), is(date));
    }

    @Test
    public void nextWeekIsFuture() {
        this.service.nextWeek();
        assertTrue(this.service.isFuture());
    }

    @Test
    public void nowIsNotFuture() {
        assertFalse(this.service.isFuture());
    }

    @Test
    public void nextWeekIncreasesYearWeek() {
        int now = this.service.getYearWeek();
        this.service.nextWeek();
        int after = this.service.getYearWeek();
        assertTrue(now < after);
    }

    @Test
    public void nextWeekDoesntIncreaseYearWeekNow() {
        int now = this.service.getYearWeekNow();
        this.service.nextWeek();
        int after = this.service.getYearWeekNow();
        assertThat(after, is(now));
    }

    @Test
    public void DateOfWeekDayIsCorrectForMonday() {
        this.service.setDate(2020, 12, 18);
        LocalDate monday = this.service.getDateOfWeekDay(0);
        LocalDate actual = LocalDate.of(2020, 12, 14);
        assertThat(monday, is(actual));
    }

    @Test
    public void DateOfWeekDayIsCorrectForSunday() {
        this.service.setDate(2020, 12, 18);
        LocalDate sunday = this.service.getDateOfWeekDay(6);
        LocalDate actual = LocalDate.of(2020, 12, 20);
        assertThat(sunday, is(actual));
    }

    @Test
    public void DateOfMondayIsCorrectIfIsSunday() {
        this.service.setDate(2020, 12, 20);
        LocalDate monday = this.service.getDateOfWeekDay(0);
        LocalDate actual = LocalDate.of(2020, 12, 14);
        assertThat(monday, is(actual));
    }
}
