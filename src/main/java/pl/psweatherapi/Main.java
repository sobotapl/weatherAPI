package pl.psweatherapi;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psweatherapi.models.IWeatherObserver;
import pl.psweatherapi.models.Utils;
import pl.psweatherapi.models.WeatherInfo;
import pl.psweatherapi.models.services.WeatherService;
import pl.psweatherapi.models.Utils;

import static javafx.fxml.FXMLLoader.*;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Pogodynka");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();
//
//        }
//
//
//public static void main(String[] args) {
//        launch(args);
//
//        }

