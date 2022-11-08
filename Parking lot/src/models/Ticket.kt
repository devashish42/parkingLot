data class TicketId(
    val parkingLotId: String,
    val floorNumber: Int,
    val slotNumber: Int
)

class Ticket(ticketId: TicketId, regNo: String, color: String) {
    val ticketId: TicketId
    private val regNo: String
    private val color: String

    init {
        this.ticketId = ticketId
        this.regNo = regNo
        this.color = color
    }
}
