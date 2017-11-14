package kiosk;

import java.util.ArrayList;

public class Queue {
    public ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private String id;

    public Queue(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    //for debug
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id= " + id + ", tickets= " + tickets.size() + "]";
    }
}
