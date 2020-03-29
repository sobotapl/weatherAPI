package pl.psweatherapi.models.services;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import pl.psweatherapi.models.Config;
import pl.psweatherapi.models.IWeatherObserver;
import pl.psweatherapi.models.Utils;
import pl.psweatherapi.models.WeatherInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//singleton
public class WeatherService {

    private static WeatherService ourInstance = new WeatherService();

                                //getInstance
    public static WeatherService getService() {return ourInstance; }
    private List<IWeatherObserver> observer = new ArrayList<>();
    private ExecutorService executorService;

    private WeatherService(){
        executorService = Executors.newSingleThreadExecutor();
    }

    public void makeRequest(String city){
        Runnable runnable = () -> readJasonData(Utils.makeHttpRquest(Config.APP_BASE_URL + city + "&appid=" + Config.APP_ID), city);
        executorService.execute(runnable);
    }

    private void readJasonData(String json, String cityName){
       //System.out.println("Vis: " + root.getInt("vibility"));
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");

        int pressure = main.getInt("pressure");
        double temp = main.getDouble("temp");

        observer.forEach(s -> {
            Platform.runLater(() -> s.onWeatherUpdate(new WeatherInfo(temp, pressure, cityName)));

        });
    }
    public void registerObserver(IWeatherObserver observer){
        this.observer.add(observer);
    }


}


