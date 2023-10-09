package com.valcon.WeatherApp.controller.api;
import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface CityControllerApi {

    @Operation(summary = "Find all cities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cities found.",
                    content = { @Content(mediaType = "application/json")})
    })
    List<CityResponseDTO> getAll();

    @Operation(summary = "Find city by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City with requested id found.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CityResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "City with this id not found.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    CityResponseDTO getById(@Parameter(description = "Id of city to be found") Long id);



}