enum class Direction {
    UP {
        override fun move(position: Position, steps: Int): Position {
            return position.up(steps)
        }
    },
    DOWN {
        override fun move(position: Position, steps: Int): Position {
            return position.down(steps)
        }
    },
    FORWARD {
        override fun move(position: Position, steps: Int): Position {
            return position.forward(steps)
        }
    };

    abstract fun move(position: Position, steps: Int): Position
}

class Command(private val direction: Direction, private val steps: Int) {
    fun run(position: Position): Position {
        return direction.move(position, steps)
    }
}

interface Position {
    fun up(steps: Int): Position
    fun down(steps: Int): Position
    fun forward(steps: Int): Position
    fun product(): Int
}

class SimplePosition(
        private val horizontalPosition: Int,
        private val depth: Int,
) : Position {
    override fun up(steps: Int): SimplePosition {
        return SimplePosition(horizontalPosition, depth - steps)
    }

    override fun down(steps: Int): SimplePosition {
        return SimplePosition(horizontalPosition, depth + steps)
    }

    override fun forward(steps: Int): SimplePosition {
        return SimplePosition(horizontalPosition + steps, depth)
    }

    override fun product(): Int {
        return horizontalPosition * depth
    }
}

class AimedPosition(
        private val horizontalPosition: Int,
        private val depth: Int,
        private val aim: Int
) : Position {
    override fun up(steps: Int): AimedPosition {
        return AimedPosition(horizontalPosition, depth, aim - steps)
    }

    override fun down(steps: Int): AimedPosition {
        return AimedPosition(horizontalPosition, depth, aim + steps)
    }

    override fun forward(steps: Int): AimedPosition {
        return AimedPosition(horizontalPosition + steps, depth + steps * aim, aim)
    }

    override fun product(): Int {
        return horizontalPosition * depth
    }
}

class Submarine(private var position: Position) {
    fun move(command: Command) {
        position = command.run(position)
    }

    fun positionProduct(): Int {
        return position.product()
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val position = SimplePosition(0, 0)
        val submarine = Submarine(position)
        parseCommands(input).forEach {
            submarine.move(it)
        }
        return submarine.positionProduct()
    }

    fun part2(input: List<String>): Int {
        val position = AimedPosition(0, 0, 0)
        val submarine = Submarine(position)
        parseCommands(input).forEach {
            submarine.move(it)
        }
        return submarine.positionProduct()
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}