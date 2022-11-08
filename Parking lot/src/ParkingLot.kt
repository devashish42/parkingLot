class ParkingLot constructor(id: String, numberOfFloors: Int, slotsPerFloor: Int) {
    var id: String
    var floors: Array<Floor> = emptyArray()

    init {
        if (isPresent) throw Exception("Already Exist")
        this.id = id
        isPresent = true

        for (i in 1..numberOfFloors) {
            floors[i] = Floor(i, slotsPerFloor, this)
        }
    }

    fun addFloor() {
        val newFloorNumber = floors.size + 1
        val newFloor = Floor(newFloorNumber, parkingLot = this)
        floors[newFloorNumber] = newFloor
    }

    private fun findSlot(vehicleType: VehicleType): Slot {
        if (floors.isEmpty()) throw IllegalStateException("No Floors Exists")
        for (floorNumber in 1..floors.size) {
            val floor = floors[floorNumber]
            try {
                return floor.findSlot(vehicleType)
            } catch (e: java.lang.Exception) {
                continue
            }
        }
        throw Exception("No relevant Slot present")
    }

    private fun findSlot(ticketId: TicketId): Slot {
        if (floors.isEmpty()) throw IllegalStateException("No Floors Exists")
        for (floorNumber in 1..floors.size) {
            val floor = floors[floorNumber]
            try {
                return floor.findSlot(ticketId)
            } catch (e: java.lang.Exception) {
                continue
            }
        }
        throw Exception("No relevant Slot present")
    }

    fun parkVehicle(vehicleType: VehicleType, regNo: String, color: String): Ticket {
        val freeSlot = findSlot(vehicleType)
        return freeSlot.book(regNo, color)
    }

    fun unParkVehicle(ticketId: TicketId) {
        val slot = findSlot(ticketId)
        slot.ticket = null
        slot.isTaken = false
        println("vehicle with ticketId: $ticketId unParked")
    }

    companion object {
        private var isPresent: Boolean = false
    }
}
