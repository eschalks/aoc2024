package nl.eschalks.aoc.d4

import nl.eschalks.aoc.Grid
import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution
import nl.eschalks.aoc.Vector

class Day4Problem1 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        val grid = problemInput.useLines {
            Grid.fromLines(it)
        }

        var total = 0

        for (y in 0 until grid.height) {
            for (x in 0 until grid.width) {
                total += countXmas(grid, Vector(x, y))
            }
        }

        println(total)
    }

    private fun countXmas(grid: Grid, start: Vector): Int {
        val c = grid.get(start.x, start.y)
        if (c != 'X') {
            return 0
        }

        return Vector.ALL_DIRECTIONS.count { isXmas(grid, start, it) }
    }

    private fun isXmas(grid: Grid, start: Vector, direction: Vector): Boolean {
        val positions = listOf(
            start,
            start + direction,
            start + direction * 2,
            start + direction * 3,
        )

        return positions.map { grid.get(it.x, it.y) }.toCharArray().concatToString() == "XMAS"
    }

}