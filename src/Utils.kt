import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Converts input into commands
 */
fun parseCommands(input: List<String>): List<Command> {
    return input.map {
        val (dir, steps) = it.split(" ")
        Command(Direction.valueOf(dir.uppercase(Locale.getDefault())), steps.toInt())
    }
}
