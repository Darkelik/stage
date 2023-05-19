package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * a single temperature zone
 */
@Schema(description = "a single temperature zone")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class TemperatureZone   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("inputPosition")
  private Integer inputPosition = null;

  @JsonProperty("outputPosition")
  private Integer outputPosition = null;

  @JsonProperty("zone")
  private String zone = null;

  public TemperatureZone id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * the unique identifier for the zone
   * @return id
   **/
  @Schema(required = true, description = "the unique identifier for the zone")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TemperatureZone name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TemperatureZone inputPosition(Integer inputPosition) {
    this.inputPosition = inputPosition;
    return this;
  }

  /**
   * Get inputPosition
   * @return inputPosition
   **/
  @Schema(description = "")
  
    public Integer getInputPosition() {
    return inputPosition;
  }

  public void setInputPosition(Integer inputPosition) {
    this.inputPosition = inputPosition;
  }

  public TemperatureZone outputPosition(Integer outputPosition) {
    this.outputPosition = outputPosition;
    return this;
  }

  /**
   * Get outputPosition
   * @return outputPosition
   **/
  @Schema(description = "")
  
    public Integer getOutputPosition() {
    return outputPosition;
  }

  public void setOutputPosition(Integer outputPosition) {
    this.outputPosition = outputPosition;
  }

  public TemperatureZone zone(String zone) {
    this.zone = zone;
    return this;
  }

  /**
   * Get zone
   * @return zone
   **/
  @Schema(description = "")
  
    public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemperatureZone temperatureZone = (TemperatureZone) o;
    return Objects.equals(this.id, temperatureZone.id) &&
        Objects.equals(this.name, temperatureZone.name) &&
        Objects.equals(this.inputPosition, temperatureZone.inputPosition) &&
        Objects.equals(this.outputPosition, temperatureZone.outputPosition) &&
        Objects.equals(this.zone, temperatureZone.zone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, inputPosition, outputPosition, zone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemperatureZone {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    inputPosition: ").append(toIndentedString(inputPosition)).append("\n");
    sb.append("    outputPosition: ").append(toIndentedString(outputPosition)).append("\n");
    sb.append("    zone: ").append(toIndentedString(zone)).append("\n");
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
