package com.smartcity.data_collector.service;

import com.smartcity.data_collector.model.SensorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SensorDataPublisherService {

    private final DataSimulatorService simulator;
    private final KafkaProducerService producer;
    @Value("${kafka.topic.sensor-data}")
    private String topic;

    public SensorDataPublisherService(DataSimulatorService simulator, KafkaProducerService producer) {
        this.simulator = simulator;
        this.producer = producer;
    }

    // 1) Schedulazione: esegui ogni 5 secondi
    @Scheduled(fixedRateString = "${simulator.publish-interval-ms:5000}")
    public void publishSensorData() {
        SensorData data = simulator.generateRandomData();
        producer.sendSensorData(topic, data);
    }
}