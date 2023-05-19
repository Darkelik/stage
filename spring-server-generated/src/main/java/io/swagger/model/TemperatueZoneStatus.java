package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * status of a single zone
 */
@Schema(description = "status of a single zone")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class TemperatueZoneStatus   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("value")
  private Double value = null;

  /**
   * the temperature units
   */
  public enum UnitsEnum {
    CELSIUS("celsius"),
    
    FAHRENHEIT("fahrenheit");

    private String value;

    UnitsEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UnitsEnum fromValue(String text) {
      for (UnitsEnum b : UnitsEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("units")
  private UnitsEnum units = UnitsEnum.FAHRENHEIT;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  public TemperatueZoneStatus id(String id) {
    this.id = id;
    return this;
  }

  /**
   * the unique identifier for the zone
   * @return id
   **/
  @Schema(required = true, description = "the unique identifier for the zone")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TemperatueZoneStatus name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the name of the zone
   * @return name
   **/
  @Schema(description = "the name of the zone")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TemperatueZoneStatus value(Double value) {
    this.value = value;
    return this;
  }

  /**
   * the temperature in the zone
   * @return value
   **/
  @Schema(required = true, description = "the temperature in the zone")
      @NotNull

    public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public TemperatueZoneStatus units(UnitsEnum units) {
    this.units = units;
    return this;
  }

  /**
   * the temperature units
   * @return units
   **/
  @Schema(description = "the temperature units")
  
    public UnitsEnum getUnits() {
    return units;
  }

  public void setUnits(UnitsEnum units) {
    this.units = units;
  }

  public TemperatueZoneStatus timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * the timestamp when the temperature was measured
   * @return timestamp
   **/
  @Schema(required = true, description = "the timestamp when the temperature was measured")
      @NotNull

    @Valid
    public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemperatueZoneStatus temperatueZoneStatus = (TemperatueZoneStatus) o;
    return Objects.equals(this.id, temperatueZoneStatus.id) &&
        Objects.equals(this.name, temperatueZoneStatus.name) &&
        Objects.equals(this.value, temperatueZoneStatus.value) &&
        Objects.equals(this.units, temperatueZoneStatus.units) &&
        Objects.equals(this.timestamp, temperatueZoneStatus.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, value, units, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemperatueZoneStatus {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
