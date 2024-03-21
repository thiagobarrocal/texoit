package com.texo.gra.config;

import com.texo.gra.model.Movie;
import com.texo.gra.repository.MovieRepository;
import com.texo.gra.util.Checkers;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MovieDataInitializer {

    private static final String fileName = "datafile/movielist.csv";

    private final MovieRepository movieRepository;

    @Autowired
    public MovieDataInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        List<Movie> movies = readCsvFile();
        movieRepository.saveAll(movies);
        log.info("Saved all movies from file in database: [ count '{}'] rows", movies.size());
    }

    public List<Movie> readCsvFile() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Checkers.mustNotBeNull(is, "File not found");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines()
                    .skip(1)
                    .map(Movie::fromCsv)
                    .collect(Collectors.toList());
        }
    }
}
