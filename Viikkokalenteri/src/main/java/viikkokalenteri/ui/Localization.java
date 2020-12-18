
package viikkokalenteri.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for matching, formatting and generating locale specific elements.
 */
public class Localization {
    public static final Locale LOCALE = new Locale("fi", "FI");
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d'.'M'.'");
    public static final String VALID_TIME_REGEX =
            "([0-1][0-9]|2[0-3]):[0-5][0-9]";
    public static final String VALID_INPUT_REGEX =
            "(|([0-2](|[0-9])(|:)(|[0-5])(|[0-9])|[0-9](|:)(|[0-5])(|[0-9])))";
    public static final String[] DAY_ABBRS = 
            {"Ma", "Ti", "Ke", "To", "Pe", "La", "Su"};
    public static final ObservableList<String> TIMEOPTIONS = timeOptions();
    public static final String DEFAULT_TIME = "00:00";

    /**
     * Static method for generating an ObservableList of time options such as
     * "18:15" for every quarter of an hour.
     * 
     * @return  a list of times 15 minutes apart as an ObservableList<String> 
     */
    private static ObservableList<String> timeOptions() {
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
    
}
