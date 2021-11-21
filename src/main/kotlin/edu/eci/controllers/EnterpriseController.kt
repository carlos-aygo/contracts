package edu.eci.controllers

import edu.eci.models.Enterprise
import edu.eci.services.EnterpriseService
import io.micronaut.http.annotation.*
import javax.validation.Valid

@Controller("enterprises")
open class EnterpriseController(
    private val enterpriseService: EnterpriseService
) {

    @Put
    open fun updateEnterprise(
        @Body @Valid enterprise: Enterprise
    ): Enterprise {

        return this.enterpriseService.updateEnterprise(enterprise)
    }

    @Get("pending")
    open fun getPendingEnterprises(): List<Enterprise> {

        return this.enterpriseService.getPendingEnterprises()
    }

    @Get("done")
    open fun getDoneEnterprises(): List<Enterprise> {

        return this.enterpriseService.getDoneEnterprises()
    }

    @Get
    open fun getAllEnterprises(): List<Enterprise> {

        return this.enterpriseService.getAllEnterprises()
    }
}