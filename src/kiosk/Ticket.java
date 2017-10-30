package kiosk;

public class Ticket {
    private String action;
    private String clientId;
    private String nPersons;
    private String ticketNo;
    private String tableNo;
    public Ticket(String message) {
        String[] ObjectMapper = message.split(" ");
        this.action = ObjectMapper[0];
        switch (this.action) {
            case "TicketReq:":
                this.clientId = ObjectMapper[1];
                this.nPersons = ObjectMapper[2];

                break;
            case "TicketRep:":
                this.clientId = ObjectMapper[1];
                this.nPersons = ObjectMapper[2];
                this.ticketNo = ObjectMapper[3];
                break;
            case "TicketCall:":
                this.ticketNo = ObjectMapper[1];
                this.tableNo = ObjectMapper[2];
                break;
            case "TicketAck:":
                this.ticketNo = ObjectMapper[1];
                this.tableNo = ObjectMapper[2];
                this.nPersons = ObjectMapper[3];
                break;
            case "TableAssign:":
                this.ticketNo = ObjectMapper[1];
                this.tableNo = ObjectMapper[2];
                break;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getnPersons() {
        return nPersons;
    }

    public void setnPersons(String nPersons) {
        this.nPersons = nPersons;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
}
