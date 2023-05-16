package smarttribune.QA.services

import org.springframework.stereotype.Service
import smarttribune.QA.model.HistoricQuestion
import smarttribune.QA.model.QandA
import smarttribune.QA.model.Status
import java.time.LocalDateTime

@Service
class QandAService {
    // Function to update an existing question
    fun updateQandA(qanda: QandA, newTitle: String, newStatus: Status, newUpdatedAt: LocalDateTime): QandA {
        val currentUpdatedAt = qanda.updatedAt ?: LocalDateTime.now()

        // Creates a copy of the original question with the new values
        val updatedQandA = qanda.copy(title = newTitle, status = newStatus, updatedAt = newUpdatedAt)

        // Adds the original question to the list of historical questions
        val historicQuestion = HistoricQuestion(qanda.id, qanda.title, qanda.status, currentUpdatedAt)
        updatedQandA.historicQuestions = qanda.historicQuestions.plus(historicQuestion)

        return updatedQandA
    }

    fun getAllHistoricQuestions(qandA: QandA): List<HistoricQuestion> {
        return qandA.historicQuestions
    }
}
