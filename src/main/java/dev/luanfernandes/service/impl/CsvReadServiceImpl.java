package dev.luanfernandes.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.luanfernandes.config.exception.CSVParserException;
import dev.luanfernandes.domain.dto.MovieCSV;
import dev.luanfernandes.service.CsvReadFileService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CsvReadServiceImpl implements CsvReadFileService {

    public List<MovieCSV> readMoviesFromCSV(String filename) {
        try (Reader reader = getReader(filename)) {
            CsvToBean<MovieCSV> csvToBean = new CsvToBeanBuilder<MovieCSV>(reader)
                    .withType(MovieCSV.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

        } catch (IOException e) {
            throw new CSVParserException("Error reading CSV file", e);
        }
    }

    private static InputStreamReader getReader(String filename) throws IOException {
        if (Files.exists(Path.of(filename))) {
            return new InputStreamReader(new FileInputStream(filename));
        }
        return new InputStreamReader(new ClassPathResource(filename).getInputStream());
    }
}
