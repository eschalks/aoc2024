package nl.eschalks.aoc

class Grid(val width: Int, val height: Int, private val fallback: Char = '.') {
    private val grid = Array(width * height) { fallback }

    companion object {
        fun fromLines(lines: Sequence<String>): Grid {
            val lineList = lines.toList()
            val grid = Grid(lineList[0].length, lineList.size)

            lineList.forEachIndexed { x, line ->
                line.forEachIndexed { y, char ->
                    grid.set(x, y, char)
                }
            }
            return grid
        }
    }

    fun get(x: Int, y: Int): Char {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return fallback
        }

        return grid[y * width + x]
    }

    fun set(x: Int, y: Int, c: Char) {
        grid[y * width + x] = c
    }
}