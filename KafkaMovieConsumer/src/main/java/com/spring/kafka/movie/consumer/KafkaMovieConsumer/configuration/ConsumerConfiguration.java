package com.spring.kafka.movie.consumer.KafkaMovieConsumer.configuration;

import com.spring.kafka.movie.consumer.KafkaMovieConsumer.constant.KafkaConstant;
import com.spring.kafka.movie.consumer.KafkaMovieConsumer.model.Movie;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {
    @Bean
    public Map<String, Object> consumerConfig( ){
//        public static final String GROUP_ID = "messenger_SandBox";
//        public static final String BROKER = "localhost:9092"; // KAFKA BROKER
        Map<String,Object> config = new HashMap<>();
        // Assign ConsumerConfig Bootstrap_Server a up running kafka url.
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BROKER); // Kafka Server Url
        config.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstant.GROUP_ID); // Group the data into one category/particular consumer service (unique session).
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Decode Data for key
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // Decode Data for Value
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest"); // always pick latest from queue.
        return config;
    }

    @Bean
    public ConsumerFactory<String, Movie> consumerFactory(){
        JsonDeserializer<Movie> deserializer = new JsonDeserializer<>(Movie.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),// method for creating "Producer Instances" on Kafka Broker
                new StringDeserializer(),
                deserializer
        );
    }



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Movie> kafkaListenerContainerFactory(){
        // Concurrent Message to kafka Listener Factory
        ConcurrentKafkaListenerContainerFactory<String, Movie> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }




}
