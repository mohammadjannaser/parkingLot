package stage3


data class Car(val registration: String, val color: String)
const val PARKING_SPACES = 20
val spots = Array<Car?>(PARKING_SPACES) { null }

fun main() {

    while (true) {
        val input = readln()
        if (input.equals("exit",ignoreCase = true)) return

        when (input.split(" ").first()) {
            "leave" -> {
                val (_, spot) = input.split(" ")
                leave(spot.toInt())
            }
            "park" -> {
                val (_,registration, color) = input.split(" ")
                park(registration,color)
            }
        }
    }
}


fun leave(spot: Int) {

    if (spots[spot - 1] == null) {
        println("There is no car in spot $spot.")
    } else {
        spots[spot - 1] = null
        println("Spot $spot is free.")
    }
}

fun park(registration: String, color: String) {

    val firstFreeSpot = spots.indexOfFirst { car -> car == null }
    if (firstFreeSpot < 0) {
        println("Sorry, the parking lot is full.")
    } else {
        spots[firstFreeSpot] = Car(registration, color)
        println("$color car parked in spot ${firstFreeSpot + 1}.")
    }
}