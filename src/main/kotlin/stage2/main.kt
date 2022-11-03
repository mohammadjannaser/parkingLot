package stage2

import java.util.*

val scanner = Scanner(System.`in`)

data class Car(val registration: String, val color: String)

fun main() {
    val spots = Array<Car?>(2) { null }
    spots[0] = Car("unknown", "unknown")

    val action = scanner.next()
    when (action) {
        "leave" -> {
            val spot = scanner.nextInt()
            if (spots[spot - 1] == null) {
                println("There is no car in spot $spot.")
            } else {
                spots[spot - 1] = null
                println("Spot $spot is free.")
            }
        }
        "park" -> {
            val firstFreeSpot = spots.indexOfFirst { car -> car == null }
            if (firstFreeSpot < 0) {
                println("No spot free!")
            } else {
                val registration = scanner.next()
                val color = scanner.next()

                spots[firstFreeSpot] = Car(registration, color)

                println("$color car parked in spot ${firstFreeSpot + 1}.")
            }
        }
    }
}