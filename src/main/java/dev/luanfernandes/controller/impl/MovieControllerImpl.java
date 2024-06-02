package dev.luanfernandes.controller.impl;

import static org.springframework.http.ResponseEntity.ok;

import dev.luanfernandes.controller.MovieController;
import dev.luanfernandes.domain.response.ProducerAwardIntervalResponse;
import dev.luanfernandes.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieControllerImpl implements MovieController {

    private final MovieService movieService;

    @Override
    public ResponseEntity<ProducerAwardIntervalResponse> getProducersWithAwardIntervals() {
        return ok(movieService.getProducersWithAwardIntervals());
    }
}
