package stage4


import java.util.*

val scanner = Scanner(System.`in`)

data class Car(val registration: String, val color: String)

fun main() {
    var spots: MutableList<Car?> = mutableListOf()

    while (true) {
        when (scanner.next()) {
            "exit" -> break
            "status" -> status(spots)
            "create" -> spots = create()
            "leave" -> leave(spots)
            "park" -> park(spots)
        }
    }
}

fun status(spots: List<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val noOfOccupiedSpots = spots.count { car -> car != null }
    if (noOfOccupiedSpots == 0) {
        println("Parking lot is empty.")
        return
    }

    for (i in spots.indices) {
        val car = spots[i]
        if (car != null) {
            println("${i + 1} ${car.registration} ${car.color}")
        }
    }
}

fun create(): MutableList<Car?> {
    val size = scanner.nextInt()
    println("Created a parking lot with $size spots.")
    return MutableList(size) { null }
}

fun leave(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val spot = scanner.nextInt()
    if (spots[spot - 1] == null) {
        println("There is no car in spot $spot.")
    } else {
        spots[spot - 1] = null
        println("Spot $spot is free.")
    }
}

fun park(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val firstFreeSpot = spots.indexOfFirst { car -> car == null }
    if (firstFreeSpot < 0) {
        println("Sorry, the parking lot is full.")
        return
    }

    val registration = scanner.next()
    val color = scanner.next()

    spots[firstFreeSpot] = Car(registration, color)

    println("$color car parked in spot ${firstFreeSpot + 1}.")
}