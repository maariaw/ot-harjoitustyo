
package viikkokalenteri.domain;

import java.util.Objects;

/**
 * Class representing a user created event in the calendar.
 */
public class Event {
    private final String date;
    private final String time;
    private final String description;
    private final boolean timed;
    
    public Event(String date, String description) {
        this(date, "00:00", description, false);
    }

    public Event(String date, String time, String description, boolean timed) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.timed = timed;
    }

    public String getTime() {
        return time;
    }

    public boolean isTimed() {
        return timed;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.time);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + (this.timed ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.timed != other.timed) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

}
