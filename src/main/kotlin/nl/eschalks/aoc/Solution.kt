package nl.eschalks.aoc

interface Solution  {
    suspend fun execute(problemInput: ProblemInput)
}