package stage5


import java.util.*

val scanner = Scanner(System.`in`)

data class Car(val registration: String, val color: String)

fun main() {
    var spots: MutableList<Car?> = mutableListOf()

    while (true) {
        val action = scanner.next()
        when  {
            action == "exit" -> break
            action == "create" -> spots = create()
            spots.isEmpty() -> {
                println("Sorry, a parking lot has not been created.")
                if (scanner.hasNext()) {
                    scanner.next() // read and ignore any param
                }
            }
            action == "status" -> status(spots)
            action == "leave" -> leave(spots)
            action == "park" -> park(spots)
            action == "spot_by_color" -> spotByColor(spots, scanner.next())
            action == "reg_by_color" -> regByColor(spots, scanner.next())
            action == "spot_by_reg" -> spotByReg(spots, scanner.next())
        }
    }
}

fun regByColor(spots: List<Car?>, color: String) {
    println(spots.withIndex()
        .filter { car -> car.value?.color.equals(color, ignoreCase = true) }
        .joinToString { car -> "${car.value?.registration}" }
        .ifEmpty { "No cars with color $color were found." }
    )
}

fun spotByReg(spots: List<Car?>, registration: String) {
    println(spots.withIndex()
        .filter { car -> car.value?.registration == registration }
        .joinToString { car -> "${car.index + 1}" }
        .ifEmpty { "No cars with registration number $registration were found." }
    )
}

fun spotByColor(spots: List<Car?>, color: String) {
    println(spots.withIndex()
        .filter { car -> car.value?.color.equals(color, ignoreCase = true) }
        .joinToString { car -> "${car.index + 1}" }
        .ifEmpty { "No cars with color $color were found." }
    )
}

fun status(spots: List<Car?>) {
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
    val spot = scanner.nextInt()
    if (spots[spot - 1] == null) {
        println("There is no car in spot $spot.")
    } else {
        spots[spot - 1] = null
        println("Spot $spot is free.")
    }
}

fun park(spots: MutableList<Car?>) {
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