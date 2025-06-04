package com.smartcity.data_processor.repository;

import com.smartcity.data_processor.model.ProcessedSensorData;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

// 1) Repository: due metodi aggregazione separati
public interface ProcessedSensorDataRepository extends MongoRepository<ProcessedSensorData, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'tipo': ?0 } }",
            "{ '$group': { '_id': null, 'sum': { '$sum': '$originalValue' } } }",
            "{ '$project': { '_id': 0, 'sum': 1 } }"
    })
    Double findSumByTipo(String tipo);

    @Aggregation(pipeline = {
            "{ '$match': { 'tipo': ?0 } }",
            "{ '$group': { '_id': null, 'count': { '$sum': 1 } } }",
            "{ '$project': { '_id': 0, 'count': 1 } }"
    })
    Long findCountByTipo(String tipo);
}




