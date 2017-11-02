package kiosk;

public class Ticket {
    public String clientId;
    public Integer nPersons;
    public Integer ticketNo;
    public Integer tableNo;

    public Ticket(String message) {
        String[] ObjectMapper = message.split(" ");
        this.clientId = ObjectMapper[1];
        this.nPersons = Integer.parseInt(ObjectMapper[2]);
    }

    public String getClientId() {
        return clientId;
    }

    public Integer getnPersons() {
        return nPersons;
    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTicketNo(Integer ticketNo) { this.ticketNo = ticketNo; }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }
}
