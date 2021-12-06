fun main() {

    fun rate(input: List<String>, isGama: Boolean = true): String {
        val map: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        input.forEach { item ->
            (item.indices).forEach {
                map[it]?.add(item[it].digitToInt()) ?: run {
                    map[it] = mutableListOf()
                    map[it]?.add(item[it].digitToInt())
                }
            }
        }
        return map.keys.map { it ->
            map[it]?.groupingBy { it }?.eachCount()?.let { bits ->
                if (isGama) bits.maxByOrNull { it.value }?.key else bits.minByOrNull { it.value }?.key
            }
        }.joinToString("")
    }

    fun getGamaRate(input: List<String>): String {
        return rate(input)
    }

    fun getEpsilonRate(input: List<String>): String {
        return rate(input, false)
    }

    fun rating(input: List<String>, isOxygen: Boolean = true): String {
        var list = input
        var i = 0
        while (list.count() > 1) {
            val gamaRate = getGamaRate(list)
            val epsilonRate = getEpsilonRate(list)
            list = list.filter {
                if (gamaRate == epsilonRate) {
                    it[i].digitToInt() == if (isOxygen) 1 else 0
                } else {
                    it[i] == if (isOxygen) gamaRate[i] else epsilonRate[i]
                }
            }
            i++
        }
        return list.last()
    }

    fun part1(input: List<String>): Int {
        val gamaRate = getGamaRate(input)
        val epsilonRate: String = getEpsilonRate(input)
        return gamaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = rating(input).toInt(2)
        val co2ScrubberRating = rating(input, false).toInt(2)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
