package com.smartcity.data_collector.service;

import com.smartcity.common.model.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.topic.sensor-data}")
    public void consumeSensorData(SensorData data) {
        log.info("Ricevuto: {}", data);
        // salva in DB, invia ad un altro microservizio, aggiorna metriche…
    }
}
