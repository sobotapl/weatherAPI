package pl.psweatherapi.models;

public class WeatherInfo {
    private double temp;
    private int pressure;
    private String cityName;


    public WeatherInfo(double temp, int pressure, String cityName) {
        this.temp = temp;
        this.pressure = pressure;
        this.cityName = cityName;
    }


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
