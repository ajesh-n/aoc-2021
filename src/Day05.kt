import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {
    fun List<List<Pair<Int, Int>>>.makePoints(includeDiagonal: Boolean): List<Pair<Int, Int>> =
        this.flatMap {
            val x1 = it.first().first
            val x2 = it.last().first
            val y1 = it.first().second
            val y2 = it.last().second
            when {
                x1 == x2 -> {
                    return@flatMap (min(y1, y2)..max(y1, y2)).map { n ->
                        x1 to n
                    }
                }
                y1 == y2 -> {
                    return@flatMap (min(x1, x2)..max(x1, x2)).map { n ->
                        n to y1
                    }
                }
                includeDiagonal -> {
                    val dx = (x2 - x1).sign
                    val dy = (y2 - y1).sign
                    val d = max(abs(x2 - x1), abs(y2 - y1))
                    return@flatMap (0..d).map { i ->
                        x1 + i * dx to y1 + i * dy
                    }
                }
                else -> {
                    listOf()
                }
            }
        }

    fun parsePoints(inputs: List<String>): List<List<Pair<Int, Int>>> = inputs.map { input ->
        input.split(" -> ").flatMap { item ->
            item.split(" ").map {
                val (x, y) = it.split(",")
                x.toInt() to y.toInt()
            }
        }
    }

    fun part1(inputs: List<String>): Int {
        val points = parsePoints(inputs).makePoints(false)
        return points.groupingBy { it }.eachCount().count { it.value >= 2 }
    }

    fun part2(inputs: List<String>): Int {
        val points = parsePoints(inputs).makePoints(true)
        return points.groupingBy { it }.eachCount().count { it.value >= 2 }
    }

    val inputs = readInput("Day05")
    println(part1(inputs))
    println(part2(inputs))
}