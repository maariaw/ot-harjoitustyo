// Notes: Week begins on monday.


package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeService {
    private GregorianCalendar calendar;
    private LocalDate date;
    private int yearWeekNow;
    
    public TimeService() {
        this.calendar = new GregorianCalendar();
        this.setLocalDate();
        this.yearWeekNow = this.getYearWeek();
    }
    
    public int getWeek() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public void nextWeek() {
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        this.setLocalDate();
    }
    
    public void lastWeek() {
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        this.setLocalDate();
    }
    
    public void setDate(int year, int month, int day) {
        this.calendar.set(year, month - 1, day);
        this.setLocalDate();
    }
    
    public Calendar getCalendar() {
        return this.calendar;
    }
    
    public int getWeekDayIndex() {
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int dayIndex;
        if (weekDay == 1) {
            dayIndex = 6;
        } else {
            dayIndex = weekDay - 2;
        }
        return dayIndex;
    }
    
    public LocalDate getDateOfWeekDay(int dayIndex) {
        long dateDifference = dayIndex - this.getWeekDayIndex();
        LocalDate newDate = this.date.plusDays(dateDifference);
        return newDate;
    }
    
    private void setLocalDate() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        this.date = LocalDate.of(year, month, day);
    }

    public int getYearWeek() {
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return year * 100 + week;
    }

    public int getYearWeekNow() {
        return this.yearWeekNow;
    }

    public boolean isFuture() {
        return this.yearWeekNow < this.getYearWeek();
    }
}
