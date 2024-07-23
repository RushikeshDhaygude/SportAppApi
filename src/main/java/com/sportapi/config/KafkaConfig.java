//package com.sportapi.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConfig {
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-3ed35f68-rdopt9414-73ee.e.aivencloud.com:21878");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-3ed35f68-rdopt9414-73ee.e.aivencloud.com:21878");
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "scorecard-group");
//        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        // Configure ErrorHandlingDeserializer for key and value
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
//
//        // Configure delegate deserializers for key and value
//        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName());
//
//        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.sportapi.model.ScoreCard");
//        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//
//        return new DefaultKafkaConsumerFactory<>(configProps);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public NewTopic topic() {
//        return new NewTopic("score-updates", 1, (short) 1);
//    }
//}
package com.sportapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {


    @Bean
    public ProducerFactory<String, Object> producerFactory() throws IOException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-3ed35f68-rdopt9414-73ee.e.aivencloud.com:21878");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        ClassPathResource truststoreResource = new ClassPathResource("client.truststore.jks");
        File truststoreFile = truststoreResource.getFile();


        // Load the keystore file from classpath
        ClassPathResource keystoreResource = new ClassPathResource("client.keystore.p12");
        File keystoreFile = keystoreResource.getFile();

        // SSL Configuration
        configProps.put("security.protocol", "SSL");
        configProps.put("ssl.truststore.location",  truststoreFile.getAbsolutePath());
        configProps.put("ssl.truststore.password", "Rushi@9414");
        configProps.put("ssl.keystore.location", keystoreFile.getAbsolutePath());
        configProps.put("ssl.keystore.password", "Rushi@9414");
        configProps.put("ssl.key.password", "Rushi@9414");

        // SASL Configuration
//        configProps.put("sasl.mechanism", "PLAIN");
//        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"rdopt9414@gmail.com\" password=\"Rushi@9414\";");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() throws IOException {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() throws IOException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-3ed35f68-rdopt9414-73ee.e.aivencloud.com:21878");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "scorecard-group");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ClassPathResource truststoreResource = new ClassPathResource("client.truststore.jks");
        File truststoreFile = truststoreResource.getFile();


        // Load the keystore file from classpath
        ClassPathResource keystoreResource = new ClassPathResource("client.keystore.p12");
        File keystoreFile = keystoreResource.getFile();



        // SSL Configuration
        configProps.put("security.protocol", "SSL");
        configProps.put("ssl.truststore.location",  truststoreFile.getAbsolutePath());
        configProps.put("ssl.truststore.password", "Rushi@9414");
        configProps.put("ssl.keystore.location",  keystoreFile.getAbsolutePath());
        configProps.put("ssl.keystore.password", "Rushi@9414");
        configProps.put("ssl.key.password", "Rushi@9414");

        // SASL Configuration
//        configProps.put("sasl.mechanism", "PLAIN");
//        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"rdopt9414@gmail.com\" password=\"Rushi@9414\";");

        // Error Handling Deserializer
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName());

        // Json Deserializer Settings
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.sportapi.model.ScoreCard");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() throws IOException {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("score-updates", 1, (short) 1);
    }
}
