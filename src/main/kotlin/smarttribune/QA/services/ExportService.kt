import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException
import smarttribune.QA.model.HistoricQuestion

@Service
public class ExportService {

    fun exportToCsv(data: List<HistoricQuestion>, filePath: String) {
        try {
            val file = File(filePath)
            FileWriter(file).use { writer ->
                writer.append(getCsvHeader())
                writer.append("\n")

                for (item in data) {
                    writer.append(getCsvData(item))
                    writer.append("\n")
                }

                writer.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getCsvHeader(): String {
        val fields = HistoricQuestion::class.java.declaredFields
        return fields.joinToString(",") { StringUtils.capitalize(it.name) }
    }

    private fun getCsvData(item: HistoricQuestion): String {
        val fields = HistoricQuestion::class.java.declaredFields
        return fields.joinToString(",") { field ->
            field.isAccessible = true
            field.get(item)?.toString() ?: ""
        }
    }
}
