package kiosk;

public class Ticket {
    private String clientId;
    private Integer nPersons;
    private Integer ticketNo;
    private Integer tableNo;
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

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
        //System.out.println("\t " + ticketNo + "");
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }
}
