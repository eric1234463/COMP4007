package kiosk;

import kiosk.Queue;


import java.util.ArrayList;
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
        if (this.queues.get(ticket.getIndex()).tickets.size() < 5) {
            this.queues.get(ticket.getIndex()).tickets.add(ticket);
            return true;
        } else {
            return false;
        }
    }

    public void queueToTable(Integer index){

        Table table = this.listener.tableListener.checkEmptyTable(index);
        if ( table != null ) {
            Ticket ticket = this.queues.get(index).tickets.get(0);
            if( ticket != null) {
                this.listener.ticketListener.ticketCall(ticket, table.getTableNo());
                this.queues.get(index).tickets.remove(ticket);
                this.listener.controller.updateQueue(this.queues.get(index));
            }
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
