
package viikkokalenteri.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viikkokalenteri.dao.FileEventDao;
import viikkokalenteri.domain.TimeService;

public class ViikkokalenteriUi extends Application {
    
    private TimeService timeService;
    private BorderPane layout; // Container for the whole view
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String eventFile = properties.getProperty("eventFile");
            
        FileEventDao eventDao = new FileEventDao(eventFile);
        this.timeService = new TimeService(eventDao);
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
        String[] namesOfDays = {"Maanantai", "Tiistai", "Keskiviikko", 
            "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};
        VBox day = new VBox();
        VBox title = new VBox();
        title.getChildren().add(new Label(namesOfDays[dayOfWeekIndex]));
        
        day.getChildren().add(title);
        return day;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
