package edu.eci.repositories

import edu.eci.models.Enterprise
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface EnterpriseRepository: CrudRepository<Enterprise, Long> {

    fun findByName(name: String): Optional<Enterprise>
}