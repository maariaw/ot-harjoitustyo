// Notes: Week begins on monday.


package viikkokalenteri.domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import viikkokalenteri.dao.EventDao;

public class TimeService {
    private GregorianCalendar calendar;
    private EventDao eventDao;
    
    public TimeService(EventDao eventdao) {
        this.eventDao = eventdao;
        this.calendar = new GregorianCalendar(TimeZone.getDefault());
        this.calendar.setTime(new Date());
    }
    
    public int getWeek() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    public void nextWeek() {
        calendar.add(Calendar.DAY_OF_YEAR, 7);
    }
    
    public void lastWeek() {
        calendar.add(Calendar.DAY_OF_YEAR, -7);
    }
    
    public void setTime(int year, int month, int day) {
        this.calendar.set(year, month, day);
    }
    
    public Calendar getCalendar() {
        return this.calendar;
    }
    
    public boolean createEvent(LocalDate date, String description) {
        Event event = new Event(date.toString(), description);
        try {   
            eventDao.create(event);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
