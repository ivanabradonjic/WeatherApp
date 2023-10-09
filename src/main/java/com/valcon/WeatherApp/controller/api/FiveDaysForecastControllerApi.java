package com.valcon.WeatherApp.controller.api;

import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;
import com.valcon.WeatherApp.dto.ErrorResponseDTO;
import com.valcon.WeatherApp.dto.TimeIntervalParametersDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface FiveDaysForecastControllerApi {


    @Operation(summary = "Find average temperature for city in time interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average temperature is calculated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CityAvgTempResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "City with this id not found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid time interval parameters",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    CityAvgTempResponseDTO averageTemperatureByCity(@Parameter(description = "Name of city to be used to calculate average temperature") String name, @Parameter(description = "Time interval parameters in format LocalDateTime") TimeIntervalParametersDTO timeIntervalParametersDTO);

    @Operation(summary = "Find average temperature for all cities in time interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average temperature is calculated",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CityAvgTempResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid time interval parameters",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    List<CityAvgTempResponseDTO> allCitiesAverageTemperatures(@Parameter(description = "Time interval parameters in format LocalDateTime") TimeIntervalParametersDTO timeIntervalParametersDTO);


}
