package kiosk;

public class Table {
    private Integer ticketNo;
    private String tableNo;
    private Integer nPersons;
    private Boolean isEmpty;

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

    public void assignTable() { }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }


}