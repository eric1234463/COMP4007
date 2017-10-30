package kiosk;

public class Table {
    private String action;
    private Integer ticketNo;
    private Integer tableNo;
    private Integer nPersons;
    public Table (String message) {
        String[] ObjectMapper = message.split(" ");
        this.action = ObjectMapper[0];
        switch (this.action) {
            case "TicketAck:":
                this.ticketNo = Integer.parseInt(ObjectMapper[1]);
                this.tableNo = Integer.parseInt(ObjectMapper[2]);
                this.nPersons = Integer.parseInt(ObjectMapper[3]);
                break;
        }
    }

    public String getAction() {
        return action;
    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public Integer getnPersons() {
        return nPersons;
    }
}
