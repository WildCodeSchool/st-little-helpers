package dev.wcs.tutoring.littlehelpers.csv;

import dev.wcs.tutoring.littlehelpers.pojo.City;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CsvReader {

    private String[] HEADERS = {"city", "population", "founded", "capital"};

    public void readFileAsCsv(File csvInput) throws IOException {
        Reader in = new FileReader(csvInput);
        CSVFormat csvFormat =
            CSVFormat.Builder.create()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        Iterable<CSVRecord> records = csvFormat.parse(in);
        for (CSVRecord record : records) {
            City city =
                City.builder()
                    .capital(Boolean.valueOf(record.get("capital")))
                    .founded(LocalDate.parse(record.get("founded"), DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .name(record.get("city"))
                    .population(Integer.parseInt(record.get("population"))).build();
            System.out.println(city);
        }
    }

    public static void main(String[] args) throws IOException {
        new CsvReader().readFileAsCsv(new File("src/test/resources/cities.csv"));
    }
}
