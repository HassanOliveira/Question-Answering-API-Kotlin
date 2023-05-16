package smarttribune.QA.model

import jakarta.persistence.*
import java.time.LocalDateTime

// Define a data class for the Q&A, containing the title, promoted, status, answers, createdAt, updatedAt, and historicQuestions
@Entity
data class QandA(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val promoted: Boolean,
    var status: Status,
    @OneToMany(cascade = [CascadeType.ALL])
    val answers: List<Answer>,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime?,
    @OneToMany(cascade = [CascadeType.ALL])
    var historicQuestions: List<HistoricQuestion> = emptyList()
)
