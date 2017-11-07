package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    public Controller controller;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    public TableListener tableListener;
    public QueueListener queueListener;
    public TicketListener ticketListener;
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
        int countTicketReq = 1;

        while(true) {
            Thread queueThread = new Thread(queueListener);
            queueThread.start();
            try {
                String Msg = bufferedReader.readLine();
                System.out.println(Msg);
                mapMsg(Msg,countTicketReq);
                countTicketReq += 1;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mapMsg(String message , int countTicketReq) {
        String[] Msg = message.split(" ");
        String action = Msg[0];
        Ticket ticket;
        switch (action) {
            case "TicketReq:":
                String ticketNo = String.valueOf(countTicketReq);
                ticket = new Ticket(message,ticketNo);
                this.queueListener.addTicketToQueue(ticket);
                this.ticketListener.addTicketToList(ticket);
                this.ticketRep(ticket);
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
}
