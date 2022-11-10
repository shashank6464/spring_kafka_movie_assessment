package com.spring.kafka.movie.producer.KafkaMovieProducer.controller;

import com.spring.kafka.movie.producer.KafkaMovieProducer.constant.KafkaConstant;
import com.spring.kafka.movie.producer.KafkaMovieProducer.model.Movie;
import com.spring.kafka.movie.producer.KafkaMovieProducer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private KafkaTemplate<String, Movie> kafkaTemplate;

    @Autowired
    private MovieService service;

    //add movie
    @PostMapping(value = "/add",consumes = "application/json",produces = "application/json")
    public void send(@RequestBody Movie movie){
        System.out.println(movie);
        service.addMovie(movie);
        movie.setCreationDate(LocalDateTime.now().toString());
        try{
            kafkaTemplate.send(KafkaConstant.TOPIC,movie).get();
        }
        catch (InterruptedException | ExecutionException exception){
            throw new RuntimeException(exception);
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }

    //get all movies
    @GetMapping
    public List<Movie> getAllMovie(){
        return service.getAllMovies();
    }
}