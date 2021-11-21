package edu.eci.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import edu.eci.models.Enterprise
import edu.eci.services.EnterpriseService
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.LoggerFactory

@KafkaListener(
    groupId = "contracts",
    offsetReset = OffsetReset.EARLIEST,
    pollTimeout = "10000ms",
    sessionTimeout = "30000"
)
class KafkaListener(
    private val objectMapper: ObjectMapper,
    private val enterpriseService: EnterpriseService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Topic("contracts")
    fun processEmail(data: String) {
        this.logger.info("start reading contracts ", data)
        try {
            val enterprise = this.objectMapper.readValue(data, Enterprise::class.java)
            this.enterpriseService.createEnterprise(enterprise)

        }catch (ex: Exception){
            this.logger.error("error reading contract {} because {}", data, ex.message, ex)
        }
    }
}