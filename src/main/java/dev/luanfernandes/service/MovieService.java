package dev.luanfernandes.service;

import dev.luanfernandes.domain.response.ProducerAwardIntervalResponse;

public interface MovieService {
    ProducerAwardIntervalResponse getProducersWithAwardIntervals();
}
