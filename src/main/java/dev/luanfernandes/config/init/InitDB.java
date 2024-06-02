package dev.luanfernandes.config.init;

import dev.luanfernandes.domain.mapper.MovieMapper;
import dev.luanfernandes.repository.MovieRepository;
import dev.luanfernandes.service.CsvReadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class InitDB implements CommandLineRunner {

    private final CsvReadFileService readFileService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Value("${golden-raspberry-awards.csv.file}")
    private String filename;

    @Override
    public void run(String... args) {
        log.info("Reading file: {}", filename);
        log.info("Saving movies to database");
        movieRepository.saveAll(readFileService.readMoviesFromCSV(filename).stream()
                .map(movieMapper::map)
                .toList());
        log.info("Movies saved to database");
        log.info("Database initialized");
    }
}
