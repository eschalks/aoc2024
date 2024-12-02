package nl.eschalks.aoc.d1

import nl.eschalks.aoc.Counter
import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import nl.eschalks.aoc.update
import kotlin.math.absoluteValue

class Day1Problem2 : Solution {
    override fun execute(problemInput: ProblemInput) {
        val counts = Counter<Int>()
        val l1 = ArrayList<Int>()

        problemInput.forEachLine {
            val numbers = it.split(' ').filter { it.isNotBlank() }
            l1.add(numbers[0].toInt())

            val n2 = numbers[1].toInt()
            counts.add(n2)
        }

        val total = l1.sumOf {
            it * counts[it]
        }

        println(total)
    }

}