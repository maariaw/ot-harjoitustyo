// Notes: Week begins on monday.


package viikkokalenteri.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import viikkokalenteri.ui.Localization;

/**
 * Class that provides methods for changing and querying the calendar time frame.
 */
public class TimeService {
    private LocalDate date;
    private int yearWeekNow;
    private TemporalField weekField;

    public TimeService() {
        this.date = LocalDate.now();
        this.weekField = WeekFields.of(Localization.LOCALE)
                .weekOfWeekBasedYear();
        this.yearWeekNow = this.getYearWeek();
    }
    
    /**
     * Returns the number of the week that the calendar is currently set to.
     * 
     * @return  week number of set time
     */
    public int getWeek() {
        return date.get(weekField);
    }
    
    /**
     * Returns the date that the calendar is currently set to.
     * 
     * @return  date of set time
     */
    public LocalDate getDate() {
        return this.date;
    }
    
    /**
     * Puts the calendar one week forward from its currently set time.
     */
    public void nextWeek() {
        this.date = this.date.plusWeeks(1);
    }
    
    /**
     * Puts the calendar one week backward from its currently set time.
     */
    public void lastWeek() {
        this.date = this.date.minusWeeks(1);
    }
    
    /**
     * Sets the calendar to the specified year, month and day.
     * 
     * @param   year  Year to be set
     * @param   month Month to be set
     * @param   day   Day of month to be set
     */
    public void setDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }
    
    /**
     * Returns an integer representing the current day of the week that the
     * calendar is set to.
     * 
     * @return  day of week as integer (0 = Monday) of set time
     */
    private int getWeekDayIndex() {
        DayOfWeek weekDay = this.date.getDayOfWeek();
        int dayIndex = weekDay.getValue() - 1;
        return dayIndex;
    }
    
    /**
     * Returns the date of the specified weekday of the current week of year
     * that the calendar is set to.
     * 
     * @param   dayIndex    weekday of interest (0 = Monday)
     * 
     * @return  date of the specified weekday on week of set time
     */
    public LocalDate getDateOfWeekDay(int dayIndex) {
        long dateDifference = dayIndex - this.getWeekDayIndex();
        LocalDate newDate = this.date.plusDays(dateDifference);
        return newDate;
    }
    
    /**
     * Returns an integer representing the year and number of week that the
     * calendar is currently set to.
     * 
     * @return  year and week identifier of set time
     */
    public int getYearWeek() {
        int year = date.getYear();
        int week = date.get(weekField);
        return year * 100 + week;
    }
    
    /**
     * Returns the integer representing the year and number of week at the time
     * of constructing this TimeService object.
     * 
     * @return  year and week identifier of time at creation
     */
    public int getYearWeekNow() {
        return this.yearWeekNow;
    }
    
    /**
     * Returns true if the week that the calendar is set to is after the
     * week the TimeService was constructed.
     * 
     * @return  true if calendar is set to next week or later
     */
    public boolean isFuture() {
        return this.yearWeekNow < this.getYearWeek();
    }
}
