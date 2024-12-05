package nl.eschalks.aoc

data class Vector(val x: Int, val y: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
    operator fun unaryMinus() = Vector(-x, -y)

    companion object {
        val ORDINAL_DIRECTIONS = listOf(Vector(1, 0), Vector(0, 1), Vector(-1, 0), Vector(0, -1))
        val DIAGONAL_DIRECTIONS = listOf(Vector(1, 1), Vector(-1, 1), Vector(-1, -1), Vector(1, -1))
        val ALL_DIRECTIONS = ORDINAL_DIRECTIONS + DIAGONAL_DIRECTIONS
    }
}
