class Board(private val grids: List<List<Int>>) {
    private val marked = mutableListOf<Int>()
    private val rows = (0..24).chunked(5)
    private val columns = (0..24).windowed(5, 1).take(5).map {
        it.mapIndexed { index, i ->
            i + index * 4
        }
    }
    var isWinner: Boolean = false

    fun mark(number: Int): Boolean {
        grids.forEachIndexed { index, row ->
            val position = row.indexOf(number)
            if (position != -1) {
                marked.add(index * 5 + position)
            }
        }
        isWinner = isBingoByRow() || isBingoByColumn()
        return isWinner
    }

    fun sum(): Int {
        return grids.mapIndexed { index, list ->
            list.filterIndexed { position, _ -> !marked.contains(index * 5 + position) }.sum()
        }.sum()
    }

    private fun isBingoByRow(): Boolean = rows.any { marked.containsAll(it) }
    private fun isBingoByColumn(): Boolean = columns.any { marked.containsAll(it) }
}

fun main() {
    fun part1(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = input.asSequence().drop(2).map {
            it.split(" ")
                    .filter { num ->
                        num.isNotEmpty()
                    }.map { num ->
                        num.toInt()
                    }
        }.filter { it.isNotEmpty() }.chunked(5).map {
            Board(it)
        }.toList()

        for (draw in draws) {
            boards.forEach {
                if (it.mark(draw)) {
                    return draw * it.sum()
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = input.asSequence().drop(2).map {
            it.split(" ")
                    .filter { num ->
                        num.isNotEmpty()
                    }.map { num ->
                        num.toInt()
                    }
        }.filter { it.isNotEmpty() }.chunked(5).map {
            Board(it)
        }.toList()

        for (draw in draws) {
            boards.filter { !it.isWinner }.forEach {
                if (it.mark(draw) && boards.all { board -> board.isWinner }) {
                    return draw * it.sum()
                }
            }
        }
        return 0
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
