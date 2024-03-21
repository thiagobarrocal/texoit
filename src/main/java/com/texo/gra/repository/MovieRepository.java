package com.texo.gra.repository;

import com.texo.gra.model.Movie;
import lombok.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    String CACHE_NAME = "movies";

    @NonNull
    @Cacheable(value = CACHE_NAME, unless = "#result == null")
    @Override
    List<Movie> findAll();
}
