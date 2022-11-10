package com.spring.kafka.movie.producer.KafkaMovieProducer.service;

import com.spring.kafka.movie.producer.KafkaMovieProducer.model.Movie;
import com.spring.kafka.movie.producer.KafkaMovieProducer.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository repository;

    //to get all movies
    public List<Movie> getAllMovies(){
        return repository.findAll();
    }

    //for adding movie
    public void addMovie(Movie movie){ repository.save(movie);}

}