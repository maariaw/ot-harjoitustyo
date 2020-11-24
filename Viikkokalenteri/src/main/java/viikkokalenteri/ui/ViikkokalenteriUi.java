
package viikkokalenteri.ui;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viikkokalenteri.dao.FileEventDao;
import viikkokalenteri.domain.Event;
import viikkokalenteri.domain.EventService;
import viikkokalenteri.domain.TimeService;

public class ViikkokalenteriUi extends Application {
    
    private TimeService timeService;
    private EventService eventService;
    private DateTimeFormatter formatter;
    private BorderPane layout; // Container for the whole view
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String eventFile = properties.getProperty("eventFile");
            
        FileEventDao eventDao = new FileEventDao(eventFile);
        this.timeService = new TimeService();
        this.eventService = new EventService(eventDao);
        this.formatter = DateTimeFormatter.ofPattern("d'.'M'.'");
        this.layout = new BorderPane();
    }

    @Override
    public void start(Stage calendar) {
        calendar.setTitle("Viikkokalenteri");
        setWeekScene();
        
        Scene weekview = new Scene(layout);
        
        calendar.setScene(weekview);
        calendar.show();
    }
    
    public void setWeekScene() {
        this.layout.getChildren().clear();
        
        HBox weekPicker = new HBox();
        Label week = new Label("Viikko " + this.timeService.getWeek());
        Button back = new Button("<");
        back.setOnAction((event) -> {
            this.timeService.lastWeek();
            setWeekScene();
        });
        Button forward = new Button(">");
        forward.setOnAction((event) -> {
            this.timeService.nextWeek();
            setWeekScene();
        });
        weekPicker.getChildren().add(back);
        weekPicker.getChildren().add(week);
        weekPicker.getChildren().add(forward);
        
        HBox days = createDayView();
        
        Button createEvent = new Button("Uusi tapahtuma");
        createEvent.setOnAction((event) -> {
            this.makeNewEventWindow();
        });
        
        layout.setTop(createEvent);
        layout.setCenter(weekPicker);
        layout.setBottom(days);
        
    }
    
    public HBox createDayView() {
        HBox days = new HBox();
        
        for (int i = 0; i < 7; i++) {
            days.getChildren().add(createADay(i));
        }
        return days;
    }
    
    public VBox createADay(int dayOfWeekIndex) {
        String[] titlesOfDays = {"Ma", "Ti", "Ke", 
            "To", "Pe", "La", "Su"};
        LocalDate date = this.timeService.getDateOfWeekDay(dayOfWeekIndex);
        String fDate = date.format(formatter);
        VBox day = new VBox();
        VBox title = new VBox();
        title.getChildren().add(new Label(titlesOfDays[dayOfWeekIndex]
                + " " + fDate));
        day.getChildren().add(title);
        VBox events = new VBox();
        for (Event event : this.eventService.getEventsForDay(date)) {
            events.getChildren().add(new Label(event.getDescription()));
        }
        day.getChildren().add(events);
        return day;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    private void makeNewEventWindow() {
        Label dateText = new Label("Päivä:");
        // DatePicker
        DatePicker datePicker = new DatePicker();
        
        Label descText = new Label("Tapahtuma:");
        // String input
        TextField description = new TextField();
        
        
        // Button for closing
        Button createButton = new Button("Vie kalenteriin");
        createButton.setOnAction((event) -> {
            this.eventService.createEvent(datePicker.getValue(),
                    description.getText());
            description.setText("");
        });
        
        VBox newEventContainer = new VBox();
        newEventContainer.getChildren().addAll(dateText, datePicker, descText,
                description, createButton);
        Scene newEventScene = new Scene(newEventContainer);
        Stage newEventWindow = new Stage();
        newEventWindow.setTitle("Uusi tapahtuma");
        newEventWindow.setScene(newEventScene);
        newEventWindow.show();
        
    }
}
