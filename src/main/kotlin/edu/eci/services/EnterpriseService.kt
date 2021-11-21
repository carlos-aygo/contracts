package edu.eci.services

import edu.eci.models.Enterprise

interface EnterpriseService {

    fun createEnterprise(enterprise: Enterprise): Enterprise
    fun updateEnterprise(enterprise: Enterprise): Enterprise
    fun getPendingEnterprises(): List<Enterprise>
    fun getDoneEnterprises(): List<Enterprise>
    fun getAllEnterprises(): List<Enterprise>
}