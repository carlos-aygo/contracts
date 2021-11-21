package edu.eci.services

import edu.eci.entities.OrchestratorEvent
import edu.eci.enums.EnterpriseStateEnum
import edu.eci.models.Enterprise
import edu.eci.repositories.EnterpriseRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class EnterpriseServiceImpl(
    private val enterpriseRepository: EnterpriseRepository,
    private val kafkaPublisherService: KafkaPublisherService
) : EnterpriseService {

    override fun createEnterprise(enterprise: Enterprise): Enterprise {

        enterprise.createdAt = LocalDateTime.now()
        enterprise.state = EnterpriseStateEnum.PENDING.name

        return this.enterpriseRepository.save(enterprise)
    }

    override fun updateEnterprise(enterprise: Enterprise): Enterprise {
        enterprise.state = EnterpriseStateEnum.DONE.name

        return this.enterpriseRepository.update(enterprise).let { enterpriseDB ->
            this.kafkaPublisherService.sendOrchestratorData(
                OrchestratorEvent(
                    event = "CONTRACTS_PHASE_DONE",
                    data = enterpriseDB,
                    id = enterpriseDB.id.toString()
                )
            )
            enterpriseDB
        }
    }

    override fun getPendingEnterprises(): List<Enterprise> {

        return this.getAllEnterprises().filter { it.state == EnterpriseStateEnum.DONE.name }
    }

    override fun getDoneEnterprises(): List<Enterprise> {

        return this.getAllEnterprises().filter { it.state == EnterpriseStateEnum.DONE.name }
    }

    override fun getAllEnterprises(): List<Enterprise> {

        return this.enterpriseRepository.findAll().sortedBy { it.createdAt }
    }
}