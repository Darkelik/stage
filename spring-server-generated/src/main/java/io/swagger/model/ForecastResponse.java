package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.City;
import io.swagger.model.Forecast;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ForecastResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class ForecastResponse   {
  @JsonProperty("city")
  private City city = null;

  @JsonProperty("values")
  @Valid
  private List<Forecast> values = null;

  public ForecastResponse city(City city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
   **/
  @Schema(description = "")
  
    @Valid
    public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public ForecastResponse values(List<Forecast> values) {
    this.values = values;
    return this;
  }

  public ForecastResponse addValuesItem(Forecast valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<Forecast>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * Get values
   * @return values
   **/
  @Schema(description = "")
      @Valid
    public List<Forecast> getValues() {
    return values;
  }

  public void setValues(List<Forecast> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForecastResponse forecastResponse = (ForecastResponse) o;
    return Objects.equals(this.city, forecastResponse.city) &&
        Objects.equals(this.values, forecastResponse.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForecastResponse {\n");
    
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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
