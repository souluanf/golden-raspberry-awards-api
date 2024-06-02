package dev.luanfernandes.domain.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ProducerAwardIntervalResponse(List<ProducerResponse> min, List<ProducerResponse> max) {}
