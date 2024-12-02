package nl.eschalks.aoc

class Counter<T> {
    private val counts = HashMap<T, Int>()

    fun add(value: T) {
        counts.update(value, { (it ?: 0) + 1})
    }

    operator fun get(key: T): Int {
        return counts[key] ?: 0
    }
}