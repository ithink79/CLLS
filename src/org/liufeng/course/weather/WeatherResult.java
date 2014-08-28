package org.liufeng.course.weather;

import java.util.List;

public class WeatherResult {
        public String currentCity;
        public List<WeatherResultPair> weather_data;
        public String getCurrentCity() {
                return currentCity;
        }
        public void setCurrentCity(String currentCity) {
                this.currentCity = currentCity;
        }
        public List<WeatherResultPair> getWeather_data() {
                return weather_data;
        }
        public void setWeather_data(List<WeatherResultPair> weather_data) {
                this.weather_data = weather_data;
        }
}