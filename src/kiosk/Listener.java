package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;


public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    public Controller controller;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    public TableListener tableListener;
    public QueueListener queueListener;
    public TicketListener ticketListener;
    public Integer[] countTicketReq = {10000,20000,30000,40000,50000};
    public Listener(Controller controller){
        this.controller = controller;

        try {
            ServerSocket serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server start...");

            Socket socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            System.out.println("\tWAIT Client Msg ...");
        } catch(Exception ex) {}
    }
    @Override
    public void run() {
        tableListener = new TableListener(this);
        queueListener = new QueueListener(this);
        ticketListener = new TicketListener(this);
        Timer timer = new Timer();
        timer.schedule(queueListener, 0, 500);
        while(true) {
            try {
                String Msg = bufferedReader.readLine();
                System.out.println(Msg);
                mapMsg(Msg);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mapMsg(String message ) {
        String[] Msg = message.split(" ");
        String action = Msg[0];
        Ticket ticket;
        switch (action) {
            case "TicketReq:":
                Integer ticketNo = this.generateTicktetNo(Msg[2]);
                ticket = new Ticket(message,String.valueOf(ticketNo));
                if (this.queueListener.addTicketToQueue(ticket)) {
                    this.ticketListener.addTicketToList(ticket);
                    this.ticketRep(ticket);
                } else {
                    this.queueTooLong(ticket);
                }
                break;
            case "TicketAck:":
                ticket = this.ticketListener.getTicketByTicketNo(Msg[1]);
                this.tableAssign(ticket);
                break;
            case "CheckOut:":
                this.tableListener.checkOut(Msg[1]);
                break;
            default: System.out.println("\t " + "error" + "");
                break;
        }
    }


    public Integer generateTicktetNo(String nPersons){
        Integer index;
        switch (Integer.parseInt(nPersons)){
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
                this.countTicketReq[index]++;
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
    public void ticketRep(Ticket ticket) {
        String clientId = ticket.getClientId();
        int nPersons = ticket.getnPersons();
        String ticketNo = ticket.getTicketNo();
        System.out.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.flush();
    }

    public void ticketCall(Ticket ticket) {
        String ticketNo = ticket.getTicketNo();
        String tableNo = ticket.getTableNo();
        System.out.println("TicketCall: "+ticketNo+" "+tableNo+"");
        printWriter.println("TicketCall: "+ticketNo+" "+tableNo+"");
        printWriter.flush();
    }

    public void tableAssign(Ticket ticket) {
        String ticketNo = ticket.getTicketNo();
        String tableNo = ticket.getTableNo();
        this.tableListener.tableAssign(tableNo,ticket);
        System.out.println("TableAssign: "+ticketNo+" "+tableNo+"");
        printWriter.println("TableAssign: "+ticketNo+" "+tableNo+"");
        printWriter.flush();
    }

    public void queueTooLong(Ticket ticket){
        String clientId = ticket.getClientId();
        Integer nPerson = ticket.getnPersons();
        System.out.println("QueueTooLong: "+clientId+" "+nPerson+"");
        printWriter.println("QueueTooLong: "+clientId+" "+nPerson+"");
        printWriter.flush();

    }

}
