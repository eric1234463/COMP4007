package kiosk;

public class Client {
    private String  tableNo;
    private double totalSpending;
    public Client (String tableNo, String  totalSpending) {
        this.tableNo = tableNo;
        this.totalSpending = Double.parseDouble(totalSpending);
    }

    public String getTableNo() {
        return tableNo;
    }

    public double getTotalSpending() {
        return totalSpending;
    }
}
