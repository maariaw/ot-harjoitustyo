// Notes: Week begins on monday.


package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class that provides methods for changing and querying the calendar time frame.
 */
public class TimeService {
    private GregorianCalendar calendar;
    private LocalDate date;
    private int yearWeekNow;
    
    public TimeService() {
        this.calendar = new GregorianCalendar();
        this.setLocalDate();
        this.yearWeekNow = this.getYearWeek();
    }
    
    /**
     * Returns the number of the week that the calendar is currently set to.
     * 
     * @return  week number of set time
     */
    public int getWeek() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
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
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        this.setLocalDate();
    }
    
    /**
     * Puts the calendar one week backward from its currently set time.
     */
    public void lastWeek() {
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        this.setLocalDate();
    }
    
    /**
     * Sets the calendar to the specified year, month and day.
     * 
     * @param   year  Year to be set
     * @param   month Month to be set
     * @param   day   Day of month to be set
     */
    public void setDate(int year, int month, int day) {
        this.calendar.set(year, month - 1, day);
        this.setLocalDate();
    }
    
    public Calendar getCalendar() {
        return this.calendar;
    }
    
    /**
     * Returns an integer representing the current day of the week that the
     * calendar is set to.
     * <p>
     * In the calendar object a Sunday is represented by number 1, so this method
     * converts the numbering to start from Monday and zero.
     * 
     * @return  day of week as integer (0 = Monday) of set time
     */
    private int getWeekDayIndex() {
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int dayIndex;
        if (weekDay == 1) {
            dayIndex = 6;
        } else {
            dayIndex = weekDay - 2;
        }
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
     * Sets the object variable date to represent the date that the calendar
     * is currently set to.
     */
    private void setLocalDate() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        this.date = LocalDate.of(year, month, day);
    }
    
    /**
     * Returns an integer representing the year and number of week that the
     * calendar is currently set to.
     * 
     * @return  year and week identifier of set time
     */
    public int getYearWeek() {
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
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
    
    public String[] dayAbbreviations() {
        String[] dayAbbrList = {"Ma", "Ti", "Ke", "To", "Pe", "La", "Su"};
        return dayAbbrList;
    }
    
    public ObservableList<String> timeOptions() {
        ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String prefix;
            if (i < 10) {
                prefix = "0";
            } else {
                prefix = "";
            }
            hours.add(prefix + i);
        }
        String[] minutes = new String[]{"00", "15", "30", "45"};
        ArrayList<String> times = new ArrayList<>();
        for (String hour : hours) {
            for (String minute : minutes) {
                times.add(hour + ":" + minute);
            }
        }

        return FXCollections.observableArrayList(times);
    }

    public String timeInputRegex() {
        String timeRegex = "(|([0-2](|[0-9])(|:)(|[0-5])(|[0-9])" + "|"
                                + "[0-9](|:)(|[0-5])(|[0-9])))";
        return timeRegex;
    }

    public String validTimeRegex() {
        String validRegex = "([0-1][0-9]|2[0-3]):[0-5][0-9]";
        return validRegex;
    }
}
