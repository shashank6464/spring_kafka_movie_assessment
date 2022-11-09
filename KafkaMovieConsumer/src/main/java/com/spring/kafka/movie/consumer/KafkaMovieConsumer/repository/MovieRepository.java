package com.spring.kafka.movie.consumer.KafkaMovieConsumer.repository;

import com.spring.kafka.movie.consumer.KafkaMovieConsumer.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

}