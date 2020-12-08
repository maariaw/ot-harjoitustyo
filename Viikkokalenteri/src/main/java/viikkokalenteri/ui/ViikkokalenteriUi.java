
package viikkokalenteri.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import viikkokalenteri.dao.FileEventDao;
import viikkokalenteri.domain.Event;
import viikkokalenteri.domain.EventService;
import viikkokalenteri.domain.TimeService;

/**
 * Class for creating the graphic user interface of the application.
 */
public class ViikkokalenteriUi extends Application {
    
    private TimeService timeService;
    private EventService eventService;
    private DateTimeFormatter formatter;
    private VBox layout; // Container for the whole view
    
    /**
     * Initializes the local variables.
     */
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        String propFile = "config.properties";

        try {
            properties.load(new FileInputStream(propFile));
        } catch (IOException e) {
            FileWriter writer = new FileWriter(new File(propFile));
            writer.write("eventFile=events.txt");
            writer.close();
            properties.load(new FileInputStream(propFile));
        }
        
        String eventFile = properties.getProperty("eventFile");
            
        FileEventDao eventDao = new FileEventDao(eventFile);
        this.timeService = new TimeService();
        this.eventService = new EventService(eventDao);
        this.formatter = DateTimeFormatter.ofPattern("d'.'M'.'");
        this.layout = new VBox(8);
    }
    
    /**
     * Opens the application to the main view.
     * @param   calendar    The Stage of main view
     */
    @Override
    public void start(Stage calendar) {
        calendar.setTitle("Viikkokalenteri");
        setWeekScene();
        
        Scene weekview = new Scene(layout, 800, 1000);
        
        calendar.setScene(weekview);
        calendar.show();
    }
    
    /**
     * Constructs and updates the main view of the calendar.
     */
    public void setWeekScene() {
        this.layout.getChildren().clear();
        
        StringBuilder imgfile = new StringBuilder("/");
        if (this.timeService.isFuture() || this.timeService.getYearWeek() < 202045) {
            imgfile.append(Integer.toString(this.timeService.getYearWeekNow()));
        } else {
            imgfile.append(Integer.toString(this.timeService.getYearWeek()));
        }
        imgfile.append(".jpg");
        
        InputStream imgInputStream = this.getClass().getResourceAsStream(imgfile.toString());
        Image calendarPicture = new Image(imgInputStream);
        ImageView pictureIV = new ImageView(calendarPicture);
        Pane pictureframe = new Pane();
        pictureframe.getChildren().add(pictureIV);

        HBox weekPicker = new HBox(6);
        weekPicker.setPrefWidth(200);
        weekPicker.setPrefHeight(20);
        weekPicker.setAlignment(Pos.CENTER);

        Label week = new Label("Viikko " + this.timeService.getWeek());
        week.setFont(new Font("Arial", 20));
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

        Button createEvent = new Button("Uusi tapahtuma");

        GridPane middlepanel = new GridPane();
        middlepanel.add(createEvent, 1, 1);
        middlepanel.add(weekPicker, 2, 1);

        middlepanel.getColumnConstraints().add(new ColumnConstraints(5));
        middlepanel.getColumnConstraints().add(new ColumnConstraints(195));
        middlepanel.getColumnConstraints().add(new ColumnConstraints(400));
        middlepanel.getColumnConstraints().add(new ColumnConstraints(200));

        GridPane days = createDayView();
        days.setPadding(new Insets(0, 5, 5, 5));
        
        createEvent.setOnAction((event) -> {
            this.makeNewEventWindow();
        });
        
        layout.getChildren().add(pictureframe);
        layout.getChildren().add(middlepanel);
        layout.getChildren().add(days);
        
    }
    
    /**
     * Builds the day grid of the calendar.
     * @return  the container for the day view
     */
    public GridPane createDayView() {
        GridPane days = new GridPane();
        
        for (int i = 0; i < 7; i++) {
            days.add(createADay(i), i, 0);
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            days.getColumnConstraints().add(column);
        }
        
        return days;
    }
    
    /**
     * Constructs a single day panel.
     * @param   dayOfWeekIndex  Index of the weekday to be created (0 = Monday)
     * @return  the container for a day view
     */
    public VBox createADay(int dayOfWeekIndex) {
        String[] titlesOfDays = {"Ma", "Ti", "Ke", 
            "To", "Pe", "La", "Su"};
        LocalDate date = this.timeService.getDateOfWeekDay(dayOfWeekIndex);
        String fDate = date.format(formatter);
        VBox day = new VBox(5);
        VBox title = new VBox();
        Label daytitle = new Label(titlesOfDays[dayOfWeekIndex]
                + " " + fDate);
        daytitle.setFont(new Font("Arial", 16));
        daytitle.setPadding(new Insets(0,0,0,5));
        title.getChildren().add(daytitle);
        day.getChildren().add(title);
        VBox events = new VBox(3);
        events.setPadding(new Insets(3));
        for (Event event : this.eventService.getEventsForDay(date)) {
            Label eventdesc = new Label(event.getDescription());
            eventdesc.setWrapText(true);
            eventdesc.setLineSpacing(-2);
            eventdesc.setOnMouseEntered((mouseOn) -> {
                eventdesc.setTextFill(Color.BLUE);
            });
            eventdesc.setOnMouseExited((mouseOff) -> {
                eventdesc.setTextFill(Color.BLACK);
            });

            ContextMenu menu = new ContextMenu();
            MenuItem delete = new MenuItem("Poista");
            menu.getItems().add(delete);
            delete.setOnAction((choice) -> {
                eventService.removeEvent(event);
                setWeekScene();
            });

            eventdesc.setContextMenu(menu);
            eventdesc.setOnMouseClicked((click) -> {
                menu.show(eventdesc,Side.BOTTOM, 0, 0);
            });
            events.getChildren().add(eventdesc);
        }
        day.getChildren().add(events);
        day.setPrefHeight(365);
        day.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return day;
    }
    
    /**
     * Opens a new window for the event creation.
     */
    private void makeNewEventWindow() {
        Label dateText = new Label("Päivä:");
        
        DatePicker datePicker = new DatePicker(this.timeService.getDate());
        
        Label descText = new Label("Tapahtuma:");
        
        TextField description = new TextField();
        
        Button createButton = new Button("Vie kalenteriin");
        createButton.setOnAction((event) -> {
            LocalDate date = datePicker.getValue();
            String text = description.getText();
            if (date != null && !text.isBlank()) {
                this.eventService.createEvent(datePicker.getValue(),
                    description.getText());
            description.setText("");
            this.setWeekScene();
            }
        });
        
        VBox newEventContainer = new VBox(10);
        newEventContainer.setPadding(new Insets(10, 10, 15, 10));
        newEventContainer.getChildren().addAll(dateText, datePicker, descText,
                description, createButton);
        Scene newEventScene = new Scene(newEventContainer, 300, 170);
        Stage newEventWindow = new Stage();
        newEventWindow.setTitle("Uusi tapahtuma");
        newEventWindow.setScene(newEventScene);
        newEventWindow.show();
        
    }
    
    public static void main(String[] args) {
        Locale.setDefault(new Locale("fi", "FI"));
        launch(args);
    }
    
}

