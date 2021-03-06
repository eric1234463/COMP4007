package kiosk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 */
public class Listener implements Runnable {
    private static final int LISTEN_PORT = 54321;
    public Controller controller;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    public TableListener tableListener;

    QueueListener[] queueListeners = new QueueListener[5];

    public Integer[] countTicketReq = {10001, 20001, 30001, 40001, 50001};

    public Listener(Controller controller) {
        this.controller = controller;
        try {
            ServerSocket serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server start...");
            Log.logger.log(Level.FINE, "Registering Server");

            Socket socket = serverSocket.accept();
            Log.logger.log(Level.FINE, "Server Created");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            Log.logger.log(Level.FINE, "Server Started");

            System.out.println("\tWAIT Client Msg ...");
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        tableListener = new TableListener(this);

        for (int i = 0; i < 5; i++) {
            queueListeners[i] = new QueueListener(this, i);
        }

        while (true) {
            try {
                String Msg = bufferedReader.readLine();
                //System.out.println(Msg);
                mapMsg(Msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method process message string and respond to different message type accordingly.
     * <p>
     * If the message is not valid, error message will be printed.
     *
     * @param message raw message string.
     */
    public void mapMsg(String message) {
        Log.logger.log(Level.INFO, "New msg: NetIn: [" + message + "]");
        String[] Msg = message.split(" ");
        String action = Msg[0];
        Ticket ticket;
        switch (action) {
            case "TicketReq:":
                //System.out.println(System.currentTimeMillis());
                Integer ticketNo = this.generateTicktetNo(Msg[2]);
                ticket = new Ticket(message, String.valueOf(ticketNo));
                int index = ticketNo / 10000;
                if (this.queueListeners[index - 1].addTicketToQueue(ticket)) {
                    Log.logger.log(Level.INFO, "\n" + this.queueListeners[0].getQueue().getId() + ": " + this.queueListeners[0].getQueue().tickets.size() + "\n" +
                            this.queueListeners[1].getQueue().getId() + ": " + this.queueListeners[1].getQueue().tickets.size() + "\n" +
                            this.queueListeners[2].getQueue().getId() + ": " + this.queueListeners[2].getQueue().tickets.size() + "\n" +
                            this.queueListeners[3].getQueue().getId() + ": " + this.queueListeners[3].getQueue().tickets.size() + "\n" +
                            this.queueListeners[4].getQueue().getId() + ": " + this.queueListeners[4].getQueue().tickets.size() + "\n");
                    this.ticketRep(ticket);
                } else {
                    this.queueTooLong(ticket);
                }

                break;
            case "TicketAck:":
                this.tableAssign(Msg[1], Msg[2]);
                this.tableListener.tableAssign(Msg[2]);
                break;
            case "CheckOut:":
                this.tableListener.checkOut(Msg[1]);
                break;
            default:
                System.out.println("\t " + "error" + "");
                break;
        }
    }

    /**
     * Input the number of person, assign a ticket number according to table type, return ticket number
     * <p>
     *
     * @param nPersons number of person
     *
     * @return ticket number
     */
    public Integer generateTicktetNo(String nPersons) {
        Integer index;
        switch (Integer.parseInt(nPersons)) {
            case 1:
            case 2:
                index = 0;
                return this.countTicketReq[index]++;
            case 3:
            case 4:
                index = 1;
                return this.countTicketReq[index]++;
            case 5:
            case 6:
                index = 2;
                return this.countTicketReq[index]++;
            case 7:
            case 8:
                index = 3;
                return this.countTicketReq[index]++;
            case 9:
            case 10:
                index = 4;
                return this.countTicketReq[index]++;
        }
        return 0;
    }

    /**
     * Send message to client server for ticket respond
     * <p>
     * This method have no return
     *
     * @param ticket ticket being responded
     */
    public void ticketRep(Ticket ticket) {
        String clientId = ticket.getClientId();
        int nPersons = ticket.getnPersons();
        String ticketNo = ticket.getTicketNo();
        Log.logger.log(Level.FINER, "NetOut: [TicketRep: " + clientId + " " + nPersons + " " + ticketNo + "]");
        //System.out.println("TicketRep: " + clientId + " " + nPersons + " " + ticketNo + "");
        printWriter.println("TicketRep: " + clientId + " " + nPersons + " " + ticketNo + "");
        printWriter.flush();
    }

    /**
     * Send message to client server saying assigning a table to a ticket
     * <p>
     * This method have no return
     *
     * @param ticketNo ticket number that being assigned a table
     * @param tableNo  table number to be assign to the ticket
     */
    public void ticketCall(String ticketNo, String tableNo) {
        System.out.println("TicketCall: " + ticketNo + " " + tableNo + "");
        printWriter.println("TicketCall: " + ticketNo + " " + tableNo + "");
        Log.logger.log(Level.FINER, "NetOut: [TicketCall: " + ticketNo + " " + tableNo + "]");
        printWriter.flush();
    }

    /**
     * Send message to client server saying assigning a table to a ticket
     * <p>
     * This method have no return
     *
     * @param ticketNo ticket number that being assigned a table
     * @param tableNo  table number to be assign to the ticket
     */
    public void tableAssign(String ticketNo, String tableNo) {
        //System.out.println("get ACK and do TableAssign: " + ticketNo + " " + tableNo + "");
        printWriter.println("TableAssign: " + ticketNo + " " + tableNo + "");

        printWriter.flush();
    }

    /**
     * Send message to client server saying the queue is too long for this ticket
     * <p>
     * This method have no return
     *
     * @param ticket ticket that is told be in long queue
     */
    public void queueTooLong(Ticket ticket) {
        String clientId = ticket.getClientId();
        Integer nPerson = ticket.getnPersons();
        System.out.println("QueueTooLong: " + clientId + " " + nPerson + "");
        printWriter.println("QueueTooLong: " + clientId + " " + nPerson + "");
        Log.logger.log(Level.FINER, "NetOut: [QueueTooLong: " + clientId + " " + nPerson + "]");

        printWriter.flush();

    }

}
