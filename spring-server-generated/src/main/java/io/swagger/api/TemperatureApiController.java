package io.swagger.api;

import io.swagger.model.ForecastResponse;
import io.swagger.model.HeaterState;
import io.swagger.model.ModelApiResponse;
import io.swagger.model.TemperatueZoneStatus;
import io.swagger.model.TemperatureSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-19T08:00:08.890229128Z[GMT]")
@RestController
public class TemperatureApiController implements TemperatureApi {

    private static final Logger log = LoggerFactory.getLogger(TemperatureApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TemperatureApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ForecastResponse> getForecast(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("days") Integer days) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ForecastResponse>(objectMapper.readValue("{\n  \"city\" : {\n    \"country\" : \"country\",\n    \"name\" : \"name\",\n    \"lon\" : 1.4658129805029452,\n    \"id\" : 0,\n    \"lat\" : 6.027456183070403\n  },\n  \"values\" : [ {\n    \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"temperature\" : {\n      \"high\" : 3.616076749251911,\n      \"low\" : 9.301444243932576,\n      \"night\" : 1.2315135367772556,\n      \"evening\" : 7.386281948385884,\n      \"day\" : 4.145608029883936,\n      \"morning\" : 2.027123023002322\n    },\n    \"weather\" : {\n      \"summary\" : \"summary\",\n      \"icon\" : \"icon\",\n      \"description\" : \"description\"\n    },\n    \"humidity\" : 5,\n    \"pressure\" : 5.962133916683182,\n    \"clouds\" : 7,\n    \"windSpeed\" : 2.3021358869347655\n  }, {\n    \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"temperature\" : {\n      \"high\" : 3.616076749251911,\n      \"low\" : 9.301444243932576,\n      \"night\" : 1.2315135367772556,\n      \"evening\" : 7.386281948385884,\n      \"day\" : 4.145608029883936,\n      \"morning\" : 2.027123023002322\n    },\n    \"weather\" : {\n      \"summary\" : \"summary\",\n      \"icon\" : \"icon\",\n      \"description\" : \"description\"\n    },\n    \"humidity\" : 5,\n    \"pressure\" : 5.962133916683182,\n    \"clouds\" : 7,\n    \"windSpeed\" : 2.3021358869347655\n  } ]\n}", ForecastResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ForecastResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ForecastResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<HeaterState> getHeaterState(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("zoneId") String zoneId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<HeaterState>(objectMapper.readValue("{\n  \"id\" : \"id\",\n  \"state\" : \"state\"\n}", HeaterState.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<HeaterState>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HeaterState>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TemperatueZoneStatus> getZoneTemperature(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("zoneId") String zoneId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TemperatueZoneStatus>(objectMapper.readValue("{\n  \"name\" : \"name\",\n  \"id\" : \"id\",\n  \"units\" : \"fahrenheit\",\n  \"value\" : 5.962133916683182,\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}", TemperatueZoneStatus.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TemperatueZoneStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TemperatueZoneStatus>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModelApiResponse> setHeaterState(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("zoneId") String zoneId,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={ "false", "true" }
)) @PathVariable("state") String state) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModelApiResponse>(objectMapper.readValue("{\n  \"code\" : 0,\n  \"message\" : \"everything is ok\"\n}", ModelApiResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModelApiResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TemperatureSummary> temperatureSummary() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TemperatureSummary>(objectMapper.readValue("{\n  \"zoneStatus\" : [ {\n    \"name\" : \"name\",\n    \"id\" : \"id\",\n    \"units\" : \"fahrenheit\",\n    \"value\" : 5.962133916683182,\n    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n  }, {\n    \"name\" : \"name\",\n    \"id\" : \"id\",\n    \"units\" : \"fahrenheit\",\n    \"value\" : 5.962133916683182,\n    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n  } ],\n  \"zones\" : [ {\n    \"inputPosition\" : 6,\n    \"outputPosition\" : 1,\n    \"zone\" : \"zone\",\n    \"name\" : \"name\",\n    \"id\" : 0\n  }, {\n    \"inputPosition\" : 6,\n    \"outputPosition\" : 1,\n    \"zone\" : \"zone\",\n    \"name\" : \"name\",\n    \"id\" : 0\n  } ]\n}", TemperatureSummary.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TemperatureSummary>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TemperatureSummary>(HttpStatus.NOT_IMPLEMENTED);
    }

}
