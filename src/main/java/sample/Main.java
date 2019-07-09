package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"));
        primaryStage.setTitle("Main");
        Scene scene = new Scene(root, 800, 500, Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(mouseEvent ->  {

                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent ->  {
                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
                primaryStage.setY(mouseEvent.getScreenY() - yOffset);
            });
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
