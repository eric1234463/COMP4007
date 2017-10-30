package kiosk;

public class Client {
    private String action;
    private Integer tableNo;
    private double totalSpending;
    public Client (String message) {
        String[] ObjectMapper = message.split(" ");
        this.action = ObjectMapper[0];
        switch (this.action) {
            case "CheckOut:":
                this.tableNo = Integer.parseInt(ObjectMapper[1]);
                this.totalSpending = Double.parseDouble(ObjectMapper[2]);
                break;
        }
    }

    public String getAction() {
        return action;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public double getTotalSpending() {
        return totalSpending;
    }
}
