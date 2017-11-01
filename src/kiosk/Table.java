package kiosk;

public class Table {
    private Integer ticketNo;
    private String tableNo;
    private Integer nPersons;
    private Boolean isEmpty;


    public Table (Integer ticketNo, Integer nPersons, String tableNo) {
        this.ticketNo = ticketNo;
        this.nPersons = nPersons;
        this.tableNo = tableNo;
        this.isEmpty = true;
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

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

}
