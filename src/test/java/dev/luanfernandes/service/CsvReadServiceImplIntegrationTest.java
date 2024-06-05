package dev.luanfernandes.service;

import dev.luanfernandes.config.exception.CSVParserException;
import dev.luanfernandes.domain.dto.MovieCSV;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class CsvReadServiceImplIntegrationTest {

    @Autowired
    private CsvReadFileService csvReadFileService;

    @Test
    void testReadMoviesFromCSV() {
        String filename = "csv/movielist.csv";
        List<MovieCSV> movies = csvReadFileService.readMoviesFromCSV(filename);

        assertThat(movies).isNotEmpty();
        for (MovieCSV movie : movies) {
            assertThat(movie.getYear()).isInstanceOf(Integer.class);
            assertThat(movie.getTitle()).isInstanceOf(String.class).isNotEmpty();
            assertThat(movie.getStudio()).isInstanceOf(String.class).isNotEmpty();
            assertThat(movie.getProducer()).isInstanceOf(String.class).isNotEmpty();
            assertThat(movie.isWinner()).isInstanceOf(Boolean.class);
        }

        MovieCSV firstMovie = movies.getFirst();
        assertThat(firstMovie.getTitle()).isEqualTo("Can't Stop the Music");
        assertThat(firstMovie.getProducer()).isEqualTo("Allan Carr");
        assertThat(firstMovie.getStudio()).isEqualTo("Associated Film Distribution");
        assertThat(firstMovie.getYear()).isEqualTo(1980);
        assertThat(firstMovie.isWinner()).isTrue();
    }

    @Test
    void testReadMoviesFromCSVFileNotFound() {
        String invalidPath = "/invalid-path.csv";
        assertThatThrownBy(() -> csvReadFileService.readMoviesFromCSV(invalidPath))
                .isInstanceOf(CSVParserException.class)
                .hasMessageContaining("Error reading CSV file");
    }
}
