package nl.eschalks.aoc

import org.jsoup.Jsoup
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URI

class ProblemInput(val day: Int, val isExample: Boolean) {
    fun open(): InputStream {
        val filename = if (isExample) {
            "$day.example.txt"
        } else {
            "$day.txt"
        }

        return ensureFile(filename).inputStream()
    }

    private fun ensureFile(filename: String): File {
        val file = File("input", filename)
        if (!file.exists()) {
            if (isExample) {
                file.writeText(fetchExample())
            } else {
                fetchReal().use { input ->
                    file.outputStream().use { output ->
                        output.write(input.readAllBytes())
                    }
                }
            }
        }

        return file
    }

    private fun fetchExample(): String {
        val html = Jsoup.connect("https://adventofcode.com/2024/day/$day").get()
        val example = html.selectFirst("article.day-desc pre > code")!!
        return example.text()
    }

    private fun fetchReal(): InputStream {
        val url = URI("https://adventofcode.com/2024/day/$day/input").toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Cookie", "session=${System.getenv("AOC")}")
        return connection.inputStream
    }

    inline fun <R> useLines(filter: Boolean = true, block: (Sequence<String>) -> R): R {
        open().bufferedReader().useLines {
            val seq = it.map { it.trim() }
            if (!filter) {
                return block(seq)
            }

            return block(seq.filter { it.isNotEmpty() })
        }
    }

    fun sections(): Sequence<List<String>> {
        return sequence {
            var section = ArrayList<String>()
            useLines(filter = false) {
                for (line in it) {
                    if (line.isEmpty()) {
                        yield(section)
                        section = ArrayList()
                        continue
                    }

                    section.add(line)
                }
            }

            if (section.isNotEmpty()) {
                yield(section)
            }
        }
    }

    fun forEachLine(block: (String) -> Unit) {
        useLines {
            it.forEach(block)
        }
    }

    fun line(): String = open().bufferedReader().use {
        it.readLine()
    }

    fun text() = open().bufferedReader().use {
        it.readText()
    }
}