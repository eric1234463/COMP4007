package kiosk;

import java.util.ArrayList;

public class Queue {
    public ArrayList<ArrayList<Ticket>> tickets = new ArrayList<ArrayList<Ticket>>();

    public Queue(){
        for(int i = 0; i < 5; i++) {
            tickets.add(new ArrayList<Ticket>());
        }
    }
    public void addTicket(Ticket ticket) {
        switch (ticket.nPersons){
            case 1:
            case 2:
                tickets.get(0).add(ticket);
                break;
            case 3:
            case 4:
                tickets.get(1).add(ticket);
                break;
            case 5:
            case 6:
                tickets.get(2).add(ticket);
                break;
            case 7:
            case 8:
                tickets.get(3).add(ticket);
                break;
            case 9:
            case 10:
                tickets.get(4).add(ticket);
                break;
        }
    }

}
