package dev.luanfernandes.domain.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCSV {
    @CsvBindByName(column = "title")
    String title;

    @CsvBindByName(column = "producers")
    String producer;

    @CsvBindByName(column = "studios")
    String studio;

    @CsvBindByName(column = "year")
    int year;

    @CsvBindByName(column = "winner")
    boolean winner;
}
