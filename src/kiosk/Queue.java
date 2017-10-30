package kiosk;

public class Queue {
    private String action;
    private Integer clientID;
    private Integer nPersons;
    public Queue(String message) {
        String[] ObjectMapper = message.split(" ");
        this.action = ObjectMapper[0];
        switch (this.action) {
            case "QueueTooLong:":
                this.clientID = Integer.parseInt(ObjectMapper[2]);
                this.nPersons = Integer.parseInt(ObjectMapper[3]);
                break;
        }
    }

    public String getAction() {
        return action;
    }

    public Integer getClientID() {
        return clientID;
    }

    public Integer getnPersons() {
        return nPersons;
    }
}
