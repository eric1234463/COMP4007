package kiosk;

public class Table {
    private String ticketNo;
    private String tableNo;
    private Integer nPersons;
    private Boolean isEmpty;
    private Integer index;
    public Table (String tableNo, Boolean isEmpty,Integer index) {
        this.tableNo = tableNo;
        this.isEmpty = true;
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public Integer getnPersons() {
        return nPersons;
    }

    public void assignTable(String ticketNo,Integer nPersons) {
        this.ticketNo = ticketNo;
        this.nPersons = nPersons;
        isEmpty = false;
    }


    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) { isEmpty = empty; }

    //for debug
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[tableNo= " + tableNo + ", isEmpty= "+ isEmpty + "]";
    }

}