package service

import Ticket
import TicketId
import enums.VehicleType
import models.ParkingLot

class ParkingLotService() {

    private val parkingLot: ParkingLot

    private val parkingLotId = "123"

    init {
        parkingLot = ParkingLot(parkingLotId, 4, 4)
    }

    fun parkVehicle(vehicleType: VehicleType, regNo: String, color: String): Ticket {
        val freeSlot = parkingLot.findSlot(vehicleType)
        return freeSlot.book(regNo, color)
    }

    fun addFloor() {
        parkingLot.addFloor()
    }

    fun unParkVehicle(ticketId: TicketId) {
        val slot = parkingLot.findSlot(ticketId)
        slot.vacate()
        println("vehicle with ticketId: $ticketId unParked")
    }
}
