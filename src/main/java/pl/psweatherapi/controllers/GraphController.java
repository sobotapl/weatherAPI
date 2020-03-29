package pl.psweatherapi.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pl.psweatherapi.models.WeatherModel;
import pl.psweatherapi.models.dao.WeatherDao;
import pl.psweatherapi.models.dao.impl.WeatherDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class GraphController implements Initializable {

    @FXML
    BarChart chartTemp;

    @FXML
    ListView<String> listCities;
    private ObservableList<String> cityObservableList;

    @FXML
    Button buttonBack;

    private WeatherDao weatherDao = new WeatherDaoImpl();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityObservableList = FXCollections.observableList(weatherDao.getCities());
        listCities.setItems(cityObservableList);

        listCities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           generateChart(newValue);

       });

        buttonBack.setOnMouseClicked(e -> {
            try {
                takeMeBack();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



    }



    private void generateChart(String cityname) {
        List<WeatherModel> weatherList = weatherDao.getAllWeatherData(cityname);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(cityname);

        for (WeatherModel weatherModel : weatherList) {
            series.getData().add(new XYChart.Data<>(weatherModel.getDate().toString(), weatherModel.getTemp()-273));
        }

        chartTemp.getData().clear();
        chartTemp.getData().add(series);

    }

    private void takeMeBack() throws IOException {
    Stage stage = (Stage) buttonBack.getScene().getWindow();
        try {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        stage.setScene(new Scene(root, 600, 400));
    }catch (
    IOException e){
        e.printStackTrace();
    }
}
}
