import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        val sortedPositions = positions.sorted()
        val intRange = sortedPositions.minOf { it }..sortedPositions.maxOf { it }
        val list = intRange.map { position ->
            sortedPositions.map {
                abs(it - position)
            }
        }
        return list.minByOrNull { it.sum() }?.sum() ?: 0
    }

    fun part2(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        val sortedPositions = positions.sorted()
        val intRange = sortedPositions.minOf { it }..sortedPositions.maxOf { it }
        val list = intRange.map {
            sortedPositions.map { position ->
                val n = abs(it - position)
                n * (n + 1) / 2
            }
        }
        return list.minByOrNull { it.sum() }?.sum() ?: 0
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
