package pl.psweatherapi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import netscape.security.UserTarget;
import pl.psweatherapi.models.IWeatherObserver;
import pl.psweatherapi.models.WeatherInfo;
import pl.psweatherapi.models.WeatherModel;
import pl.psweatherapi.models.dao.WeatherDao;
import pl.psweatherapi.models.dao.impl.WeatherDaoImpl;
import pl.psweatherapi.models.services.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver {

    private WeatherService weatherService = WeatherService.getService();

    @FXML
    TextField textCity;

    @FXML
    Button buttonSend;

    @FXML
    Label textWeather;

    @FXML
    ProgressIndicator progressIndi;

    @FXML
    Button buttonChart;


    private WeatherDao weatherDao = new WeatherDaoImpl();


    public void initialize(URL location, ResourceBundle resources) {
        progressIndi.setVisible(false);
        textWeather.setVisible(false);



        weatherService.registerObserver(this);

        buttonSend.setOnMouseClicked(e -> {
            if (!textCity.getText().isEmpty())
                progressIndi.setVisible(true);
                textWeather.setVisible(true);

                weatherService.makeRequest(textCity.getText());
            textCity.clear();

        });

        textCity.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                progressIndi.setVisible(true);
                textWeather.setVisible(true);

                weatherService.makeRequest(textCity.getText());
                textCity.clear();
            }
        });

        buttonChart.setOnMouseClicked(e -> {
            try {
                goToGraph();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void goToGraph() throws IOException {
        Stage stage = (Stage) buttonChart.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("graphView.fxml"));
            stage.setScene(new Scene(root, 600, 400));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {

        textWeather.setText("Temp: " + info.getTemp() + " | Cisnienie: " + info.getPressure());
        progressIndi.setVisible(false);
        textWeather.setVisible(true);

        weatherDao.addWeather(new WeatherModel(info));

    }
}
