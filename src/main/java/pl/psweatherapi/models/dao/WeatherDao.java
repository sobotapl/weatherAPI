package pl.psweatherapi.models.dao;

import pl.psweatherapi.models.WeatherModel;

import java.util.List;

public interface WeatherDao {

    void addWeather(WeatherModel model);
    List<WeatherModel> getAllWeatherData(String cityname);
    List<String> getCities();

}
