package smarttribune.QA

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["smarttribune.QA.services"])
class QaApplication

fun main(args: Array<String>) {
	runApplication<QaApplication>(*args)
}
