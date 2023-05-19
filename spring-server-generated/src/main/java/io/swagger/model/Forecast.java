package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.ForecastTemperature;
import io.swagger.model.WeatherForecast;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Forecast
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class Forecast   {
  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("pressure")
  private Double pressure = null;

  @JsonProperty("humidity")
  private Integer humidity = null;

  @JsonProperty("windSpeed")
  private Double windSpeed = null;

  @JsonProperty("clouds")
  private Integer clouds = null;

  @JsonProperty("temperature")
  private ForecastTemperature temperature = null;

  @JsonProperty("weather")
  private WeatherForecast weather = null;

  public Forecast date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public Forecast pressure(Double pressure) {
    this.pressure = pressure;
    return this;
  }

  /**
   * Get pressure
   * @return pressure
   **/
  @Schema(description = "")
  
    public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public Forecast humidity(Integer humidity) {
    this.humidity = humidity;
    return this;
  }

  /**
   * Get humidity
   * @return humidity
   **/
  @Schema(description = "")
  
    public Integer getHumidity() {
    return humidity;
  }

  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  public Forecast windSpeed(Double windSpeed) {
    this.windSpeed = windSpeed;
    return this;
  }

  /**
   * Get windSpeed
   * @return windSpeed
   **/
  @Schema(description = "")
  
    public Double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(Double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public Forecast clouds(Integer clouds) {
    this.clouds = clouds;
    return this;
  }

  /**
   * Get clouds
   * @return clouds
   **/
  @Schema(description = "")
  
    public Integer getClouds() {
    return clouds;
  }

  public void setClouds(Integer clouds) {
    this.clouds = clouds;
  }

  public Forecast temperature(ForecastTemperature temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * Get temperature
   * @return temperature
   **/
  @Schema(description = "")
  
    @Valid
    public ForecastTemperature getTemperature() {
    return temperature;
  }

  public void setTemperature(ForecastTemperature temperature) {
    this.temperature = temperature;
  }

  public Forecast weather(WeatherForecast weather) {
    this.weather = weather;
    return this;
  }

  /**
   * Get weather
   * @return weather
   **/
  @Schema(description = "")
  
    @Valid
    public WeatherForecast getWeather() {
    return weather;
  }

  public void setWeather(WeatherForecast weather) {
    this.weather = weather;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Forecast forecast = (Forecast) o;
    return Objects.equals(this.date, forecast.date) &&
        Objects.equals(this.pressure, forecast.pressure) &&
        Objects.equals(this.humidity, forecast.humidity) &&
        Objects.equals(this.windSpeed, forecast.windSpeed) &&
        Objects.equals(this.clouds, forecast.clouds) &&
        Objects.equals(this.temperature, forecast.temperature) &&
        Objects.equals(this.weather, forecast.weather);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, pressure, humidity, windSpeed, clouds, temperature, weather);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Forecast {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    pressure: ").append(toIndentedString(pressure)).append("\n");
    sb.append("    humidity: ").append(toIndentedString(humidity)).append("\n");
    sb.append("    windSpeed: ").append(toIndentedString(windSpeed)).append("\n");
    sb.append("    clouds: ").append(toIndentedString(clouds)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    weather: ").append(toIndentedString(weather)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
