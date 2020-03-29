package pl.psweatherapi.models;

public interface IWeatherObserver {

    void onWeatherUpdate(WeatherInfo info);

}
