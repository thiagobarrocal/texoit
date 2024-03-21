package com.texo.gra.controller;

import com.texo.gra.dto.AwardIntervalsDTO;
import com.texo.gra.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@ApiResponse(description = "Endpoints to access movies information", responseCode = "200")
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping("/winners")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Golden Raspberry Awards [worst category]", description = "Get list of winners from  worst film of the year")
    public ResponseEntity<AwardIntervalsDTO> getWinners() {
        var winners = moviesService.calculateRangeAwards();
        return ResponseEntity.ok(winners);
    }
}
