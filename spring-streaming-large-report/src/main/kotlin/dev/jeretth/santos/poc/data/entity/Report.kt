package dev.jeretth.santos.poc.data.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(schema = "reporting", name = "report")
data class Report(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "description", length = 1000, nullable = false)
    val description: String,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal
)
