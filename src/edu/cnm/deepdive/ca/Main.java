package edu.cnm.deepdive.ca;


import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static final int SCENE_WIDTH = 800;
  public static final int SCENE_HEIGHT = 620;

  @Override
  public void start(Stage primaryStage) throws Exception {
    ClassLoader loader = getClass().getClassLoader();
    ResourceBundle bundle = ResourceBundle.getBundle("resources/strings");
    FXMLLoader fxmlLoader = new FXMLLoader(loader.getResource("resources/main.fxml"), bundle);
    Parent parent = fxmlLoader.load();
    Controller controller = fxmlLoader.getController();
    controller.setModel(new Model());
    Scene scene = new Scene(parent, SCENE_WIDTH, SCENE_HEIGHT);
    primaryStage.setTitle(bundle.getString("windowTitle"));
    primaryStage.setResizable(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void Main(String[] args) {
    launch(args);
  }
}
