fun main() {
    fun part1(input: List<String>): Int {
        val signals = input.map { it.split(" | ") }.map { it.last().split(" ") }
        val count = signals.map {
            it.count { signal ->
                signal.length == 2 || signal.length == 3 || signal.length == 4 || signal.length == 7
            }
        }.sum()
        return count
    }

    fun part2(inputs: List<String>): Int {
        val output = inputs.map { input ->
            val (patterns, signals) = input.split(" | ").map { line -> line.split(" ") }
            val decodePatterns = mutableMapOf<Int, CharSequence>()
            patterns.forEach { pattern ->
                when (pattern.length) {
                    2 -> decodePatterns[1] = pattern
                    3 -> decodePatterns[7] = pattern
                    4 -> decodePatterns[4] = pattern
                    7 -> decodePatterns[8] = pattern
                }
            }

            signals.map decodingSignal@{ signal ->
                when (signal.length) {
                    2 -> 1
                    3 -> 7
                    4 -> 4
                    7 -> 8
                    5 -> {
                        if (signal.count { decodePatterns[7]!!.contains(it) } == 3) return@decodingSignal 3
                        if (signal.count { decodePatterns[4]!!.contains(it) } == 3) return@decodingSignal 5
                        return@decodingSignal 2
                    }
                    6 -> {
                        if (signal.count { decodePatterns[4]!!.contains(it) } == 4) return@decodingSignal 9
                        if (signal.count { decodePatterns[7]!!.contains(it) } == 3) return@decodingSignal 0
                        return@decodingSignal 6
                    }
                    else -> -1
                }
            }.joinToString("").toInt()
        }
        return output.sum()
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}


// 1- length 2
// 4- length 4
// 7- length 3
// 8- length 7

// 5 - length 5 , 3 chars common with 4
// 3 - length 5, 3 chars common with 3
// 2 - length 5
// 9 - length 6, 4 chars common with 4
// 0 - length 6, 3 chars common with 7
// 6 - length 6
