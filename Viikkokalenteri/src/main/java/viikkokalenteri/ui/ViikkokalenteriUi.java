
package viikkokalenteri.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
import javafx.scene.layout.Priority;
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
        this.formatter = Localization.FORMATTER;
        this.layout = new VBox(8);
    }
    
    /**
     * Opens the application to the main view.
     * @param   calendar    The Stage of main view
     */
    @Override
    public void start(Stage calendar) {
        calendar.setTitle("Viikkokalenteri");
        calendar.setMinWidth(800);
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
        if (this.timeService.isFuture()
                || this.timeService.getYearWeek() < 202045) {
            // No images stored for before week 45, so show the current image
            imgfile.append(Integer.toString(this.timeService.getYearWeekNow()));
        } else {
            imgfile.append(Integer.toString(this.timeService.getYearWeek()));
        }
        imgfile.append(".jpg");
        
        InputStream imgInputStream = this.getClass()
                .getResourceAsStream(imgfile.toString());
        Image calendarPicture = new Image(imgInputStream);
        ImageView pictureIV = new ImageView(calendarPicture);
        VBox pictureframe = new VBox();
        pictureframe.setAlignment(Pos.CENTER);
        pictureframe.getChildren().add(pictureIV);

        HBox weekPicker = new HBox(6);
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
        weekPicker.getChildren().addAll(back, week, forward);

        Button createEvent = new Button("Uusi tapahtuma");

        GridPane middlepanel = new GridPane();
        middlepanel.add(createEvent, 0, 0);
        middlepanel.add(weekPicker, 1, 0);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        ColumnConstraints column3 = new ColumnConstraints();
        column1.setPercentWidth(20);
        middlepanel.getColumnConstraints().addAll(column1, column2, column3);
        middlepanel.setPadding(new Insets(0, 5, 0, 5));

        GridPane days = createDayView();
        days.setPadding(new Insets(0, 5, 5, 5));
        
        createEvent.setOnAction((event) -> {
            this.makeNewEventWindow(
                    this.timeService.getDate(), "", false,
                    Localization.DEFAULT_TIME
            );
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
        LocalDate date = this.timeService.getDateOfWeekDay(dayOfWeekIndex);
        String fDate = date.format(formatter);
        VBox day = new VBox(5);
        VBox title = new VBox();
        Label daytitle = new Label(
                Localization.DAY_ABBRS[dayOfWeekIndex]
                + " " + fDate
        );
        daytitle.setFont(new Font("Arial", 16));
        daytitle.setPadding(new Insets(0,0,0,5));
        title.getChildren().add(daytitle);
        day.getChildren().add(title);
        VBox events = new VBox(3);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(events);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        events.setPadding(new Insets(3));
        for (Event event : this.eventService.getEventsForDay(date)) {
            Label eventdesc = new Label(event.getDescription());
            if (event.isTimed()) {
                eventdesc.setText(event.getTime() + " "
                        + event.getDescription());
            }
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
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Tapahtuman poisto");
                alert.setHeaderText("Haluatko varmasti poistaa tapahtuman?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    eventService.removeEvent(event);
                    setWeekScene();
                }
            });
            MenuItem edit = new MenuItem("Muokkaa");
            menu.getItems().add(edit);
            edit.setOnAction((choice) -> {
                boolean changed = makeNewEventWindow(
                        LocalDate.parse(event.getDate()),
                        event.getDescription(),
                        event.isTimed(), event.getTime());
                if (changed) {
                    eventService.removeEvent(event);
                    setWeekScene();
                }
            });

            eventdesc.setContextMenu(menu);
            eventdesc.setOnMouseClicked((click) -> {
                menu.show(eventdesc,Side.BOTTOM, 0, 0);
            });
            events.getChildren().add(eventdesc);
        }
        day.getChildren().add(scroll);
        day.setPrefHeight(365);
        day.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return day;
    }

    /**
     * Opens a new window for the event creation.
     */
    private boolean makeNewEventWindow(LocalDate initDate,
            String initDescription, boolean timed, String initTime) {
        ArrayList<Integer> eventsCreated = new ArrayList<>();

        Label dateText = new Label("Päivä:");
        
        DatePicker datePicker = new DatePicker(initDate);
        datePicker.setEditable(false);

        CheckBox timeToggle = new CheckBox("Aseta aika");
        timeToggle.setAllowIndeterminate(false);
        timeToggle.setSelected(timed);

        ComboBox timePicker = new ComboBox(Localization.TIMEOPTIONS);
        timePicker.setValue(initTime);
        timePicker.visibleProperty().bind(timeToggle.selectedProperty());
        timePicker.managedProperty().bind(timeToggle.selectedProperty());
        timePicker.setEditable(true);
        timePicker.getEditor().setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText()
                        .matches(Localization.VALID_INPUT_REGEX)
                        ) ? change : null));

        timePicker.getEditor().focusedProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        String timeInput = (String) timePicker.getValue();
                        int inputSize = timeInput.length();
                        StringBuilder timeFix = new StringBuilder();
                        if (inputSize > 2) {
                            if (inputSize > 3) {
                                timeFix.append(timeInput.subSequence(0, 2));
                            } else {
                                timeFix.append("0");
                                timeFix.append(timeInput.charAt(0));
                            }
                            timeFix.append(":");
                            timeFix.append(timeInput.substring(inputSize - 2));
                            timeInput = timeFix.toString();
                            timePicker.setValue(timeInput);
                        }
                        if (!timeInput.matches(Localization.VALID_TIME_REGEX)) {
                            timePicker.getEditor().requestFocus();
                        }
                    }
                });

        Label descText = new Label("Tapahtuma:");
        
        TextField description = new TextField(initDescription);
        
        Button createButton = new Button("Vie kalenteriin");
        createButton.setOnAction((event) -> {
            LocalDate date = datePicker.getValue();
            String text = description.getText();
            if (date != null && !text.isBlank()) {
                this.eventService.createEvent(datePicker.getValue(),
                    (String) timePicker.getValue(), description.getText(),
                    timeToggle.isSelected());
                eventsCreated.add(1);
                this.setWeekScene();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        
        BooleanBinding timeValid = Bindings.createBooleanBinding(() -> {
            return timePicker.getEditor().getText()
                    .matches(Localization.VALID_TIME_REGEX);
        }, timePicker.getEditor().textProperty());
        createButton.disableProperty().bind(timeValid.not());

        VBox newEventContainer = new VBox(10);
        newEventContainer.setPadding(new Insets(10, 10, 15, 10));
        newEventContainer.getChildren().addAll(dateText, datePicker, timeToggle,
                timePicker, descText, description, createButton);
        Scene newEventScene = new Scene(newEventContainer);
        Stage newEventWindow = new Stage();
        newEventWindow.setMinWidth(150);
        newEventWindow.setMinHeight(260);
        newEventWindow.setWidth(260);
        newEventWindow.setHeight(260);
        newEventWindow.setTitle("Uusi tapahtuma");
        newEventWindow.setScene(newEventScene);
        newEventWindow.showAndWait();
        return !eventsCreated.isEmpty();
    }

    public static void main(String[] args) {
        Locale.setDefault(Localization.LOCALE);
        launch(args);
    }
}

