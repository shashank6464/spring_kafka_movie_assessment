package com.spring.kafka.movie.consumer.KafkaMovieConsumer.service;

import com.spring.kafka.movie.consumer.KafkaMovieConsumer.model.Movie;
import com.spring.kafka.movie.consumer.KafkaMovieConsumer.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository repository;

    public List<Movie> getAllMovies(){
        return repository.findAll();
    }

}