package edu.eci.publishers

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.context.annotation.Property
import org.apache.kafka.clients.producer.ProducerConfig

@KafkaClient(
    id= "introduction",
    acks = KafkaClient.Acknowledge.DEFAULT,
    properties = [
        Property(name = ProducerConfig.RETRIES_CONFIG, value = "3"),
        Property(name = ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, value = "10000"),
        Property(name = ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, value = "10000"),
        Property(name = ProducerConfig.COMPRESSION_TYPE_CONFIG, value = "gzip"),
    ]
)
interface KafkaPublisher {

    @Topic("orchestrator")
    fun sendOrchestratorData(data: String)
}