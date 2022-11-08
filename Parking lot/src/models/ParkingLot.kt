package models

import TicketId
import enums.VehicleType

class ParkingLot() {
    var id: String
    private var floors: Array<Floor> = emptyArray()

    init {
        if (isPresent) throw Exception("Already Exist")
        this.id = "123"
        isPresent = true
    }

    constructor(id: String, numberOfFloors: Int, slotsPerFloor: Int) : this() {
        this.id = id
        addFloors(numberOfFloors, slotsPerFloor)
    }

    fun addFloor() {
        val newFloorNumber = floors.size + 1
        val newFloor = Floor(newFloorNumber, parkingLot = this)
        floors[newFloorNumber] = newFloor
    }

    fun findSlot(vehicleType: VehicleType): Slot {
        if (floors.isEmpty()) throw IllegalStateException("No Floors Exists")
        for (floorNumber in 1..floors.size) {
            val floor = floors[floorNumber]
            if (floor.hasEmptySlot(vehicleType))
                return floor.getNextEmptySlot(vehicleType)
        }
        throw IllegalArgumentException("No relevant Models.Slot present for vehicle type of $vehicleType")
    }

    fun findSlot(ticketId: TicketId): Slot {
        if (floors.isEmpty()) throw IllegalStateException("No Floors Exists")
        for (floorNumber in 1..floors.size) {
            val floor = floors[floorNumber]
            try {
                return floor.findSlot(ticketId)
            } catch (e: java.lang.Exception) {
                continue
            }
        }
        throw Exception("No relevant Models.Slot present")
    }

    private fun addFloors(numberOfFloors: Int, slotsPerFloor: Int) {
        for (i in 1..numberOfFloors) {
            floors[i] = Floor(i, slotsPerFloor, this)
        }
    }

    companion object {
        private var isPresent: Boolean = false
    }
}
