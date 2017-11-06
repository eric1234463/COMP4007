package kiosk;

public class Ticket {
    public String clientId;
    public Integer nPersons;
    public Integer ticketNo;
    public String tableNo;
    public long offerTime;

    public Ticket(int ticketNo, String tableNo, int nPersons) {
        this.ticketNo = ticketNo;
        this.tableNo = tableNo;
        this.nPersons = nPersons;
    }

    public Ticket(String message) {
        String[] ObjectMapper = message.split(" ");
        this.clientId = ObjectMapper[1];
        this.nPersons = Integer.parseInt(ObjectMapper[2]);
        this.offerTime=System.currentTimeMillis();
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

    public String getTableNo() {
        return tableNo;
    }

    public void setTicketNo(Integer ticketNo) { this.ticketNo = ticketNo; }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
}
