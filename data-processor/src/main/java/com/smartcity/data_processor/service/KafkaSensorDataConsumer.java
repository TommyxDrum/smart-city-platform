package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSensorDataConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaSensorDataConsumer.class);

    private final SensorDataProcessorService processingService;

    public KafkaSensorDataConsumer(SensorDataProcessorService processingService) {
        this.processingService = processingService;
    }

    @KafkaListener(topics = "${kafka.topic.sensor-data}")
    public void onSensorDataReceived(SensorData data) {
        log.info("Ricevuto SensorData: {}", data);
        processingService.process(data);
    }
}
