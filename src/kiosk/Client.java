package kiosk;

public class Client {
    private Integer tableNo;
    private double totalSpending;
    public Client (String message) {
        String[] ObjectMapper = message.split(" ");
        this.tableNo = Integer.parseInt(ObjectMapper[1]);
        this.totalSpending = Double.parseDouble(ObjectMapper[2]);
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public double getTotalSpending() {
        return totalSpending;
    }
}
