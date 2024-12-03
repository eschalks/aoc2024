package nl.eschalks.aoc.d2

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import nl.eschalks.aoc.forEachWithPrevious
import kotlin.math.absoluteValue

class Day2Problem2 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        problemInput.useLines {
            println(it.count(::isSafe))
        }
    }

    fun isSafe(line: String): Boolean {
        val content = line.split(' ').map { it.toInt() }
        if (isCorrect(content)) {
            return true
        }

        for (i in content.indices) {
            val mutable = content.toMutableList()
            mutable.removeAt(i)
            if (isCorrect(mutable)) {
                return true
            }
        }

        println(content)

        return false
    }

    private fun isCorrect(content: List<Int>): Boolean {
        var isNeg: Boolean? = null

        content.asSequence().forEachWithPrevious {item, previous ->
            val diff = item - previous
            if (isNeg == null) {
                isNeg = diff < 0
            }

            if (diff == 0 || diff.absoluteValue > 3 || diff < 0 != isNeg) {
                return false
            }
        }

        return true
    }
}