package dev.luanfernandes.domain.response;

import lombok.Builder;

@Builder
public record ProducerResponse(String producer, int interval, int previousWin, int followingWin) {}
