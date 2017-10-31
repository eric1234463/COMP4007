package kiosk;

public class Queue {
    private Integer clientID;
    private Integer nPersons;
    public Queue(String message) {
        String[] ObjectMapper = message.split(" ");
        this.clientID = Integer.parseInt(ObjectMapper[2]);
        this.nPersons = Integer.parseInt(ObjectMapper[3]);
    }

    public Integer getClientID() {
        return clientID;
    }

    public Integer getnPersons() {
        return nPersons;
    }
}
