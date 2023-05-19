package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ForecastTemperature
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class ForecastTemperature   {
  @JsonProperty("low")
  private Double low = null;

  @JsonProperty("high")
  private Double high = null;

  @JsonProperty("morning")
  private Double morning = null;

  @JsonProperty("day")
  private Double day = null;

  @JsonProperty("evening")
  private Double evening = null;

  @JsonProperty("night")
  private Double night = null;

  public ForecastTemperature low(Double low) {
    this.low = low;
    return this;
  }

  /**
   * Get low
   * @return low
   **/
  @Schema(description = "")
  
    public Double getLow() {
    return low;
  }

  public void setLow(Double low) {
    this.low = low;
  }

  public ForecastTemperature high(Double high) {
    this.high = high;
    return this;
  }

  /**
   * Get high
   * @return high
   **/
  @Schema(description = "")
  
    public Double getHigh() {
    return high;
  }

  public void setHigh(Double high) {
    this.high = high;
  }

  public ForecastTemperature morning(Double morning) {
    this.morning = morning;
    return this;
  }

  /**
   * Get morning
   * @return morning
   **/
  @Schema(description = "")
  
    public Double getMorning() {
    return morning;
  }

  public void setMorning(Double morning) {
    this.morning = morning;
  }

  public ForecastTemperature day(Double day) {
    this.day = day;
    return this;
  }

  /**
   * Get day
   * @return day
   **/
  @Schema(description = "")
  
    public Double getDay() {
    return day;
  }

  public void setDay(Double day) {
    this.day = day;
  }

  public ForecastTemperature evening(Double evening) {
    this.evening = evening;
    return this;
  }

  /**
   * Get evening
   * @return evening
   **/
  @Schema(description = "")
  
    public Double getEvening() {
    return evening;
  }

  public void setEvening(Double evening) {
    this.evening = evening;
  }

  public ForecastTemperature night(Double night) {
    this.night = night;
    return this;
  }

  /**
   * Get night
   * @return night
   **/
  @Schema(description = "")
  
    public Double getNight() {
    return night;
  }

  public void setNight(Double night) {
    this.night = night;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForecastTemperature forecastTemperature = (ForecastTemperature) o;
    return Objects.equals(this.low, forecastTemperature.low) &&
        Objects.equals(this.high, forecastTemperature.high) &&
        Objects.equals(this.morning, forecastTemperature.morning) &&
        Objects.equals(this.day, forecastTemperature.day) &&
        Objects.equals(this.evening, forecastTemperature.evening) &&
        Objects.equals(this.night, forecastTemperature.night);
  }

  @Override
  public int hashCode() {
    return Objects.hash(low, high, morning, day, evening, night);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForecastTemperature {\n");
    
    sb.append("    low: ").append(toIndentedString(low)).append("\n");
    sb.append("    high: ").append(toIndentedString(high)).append("\n");
    sb.append("    morning: ").append(toIndentedString(morning)).append("\n");
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
    sb.append("    evening: ").append(toIndentedString(evening)).append("\n");
    sb.append("    night: ").append(toIndentedString(night)).append("\n");
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
