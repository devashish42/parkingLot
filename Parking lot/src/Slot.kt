class Slot(slotNumber: Int, floorNumber: Int, parkingLot: ParkingLot) {

    var number: Int? = null
    var floorNumber: Int? = null

    private val parkingLot: ParkingLot

    var isTaken: Boolean = false

    var ticket: Ticket? = null

    init {
        this.number = slotNumber
        this.floorNumber = floorNumber
        this.parkingLot = parkingLot
    }

    fun book(regNo: String, color: String): Ticket {
        val ticketId = TicketId(parkingLot.id, floorNumber!!, number!!)
        ticket = Ticket(ticketId, regNo, color)
        return ticket!!
    }

    companion object {
        fun getVehicleType(slotNumber: Int): VehicleType {
            return if (slotNumber == 1)
                VehicleType.TRUCK
            else if (slotNumber == 2 || slotNumber == 3)
                VehicleType.BIKE
            else VehicleType.CAR
        }
    }
}
