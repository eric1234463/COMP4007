package kiosk;

public class Ticket {
    public String clientId;
    public Integer nPersons;
    public String ticketNo;
    public String tableNo;
    private Integer index;

    public Ticket(String message, String ticketNo) {
        String[] ObjectMapper = message.split(" ");
        this.ticketNo = ticketNo;
        this.clientId = ObjectMapper[1];
        this.nPersons = Integer.parseInt(ObjectMapper[2]);
        switch (this.nPersons) {
            case 1:
            case 2:
                this.index = 0;
                break;
            case 3:
            case 4:
                this.index = 1;
                break;
            case 5:
            case 6:
                this.index = 2;
                break;
            case 7:
            case 8:
                this.index = 3;
                break;
            case 9:
            case 10:
                this.index = 4;
                break;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public String getClientId() {
        return clientId;
    }

    public Integer getnPersons() {
        return nPersons;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[ticketNo= " + this.ticketNo + ", tableNo= " + this.tableNo + "]";
    }
}
