package nl.eschalks.aoc.d5

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution

class Day5Problem2 : Solution {
    override suspend fun execute(problemInput: ProblemInput) {
        val sections = problemInput.sections()
        val iterator = sections.iterator()

        val predicates = iterator.next().map {
            val s = it.split('|')
            Pair(s[0], s[1])
        }.groupBy {
            it.second
        }.mapValues {
            it.value.map { it.first }
        }

        val total = iterator.next().asSequence().map {
            it.split(',')
        }.filter {
            !isValid(it, predicates)
        }.sumOf {
            val ordered = order(it, predicates)
            ordered[ordered.size / 2].toInt()
        }

        println(total)
    }

    private fun order(items: List<String>, preds: Map<String, List<String>>): List<String> {
        var remaining = items.toSet()

        var result = ArrayList<String>()

        while (remaining.isNotEmpty()) {
            for (item in remaining) {
                if (canAdd(preds[item].orEmpty(), remaining)) {
                    result.add(item)
                }
            }

            remaining = remaining - result.toSet()
        }

        return result
    }

    private fun canAdd(preds: List<String>, remaining: Set<String>): Boolean {
        for (pred in preds) {
            if (remaining.contains(pred)) {
                return false
            }
        }

        return true
    }

    fun isValid(seq: List<String>, preds: Map<String, List<String>>): Boolean {
        val encountered = HashSet<String>()
        val missing = HashSet<String>()

        for (item in seq) {
            preds[item].orEmpty().forEach {
                if (!encountered.contains(it)) {
                    missing.add(it)
                }
            }

            encountered.add(item)
        }

        return missing.count { encountered.contains(it) } == 0
    }
}