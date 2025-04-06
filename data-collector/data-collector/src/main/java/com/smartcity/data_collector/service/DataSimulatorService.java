package com.smartcity.data_collector.service;

import com.smartcity.data_collector.model.SensorData;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class DataSimulatorService {
    private final Random random = new Random();



    public SensorData generateRandomData() {
        SensorData sensorData= new SensorData();

        sensorData.setId("sensor-" + UUID.randomUUID().toString().substring(0, 8));

        sensorData.setZona(getZonaRandom());

        sensorData.setTimestamp(LocalDateTime.now());

        String tipo = getTipoRandom();

        switch (tipo) {
            case "temperatura":
                sensorData.setTipo("temperatura");
                sensorData.setUnita("°C");
                sensorData.setValore(random.nextInt(40 - 2 + 1) + 2);
                break;
            case "umidità":
                sensorData.setTipo("umidità");
                sensorData.setUnita("%");
                sensorData.setValore(random.nextInt(100 - 30 +1) + 30);
                break;
            case "traffico":
                sensorData.setTipo("traffico");
                sensorData.setUnita("%");
                sensorData.setValore(random.nextInt(100 - 1 + 1) + 1);
                break;
            case "pm10":
                sensorData.setTipo("pm10");
                sensorData.setUnita("µg/m³");
                sensorData.setValore(random.nextInt(200 - 10 +1) + 10);
                break;
        }
        return sensorData;
    }

    private String getTipoRandom() {
        String[] tipi = {"temperatura", "umidità", "traffico", "pm10"};
        int index = random.nextInt(tipi.length);
        return tipi[index];
    }

    private String getZonaRandom() {
        String[] zone = {"Centro", "Zona Nord", "Zona Sud", "Zona Est", "Zona Ovest"};
        int index = random.nextInt(zone.length);
        return zone[index];
    }
}

//"Riprendiamo da KafkaProducerService"
//oppure
//"Iniziamo a inviare i dati simulati a Kafka"
