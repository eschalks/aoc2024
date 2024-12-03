package nl.eschalks.aoc.d3

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution

class Day3Problem1 : Solution {
    private val pattern = """mul\((\d+),(\d+)\)""".toRegex()

    override suspend fun execute(problemInput: ProblemInput) {
        val total = pattern.findAll(problemInput.text()).sumOf {
            val x = it.groupValues[1].toInt()
            val y = it.groupValues[2].toInt()
            x * y
        }
        println(total)
    }
}