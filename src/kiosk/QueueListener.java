package kiosk;

import java.util.Iterator;
import java.util.logging.Level;

public class QueueListener implements Runnable {
    //public ArrayList<Queue> queue = new ArrayList<Queue>();
    private Listener listener;
    private Queue queue;
    private int index;

    public QueueListener(Listener listener, int index) {
        Thread t = new Thread(this);
        t.start();
        this.listener = listener;
        this.index = index;
        this.queue = new Queue("#queue_" + index);
    }

    public Boolean addTicketToQueue(Ticket ticket) {
        if (this.queue.tickets.size() < 20) {
            this.queue.tickets.add(ticket);
            this.listener.controller.updateQueue(this.queue.getId(), this.queue.tickets.size());
            return true;
        } else {
            return false;
        }
    }

    public void queueToTable(int queueIndex) {
        Iterator<Ticket> tickets = this.queue.tickets.iterator();
        while (tickets.hasNext()) {
            Ticket ticket = tickets.next();
            Table table = this.listener.tableListener.checkEmptyTable(queueIndex);
            if (table != null) {
                tickets.remove();
                table.setTicketNo(ticket.ticketNo);
                table.setnPersons(ticket.nPersons);

                ticket.setTableNo(table.getTableNo());
                this.listener.ticketCall(ticket.ticketNo, ticket.tableNo);

                this.listener.controller.updateLastTicketCall(queueIndex, ticket.ticketNo);
            }
        }
        this.listener.controller.updateQueue(this.queue.getId(), this.queue.tickets.size());
    }

    @Override
    public void run() {
        while (true) {
            if (this.queue.tickets.size() > 0) {
                this.queueToTable(index);
            }
            try {
                Thread.sleep(250);
                this.listener.tableListener.checkTableStatus(index);
            } catch (InterruptedException e) {
            }
        }
    }

    public Queue getQueue() {
        return queue;
    }
}
