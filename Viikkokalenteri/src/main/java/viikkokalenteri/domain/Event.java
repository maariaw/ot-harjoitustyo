
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
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.date);
        hash = 71 * hash + Objects.hashCode(this.time);
        hash = 71 * hash + Objects.hashCode(this.description);
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
