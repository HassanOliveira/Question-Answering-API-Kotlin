package smarttribune.QA.model

import jakarta.persistence.*

// Define an embeddable class for the answer, containing the channel and the body of the answer
@Entity
data class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "channel")
    val channel: Channel,
    @Column(name = "body")
    val body: String
)
