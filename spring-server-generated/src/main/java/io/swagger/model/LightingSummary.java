package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.LightingZone;
import io.swagger.model.LightingZoneStatus;
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


public class LightingSummary   {
  @JsonProperty("zones")
  @Valid
  private List<LightingZone> zones = null;

  @JsonProperty("zoneStatus")
  @Valid
  private List<LightingZoneStatus> zoneStatus = null;

  public LightingSummary zones(List<LightingZone> zones) {
    this.zones = zones;
    return this;
  }

  public LightingSummary addZonesItem(LightingZone zonesItem) {
    if (this.zones == null) {
      this.zones = new ArrayList<LightingZone>();
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
    public List<LightingZone> getZones() {
    return zones;
  }

  public void setZones(List<LightingZone> zones) {
    this.zones = zones;
  }

  public LightingSummary zoneStatus(List<LightingZoneStatus> zoneStatus) {
    this.zoneStatus = zoneStatus;
    return this;
  }

  public LightingSummary addZoneStatusItem(LightingZoneStatus zoneStatusItem) {
    if (this.zoneStatus == null) {
      this.zoneStatus = new ArrayList<LightingZoneStatus>();
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
    public List<LightingZoneStatus> getZoneStatus() {
    return zoneStatus;
  }

  public void setZoneStatus(List<LightingZoneStatus> zoneStatus) {
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
    LightingSummary lightingSummary = (LightingSummary) o;
    return Objects.equals(this.zones, lightingSummary.zones) &&
        Objects.equals(this.zoneStatus, lightingSummary.zoneStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zones, zoneStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LightingSummary {\n");
    
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
