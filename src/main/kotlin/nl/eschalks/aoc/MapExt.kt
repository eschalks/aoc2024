package nl.eschalks.aoc

fun <K, V> MutableMap<K, V>.update(key: K, updater: (V?) -> V) {
    val value = this[key]
    this[key] = updater(value)
}