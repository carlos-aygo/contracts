package edu.eci.services

import com.fasterxml.jackson.databind.ObjectMapper
import edu.eci.publishers.KafkaPublisher
import io.micronaut.scheduling.annotation.Async
import jakarta.inject.Singleton

@Singleton
open class KafkaPublisherServiceImpl(
    private val kafkaPublisher: KafkaPublisher,
    private val objectMapper: ObjectMapper
): KafkaPublisherService {

    @Async
    override fun sendOrchestratorData(data: Any) {

        this.kafkaPublisher.sendOrchestratorData(
            this.objectMapper.writeValueAsString(data)
        )
    }
}