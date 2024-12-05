package nl.eschalks.aoc.d5

import nl.eschalks.aoc.ProblemInput
import nl.eschalks.aoc.Solution

class Day5Problem1 : Solution {
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
            isValid(it, predicates)
        }.sumOf {
            it[it.size / 2].toInt()
        }

        println(total)
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