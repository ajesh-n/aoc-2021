fun main() {
    fun part1(input: List<String>): Int {
        var noOfLargerMeasurements = 0
        input.reduce { depth, nextDepth ->
            if (depth.toInt() < nextDepth.toInt()) noOfLargerMeasurements++
            return@reduce nextDepth
        }
        return noOfLargerMeasurements
    }

    fun part2(input: List<String>): Int {
        val windowSize = 3
        val measuredWindows = input.windowed(windowSize, 1) { depths ->
            depths.map { it.toInt() }.sum().toString()
        }
        return part1(measuredWindows)
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
