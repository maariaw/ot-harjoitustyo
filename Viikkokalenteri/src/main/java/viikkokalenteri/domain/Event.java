
package viikkokalenteri.domain;

import java.util.Objects;
import viikkokalenteri.ui.Localization;

/**
 * Class representing a user created event in the calendar.
 */
public class Event {
    private final String date;
    private final String time;
    private final String description;
    private final boolean timed;
    
    public Event(String date, String description) {
        this(date, Localization.DEFAULT_TIME, description, false);
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
        if (!Objects.equals(this.date, other.date) ||
                !Objects.equals(this.time, other.time)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

}
