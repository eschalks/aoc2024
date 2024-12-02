package nl.eschalks.aoc

fun main(args: Array<String>) {
    val problemExecutor = ProblemExecutor(isExample = args.contains("--example"))
    problemExecutor.runLast()
}