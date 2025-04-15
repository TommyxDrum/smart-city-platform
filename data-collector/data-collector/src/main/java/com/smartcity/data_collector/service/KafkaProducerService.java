package com.smartcity.data_collector.service;

import com.smartcity.data_collector.model.SensorData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, SensorData> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, SensorData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSensorData(String topic, SensorData data) {
        kafkaTemplate.send(topic, data);
    }
}
