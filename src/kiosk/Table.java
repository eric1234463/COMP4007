package kiosk;

public class Table {
    private Integer ticketNo;
    private Integer tableNo;
    private Integer nPersons;
    private Integer row;
    private Integer col;
    public Table (Integer ticketNo,Integer nPersons,Integer tableNo) {
        this.ticketNo = ticketNo;
        this.nPersons = nPersons;
        this.tableNo = tableNo;

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

    public void assignTable(){

    }
}
