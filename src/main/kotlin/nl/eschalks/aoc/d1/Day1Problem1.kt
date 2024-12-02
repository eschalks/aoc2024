package nl.eschalks.aoc.d1

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import kotlin.math.absoluteValue

class Day1Problem1 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        val l1 = ArrayList<Int>()
        val l2 = ArrayList<Int>()
        problemInput.forEachLine {
            val numbers = it.split(' ').filter { it.isNotBlank() }
            l1.add(numbers[0].toInt())
            l2.add(numbers[1].toInt())
        }


        l1.sort()
        l2.sort()

        var total = 0

        l1.forEachIndexed { index, n ->
            total += (n - l2[index]).absoluteValue
        }

        println(total)
    }

}