package kiosk;

public class Ticket {
    private String action;
    private Integer clientId;
    private Integer nPersons;
    private Integer ticketNo;
    private Integer tableNo;
    public Ticket(String message) {
        String[] ObjectMapper = message.split(" ");
        this.action = ObjectMapper[0];
        switch (this.action) {
            case "TicketReq:":
                this.clientId = Integer.parseInt(ObjectMapper[1]);
                this.nPersons = Integer.parseInt(ObjectMapper[2]);
                break;
            case "TicketRep:":
                this.clientId = Integer.parseInt(ObjectMapper[1]);
                this.nPersons = Integer.parseInt(ObjectMapper[2]);
                this.ticketNo = Integer.parseInt(ObjectMapper[3]);
                break;
            case "TicketCall:":
                this.ticketNo = Integer.parseInt(ObjectMapper[1]);
                this.tableNo = Integer.parseInt(ObjectMapper[2]);
                break;
            case "TicketAck:":
                this.ticketNo = Integer.parseInt(ObjectMapper[1]);
                this.tableNo = Integer.parseInt(ObjectMapper[2]);
                this.nPersons = Integer.parseInt(ObjectMapper[3]);
                break;
            case "TableAssign:":
                this.ticketNo = Integer.parseInt(ObjectMapper[1]);
                this.tableNo = Integer.parseInt(ObjectMapper[2]);
                break;
        }
    }

    public String getAction() {
        return action;
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
}
