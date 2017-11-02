package kiosk;

public class Table {
    private Integer ticketNo;
    private String tableNo;
    private Integer nPersons;
    private Boolean isEmpty;

    public Table (String tableNo, Boolean isEmpty) {
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

    public void assignTable(int ticketNo) {
        this.ticketNo = ticketNo;
        isEmpty = false;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) { isEmpty = true; }

    //for debug
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[tableNo= " + tableNo + ", isEmpty= "+ isEmpty + "]";
    }
}