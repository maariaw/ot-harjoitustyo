
package viikkokalenteri.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import viikkokalenteri.domain.TimeService;

public class ViikkokalenteriUi extends Application {
    
    private TimeService timeService;
    private BorderPane layout;
    
    @Override
    public void init() throws Exception {
        this.timeService = new TimeService();
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
        
        HBox days = new HBox();
        days.getChildren().add(new Label("Ma"));
        days.getChildren().add(new Label("Ti"));
        days.getChildren().add(new Label("Ke"));
        days.getChildren().add(new Label("To"));
        days.getChildren().add(new Label("Pe"));
        days.getChildren().add(new Label("La"));
        days.getChildren().add(new Label("Su"));
        
        layout.setCenter(weekPicker);
        layout.setBottom(days);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
