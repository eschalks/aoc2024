package nl.eschalks.aoc.d2

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import nl.eschalks.aoc.forEachWithPrevious
import kotlin.math.absoluteValue

class Day2Problem1 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        problemInput.useLines {
            println(it.count(::isSafe))
        }
    }

    fun isSafe(line: String): Boolean {
        val content = line.split(' ').asSequence().map { it.toInt() }
        var previousDiff = 0

        content.forEachWithPrevious {item, previous ->
            val diff = item - previous
            if (diff == 0 || diff.absoluteValue > 3) {
                return false
            }

            if (previousDiff != 0) {
                if ((previousDiff > 0) != (diff > 0)) {
                    return false
                }
            }

            previousDiff = diff
        }

        return true
    }
}