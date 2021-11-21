package edu.eci.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import edu.eci.utils.DateUtil
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
@Entity
@Table(name = "enterprises")
open class Enterprise {

    @Id
    @field:JsonProperty("id")
    @SequenceGenerator(name = "enterprises_id_seq", sequenceName = "enterprises_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprises_id_seq")
    open var id: Long = 0

    @field:JsonProperty("name")
    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    open var name: String = ""

    @NotNull
    @NotBlank
    @field:JsonProperty("nit")
    @Column(name = "nit", nullable = false)
    open var nit: String = ""

    @field:JsonProperty("state")
    @Column(name = "state", nullable = false)
    open var state: String = ""

    @JsonFormat
        (shape = JsonFormat.Shape.STRING, pattern = DateUtil.DATE_STRING_FORMAT)
    @field:JsonProperty("created_at")
    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
}