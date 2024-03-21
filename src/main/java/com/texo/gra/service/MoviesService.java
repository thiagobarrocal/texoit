package com.texo.gra.service;

import com.texo.gra.dto.AwardIntervalsDTO;
import com.texo.gra.dto.ProducerIntervalDTO;
import com.texo.gra.model.Movie;
import com.texo.gra.repository.MovieRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class MoviesService {

    private final MovieRepository movieRepository;

    public AwardIntervalsDTO calculateRangeAwards() {
        var movies = movieRepository.findAll();
        log.info("Found {} movies", movies.size());

        // Agrupar filmes por produtor
        Map<String, List<Movie>> moviesByProducer = movies.stream()
                .collect(Collectors.groupingBy(Movie::getProducers));

        // Calcular intervalos para cada produtor
        Map<String, List<ProducerIntervalDTO>> intervalsByProducer = new HashMap<>();
        for (Map.Entry<String, List<Movie>> entry : moviesByProducer.entrySet()) {
            List<Movie> producerMovies = entry.getValue();
            List<ProducerIntervalDTO> intervals = calculateProducerIntervals(producerMovies);
            intervalsByProducer.put(entry.getKey(), intervals);
        }

        // Encontrar o produtor com maior e menor intervalo
        List<ProducerIntervalDTO> allIntervals = intervalsByProducer.values().stream()
                .flatMap(List::stream)
                .toList();

        List<ProducerIntervalDTO> maxIntervals = allIntervals.stream()
                .sorted(Comparator.comparingInt(ProducerIntervalDTO::getInterval).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<ProducerIntervalDTO> minIntervals = allIntervals.stream()
                .sorted(Comparator.comparingInt(ProducerIntervalDTO::getInterval))
                .limit(2)
                .collect(Collectors.toList());

        return AwardIntervalsDTO.builder()
                .min(minIntervals)
                .max(maxIntervals)
                .build();
    }

    private List<ProducerIntervalDTO> calculateProducerIntervals(List<Movie> movies) {
        List<ProducerIntervalDTO> intervals = new ArrayList<>();

        for (int i = 0; i < movies.size() - 1; i++) {
            Movie currentMovie = movies.get(i);
            Movie nextMovie = movies.get(i + 1);

            int interval = nextMovie.getYear() - currentMovie.getYear();
            ProducerIntervalDTO producerInterval = ProducerIntervalDTO.builder()
                    .producer(currentMovie.getProducers())
                    .interval(interval)
                    .previousWin(currentMovie.getYear())
                    .followingWin(nextMovie.getYear())
                    .build();
            intervals.add(producerInterval);
        }

        return intervals;
    }
}
