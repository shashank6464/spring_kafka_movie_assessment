package com.spring.kafka.movie.consumer.KafkaMovieConsumer.listener;

import com.spring.kafka.movie.consumer.KafkaMovieConsumer.constant.KafkaConstant;
import com.spring.kafka.movie.consumer.KafkaMovieConsumer.model.Movie;
import com.spring.kafka.movie.consumer.KafkaMovieConsumer.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    MovieRepository repository;

    @KafkaListener(topics = KafkaConstant.TOPIC, groupId = KafkaConstant.GROUP_ID)
    public void consume(Movie movie){
        System.out.println(movie);
        repository.save(movie);
    }

}