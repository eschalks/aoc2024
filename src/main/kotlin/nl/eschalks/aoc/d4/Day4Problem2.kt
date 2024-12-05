package nl.eschalks.aoc.d4

import nl.eschalks.aoc.Grid
import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import nl.eschalks.aoc.Vector

class Day4Problem2 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        val grid = problemInput.useLines {
            Grid.fromLines(it)
        }

        var total = 0

        for (y in 0 until grid.height) {
            for (x in 0 until grid.width) {
                if (isXmas(grid, Vector(x, y))) {
                    total++
                }
            }
        }

        println(total)
    }

    private fun isXmas(grid: Grid, start: Vector): Boolean {
        val c = grid.get(start.x, start.y)
        if (c != 'A') {
            return false
        }

        return Vector.DIAGONAL_DIRECTIONS.count {
            getWord(grid, start + it, -it) == "MAS"
        } == 2
    }

    private fun getWord(grid: Grid, start: Vector, direction: Vector): String {
        val positions = listOf(
            start,
            start + direction,
            start + direction * 2,
        )

        return positions.map { grid.get(it.x, it.y) }.toCharArray().concatToString()
    }

}