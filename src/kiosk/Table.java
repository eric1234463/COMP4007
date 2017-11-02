package kiosk;

public class Table {
    private Integer ticketNo;
    private String tableNo;
    private Integer nPersons;
    private Integer row;
    private Integer col;

    public Table (String tableNo) {
        this.tableNo = tableNo;

    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public Integer getnPersons() {
        return nPersons;
    }

    public void assignTable(){ }
}
