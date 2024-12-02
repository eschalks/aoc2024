package nl.eschalks.aoc

inline fun <T> Sequence<T>.forEachWithPrevious(block: (item: T, previous: T) -> Unit) {
    var previous: T? = null

    forEach { item ->
        previous?.let {
            block(item, it)
        }
        previous = item
    }
}