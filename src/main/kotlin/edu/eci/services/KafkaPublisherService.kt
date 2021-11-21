package edu.eci.services

interface KafkaPublisherService {

    fun sendOrchestratorData(data: Any)
}