package nl.eschalks.aoc.d3

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution

class Day3Problem2 : Solution {
    private val pattern = """do\(\)|don't\(\)|mul\((\d+),(\d+)\)""".toRegex()

    override suspend fun execute(problemInput: ProblemInput) {
        var total = 0
        var isEnabled = true

        pattern.findAll(problemInput.text()).forEach {
            if (it.value == "do()") {
                isEnabled = true
            } else if (it.value == "don't()")  {
                isEnabled = false
            } else if (isEnabled) {
                total += it.groupValues[1].toInt() * it.groupValues[2].toInt()
            }
        }

        println(total)
    }
}