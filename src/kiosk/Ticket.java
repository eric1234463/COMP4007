package kiosk;

public class Ticket {
    private Integer clientId;
    private Integer nPersons;
    private Integer ticketNo;
    private Integer tableNo;
    public Ticket(String message) {
        String[] ObjectMapper = message.split(" ");
        this.clientId = Integer.parseInt(ObjectMapper[1]);
        this.nPersons = Integer.parseInt(ObjectMapper[2]);
    }

    public Integer getClientId() {
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

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }
}
