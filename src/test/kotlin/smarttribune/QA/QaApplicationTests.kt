import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import smarttribune.QA.QaApplication
import smarttribune.QA.model.QandA
import smarttribune.QA.model.Status
import smarttribune.QA.model.Answer
import smarttribune.QA.model.Channel
import smarttribune.QA.services.QandAService
import smarttribune.QA.model.HistoricQuestion
import java.time.LocalDateTime

@SpringBootTest(classes = [QaApplication::class])
class QaApplicationTests {

	@Autowired
	lateinit var qandAService: QandAService

	private val exportService = ExportService()

	@Test
	fun testUpdateQandA() {
		// Create an existing QandA object for testing
		val qandA = QandA(
			id = 1L,
			title = "Question",
			promoted = false,
			status = Status.DRAFT,
			answers = listOf(
				Answer(1L, Channel.FAQ, "Answer 1"),
				Answer(2L, Channel.BOT, "Answer 2")
			),
			createdAt = LocalDateTime.now(),
			updatedAt = null,
			historicQuestions = emptyList()
		)

		// Make the call to the update function
		val newUpdatedAt = LocalDateTime.now() // Provide a new value for newUpdatedAt
		val updatedQandA = qandAService.updateQandA(qandA, "New title", Status.PUBLISHED, newUpdatedAt)

		// Check if the QandA object has been updated correctly
		assert(updatedQandA.title == "New title")
		assert(updatedQandA.status == Status.PUBLISHED)
		assert(updatedQandA.updatedAt != null)

		// Check if the list of historicalQuestions was updated correctly
		assert(updatedQandA.historicQuestions.size == 1)
		val historicQuestion = updatedQandA.historicQuestions[0]
		assert(historicQuestion.id == 1L)
		assert(historicQuestion.title == "Question")
		assert(historicQuestion.status == Status.DRAFT)
		assertNotNull(historicQuestion.updatedAt)
	}

	@Test
	fun testExportHistoricQuestionToCsv() {
		// Create a QandA object for testing
		val qandA = QandA(
			id = 1L,
			title = "Question",
			promoted = false,
			status = Status.DRAFT,
			answers = listOf(
				Answer(1L, Channel.FAQ, "Answer 1"),
				Answer(2L, Channel.BOT, "Answer 2")
			),
			createdAt = LocalDateTime.now(),
			updatedAt = null,
			historicQuestions = listOf(
				HistoricQuestion(1L, "First Question", Status.DRAFT, LocalDateTime.now().minusDays(1)),
				HistoricQuestion(2L, "Other Question", Status.PUBLISHED, LocalDateTime.now().minusDays(2))
			)
		)

		// Get the historical questions data from the QandA object
		val historicQuestions: List<HistoricQuestion> = qandAService.getAllHistoricQuestions(qandA)

		// Define the CSV file
		val projectDir = System.getProperty("user.dir")
		val filePath = "$projectDir/historic.csv"

		// Export the HistoricalQuestion data to the CSV file
		exportService.exportToCsv(historicQuestions, filePath)
	}
}
