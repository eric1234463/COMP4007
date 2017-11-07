package kiosk;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TicketListener {
    private ArrayList<ArrayList<Ticket>> tickets = new ArrayList<ArrayList<Ticket>>();
    private Listener listener;
    public TicketListener(Listener listener) {
        this.listener = listener;
        for(int i = 0; i < 5; i++) {
            tickets.add(new ArrayList<Ticket>());
        }
    }
    public void addTicketToList(Ticket ticket){
        Integer index = ticket.getIndex();
        tickets.get(index).add(ticket);
    }
    public Ticket getTicketByTicketNo(String ticketNo) {
        for(int index = 0 ; index < 5 ; index ++) {
            for (Ticket ticket : tickets.get(index)) {
                if(ticket.ticketNo.equals(ticketNo)){
                    return ticket;
                }
            }
        }
        return null;
    }
    public void ticketCall(Ticket ticket,String tableNo) {
        ticket.setTableNo(tableNo);
        this.listener.ticketCall(ticket);
    }



}