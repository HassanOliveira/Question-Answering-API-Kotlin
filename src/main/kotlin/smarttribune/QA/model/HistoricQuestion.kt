package smarttribune.QA.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
data class HistoricQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val title: String,

    val status: Status,

    val updatedAt: LocalDateTime?,
)
