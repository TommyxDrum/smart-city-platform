package com.smartcity.data_processor.service;

import com.smartcity.common.model.SensorData;
import com.smartcity.data_processor.model.ProcessedSensorData;

public interface SensorDataProcessorService {
    String getTipo();
    ProcessedSensorData process(SensorData incoming);
}
