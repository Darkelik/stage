package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.TemperatueZoneStatus;
import io.swagger.model.TemperatureZone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ok
 */
@Schema(description = "ok")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")


public class TemperatureSummary   {
  @JsonProperty("zones")
  @Valid
  private List<TemperatureZone> zones = null;

  @JsonProperty("zoneStatus")
  @Valid
  private List<TemperatueZoneStatus> zoneStatus = null;

  public TemperatureSummary zones(List<TemperatureZone> zones) {
    this.zones = zones;
    return this;
  }

  public TemperatureSummary addZonesItem(TemperatureZone zonesItem) {
    if (this.zones == null) {
      this.zones = new ArrayList<TemperatureZone>();
    }
    this.zones.add(zonesItem);
    return this;
  }

  /**
   * Get zones
   * @return zones
   **/
  @Schema(description = "")
      @Valid
    public List<TemperatureZone> getZones() {
    return zones;
  }

  public void setZones(List<TemperatureZone> zones) {
    this.zones = zones;
  }

  public TemperatureSummary zoneStatus(List<TemperatueZoneStatus> zoneStatus) {
    this.zoneStatus = zoneStatus;
    return this;
  }

  public TemperatureSummary addZoneStatusItem(TemperatueZoneStatus zoneStatusItem) {
    if (this.zoneStatus == null) {
      this.zoneStatus = new ArrayList<TemperatueZoneStatus>();
    }
    this.zoneStatus.add(zoneStatusItem);
    return this;
  }

  /**
   * Get zoneStatus
   * @return zoneStatus
   **/
  @Schema(description = "")
      @Valid
    public List<TemperatueZoneStatus> getZoneStatus() {
    return zoneStatus;
  }

  public void setZoneStatus(List<TemperatueZoneStatus> zoneStatus) {
    this.zoneStatus = zoneStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemperatureSummary temperatureSummary = (TemperatureSummary) o;
    return Objects.equals(this.zones, temperatureSummary.zones) &&
        Objects.equals(this.zoneStatus, temperatureSummary.zoneStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zones, zoneStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemperatureSummary {\n");
    
    sb.append("    zones: ").append(toIndentedString(zones)).append("\n");
    sb.append("    zoneStatus: ").append(toIndentedString(zoneStatus)).append("\n");
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
