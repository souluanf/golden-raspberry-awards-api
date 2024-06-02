package dev.luanfernandes.service;

import dev.luanfernandes.domain.dto.MovieCSV;
import java.util.List;

public interface CsvReadFileService {
    List<MovieCSV> readMoviesFromCSV(String fileName);
}
