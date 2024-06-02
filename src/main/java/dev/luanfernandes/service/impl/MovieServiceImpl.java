package dev.luanfernandes.service.impl;

import dev.luanfernandes.domain.entity.Movie;
import dev.luanfernandes.domain.response.ProducerAwardIntervalResponse;
import dev.luanfernandes.domain.response.ProducerResponse;
import dev.luanfernandes.repository.MovieRepository;
import dev.luanfernandes.service.MovieService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public ProducerAwardIntervalResponse getProducersWithAwardIntervals() {
        List<Movie> winners = movieRepository.findByWinnerIsTrue();
        Map<String, List<Integer>> producerWins = processWinners(winners);
        List<ProducerResponse> minIntervals = new ArrayList<>();
        List<ProducerResponse> maxIntervals = new ArrayList<>();
        calculateIntervals(producerWins, minIntervals, maxIntervals);
        return ProducerAwardIntervalResponse.builder()
                .min(minIntervals)
                .max(maxIntervals)
                .build();
    }

    private Map<String, List<Integer>> processWinners(List<Movie> winners) {
        Map<String, List<Integer>> producerWins = new HashMap<>();
        for (Movie winner : winners) {
            String[] producers = winner.getProducer().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                producerWins.putIfAbsent(producer, new ArrayList<>());
                producerWins.get(producer).add(winner.getAwardYear());
            }
        }
        return producerWins;
    }

    private void calculateIntervals(
            Map<String, List<Integer>> producerWins,
            List<ProducerResponse> minIntervals,
            List<ProducerResponse> maxIntervals) {
        Integer minInterval = null;
        Integer maxInterval = null;
        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                ProducerResponse intervalData = ProducerResponse.builder()
                        .producer(entry.getKey())
                        .interval(interval)
                        .previousWin(years.get(i - 1))
                        .followingWin(years.get(i))
                        .build();
                if (minInterval == null || interval < minInterval) {
                    minInterval = interval;
                    minIntervals.clear();
                    minIntervals.add(intervalData);
                } else if (interval == minInterval) {
                    minIntervals.add(intervalData);
                }
                if (maxInterval == null || interval > maxInterval) {
                    maxInterval = interval;
                    maxIntervals.clear();
                    maxIntervals.add(intervalData);
                } else if (interval == maxInterval) {
                    maxIntervals.add(intervalData);
                }
            }
        }
    }
}
