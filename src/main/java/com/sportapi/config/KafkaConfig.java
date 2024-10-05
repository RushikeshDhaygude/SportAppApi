package com.sportapi.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "scorecard-group");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Configure ErrorHandlingDeserializer for key and value
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        // Configure delegate deserializers for key and value
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName());
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.sportapi.model.ScoreCard");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(configProps);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @Bean
    public NewTopic topic() {
        return new NewTopic("score-updates", 1, (short) 1);
    }
}
//package com.sportapi.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.config.SslConfigs;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConfig {
//
//    private static final String BOOTSTRAP_SERVERS = "kafka-3ed35f68-rdopt9414-73ee.e.aivencloud.com:21878";
//    private static final String GROUP_ID = "scorecard-group";
//    private static final String TRUSTSTORE_PASSWORD = "Rushi@9414";
//    private static final String KEYSTORE_PASSWORD = "Rushi@9414";
//    private static final String KEY_PASSWORD = "Rushi@9414";
//    private static final String TRUSTSTORE_PATH = "client.truststore.jks";
//    private static final String KEYSTORE_PATH = "client.keystore.p12";
//
//    private String copyResourceToTempFile(String resourcePath) throws IOException {
//        ClassPathResource resource = new ClassPathResource(resourcePath);
//        File tempFile = File.createTempFile("kafka-", "-" + resource.getFilename());
//        tempFile.deleteOnExit();
//
//        try (InputStream inputStream = resource.getInputStream();
//             FileOutputStream outputStream = new FileOutputStream(tempFile)) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//        }
//
//        return tempFile.getAbsolutePath();
//    }
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() throws IOException {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Use JsonSerializer for value
//
//        configProps.put("security.protocol", "SSL");
//        configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, copyResourceToTempFile(TRUSTSTORE_PATH));
//        configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, copyResourceToTempFile(KEYSTORE_PATH));
//        configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUSTSTORE_PASSWORD);
//        configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, KEYSTORE_PASSWORD);
//        configProps.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, KEY_PASSWORD);
//
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() throws IOException {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() throws IOException {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        configProps.put("security.protocol", "SSL");
//        configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, copyResourceToTempFile(TRUSTSTORE_PATH));
//        configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, copyResourceToTempFile(KEYSTORE_PATH));
//        configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUSTSTORE_PASSWORD);
//        configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, KEYSTORE_PASSWORD);
//        configProps.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, KEY_PASSWORD);
//
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
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
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() throws IOException {
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
