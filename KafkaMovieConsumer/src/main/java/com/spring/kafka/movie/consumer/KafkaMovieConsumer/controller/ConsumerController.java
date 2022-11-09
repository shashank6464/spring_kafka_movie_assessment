package com.spring.kafka.movie.consumer.KafkaMovieConsumer.controller;

import com.spring.kafka.movie.consumer.KafkaMovieConsumer.model.Movie;
import com.spring.kafka.movie.consumer.KafkaMovieConsumer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> getAllMovie(){
        return service.getAllMovies();
    }
}