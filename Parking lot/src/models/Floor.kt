package models

import TicketId
import enums.VehicleType

class Floor constructor(number: Int, numberOfSlots: Int = 0, parkingLot: ParkingLot) {
    private var number: Int? = null
    private val slots: Array<Slot> = emptyArray()
    private var parkingLot: ParkingLot

    init {
        this.number = number
        this.parkingLot = parkingLot

        (1..numberOfSlots).forEach { slotNumber ->
            slots[slotNumber] = Slot(slotNumber, this.number!!, parkingLot)
        }
    }

    fun addSlot() {
        val newSlotNumber = slots.size + 1
        val newSlot = Slot(newSlotNumber, number!!, parkingLot)
        slots[newSlotNumber] = newSlot
    }

    fun getNextEmptySlot(vehicleType: VehicleType): Slot {
        for (i in 1..slots.size) {
            val slot = slots[i]
            if (!slot.isTaken && slot.isValidForVehicle(vehicleType)) return slot
        }
        throw Exception("No Models.Slot Found")
    }

    fun hasEmptySlot(vehicleType: VehicleType): Boolean {
        for (i in 1..slots.size) {
            val slot = slots[i]
            if (!slot.isTaken && slot.isValidForVehicle(vehicleType)) return true
        }
        return false
    }

    fun findSlot(ticketId: TicketId): Slot {
        for (i in 1..slots.size) {
            val slot = slots[i]
            if (slot.isTaken && slot.ticket!!.ticketId == ticketId) return slots[i]
        }
        throw Exception("No Models.Slot Found")
    }

    fun displayNumberOfFreeSlots(vehicleType: VehicleType) {
        var count = 0
        for (i in 1..slots.size) {
            if (Slot.getVehicleType(slotNumber = i) == vehicleType && !slots[i].isTaken)
                count++
        }
        println("Number of free slots in floor: $number is $count")
    }

    fun displayFreeSlots(vehicleType: VehicleType) {
        for (i in 1..slots.size) {
            if (Slot.getVehicleType(slotNumber = i) == vehicleType && !slots[i].isTaken)
                println(slots[i])
        }
    }

    fun displayOccupiedSlots(vehicleType: VehicleType) {
        for (i in 1..slots.size) {
            if (Slot.getVehicleType(slotNumber = i) == vehicleType && slots[i].isTaken)
                println(slots[i])
        }
    }
}
