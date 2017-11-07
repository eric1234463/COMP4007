package kiosk;

import kiosk.Queue;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

public class QueueListener extends TimerTask implements Runnable {
    private ArrayList<Queue> queues = new ArrayList<Queue>();
    private Listener listener;

    public QueueListener(Listener listener) {
        this.listener = listener;
        for (int i = 0; i < 5; i++) {
            Queue queue = new Queue("#queue_" + i);
            this.queues.add(queue);
        }
    }

    public Boolean addTicketToQueue(Ticket ticket) {
        if (this.queues.get(ticket.getIndex()).tickets.size() < 8) {
            this.queues.get(ticket.getIndex()).tickets.add(ticket);
            return true;
        } else {
            return false;
        }
    }

    public void queueToTable(Integer row){
        Integer col = 0;
        Iterator<Ticket> tickets = this.queues.get(row).tickets.iterator();
        while (tickets.hasNext()) {
            Ticket ticket = tickets.next();
            Table table = this.listener.tableListener.checkEmptyTable(row,col);
            if (table != null) {
                if (ticket != null) {
                    this.listener.ticketListener.ticketCall(ticket, table.getTableNo());
                    tickets.remove();
                    this.listener.controller.updateQueue(this.queues.get(row));
                }
            }
            col++;
        }
    }

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    @Override
    public void run(){
        for (int index = 0; index < 5; index++) {
            if (this.queues.get(index).tickets.size() > 0) {
                this.queueToTable(index);
            }
        }
    }

}
