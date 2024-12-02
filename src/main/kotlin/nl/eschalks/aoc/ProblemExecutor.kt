package nl.eschalks.aoc

import org.reflections.Reflections
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class ProblemExecutor(private val isExample: Boolean) {
    private val pattern = """Day(\d+)Problem(\d+)""".toRegex()

    fun runLast() {
        run(sortedClasses().last())
    }

    fun run(solution: KClass<out Solution>) {
        val day = pattern.matchEntire(solution.simpleName.toString())!!.groupValues[1].toInt()
        val input = ProblemInput(day, isExample)
        solution.primaryConstructor!!.call().execute(input)
    }

    private fun sortedClasses(): List<KClass<out Solution>> {
        return Reflections(javaClass.`package`.name)
            .getSubTypesOf(Solution::class.java)
            .sortedBy {
                val groups = pattern.matchEntire(it.simpleName.orEmpty())!!.groupValues
                groups[1].toInt() * 100 + groups[2].toInt()
            }
            .map { it.kotlin }
    }
}