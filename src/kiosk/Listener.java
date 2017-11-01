package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    private Contoller contoller;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private ServerSocket serverSocket = null;

    public Listener(Contoller contoller){
        this.contoller = contoller;
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server start...");

            Socket socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            System.out.println("\tWAIT Client Msg ...");
        } catch(Exception ex) {}
    }

    @Override
    public void run() {
        int countTicketReq = 0;
        while(true) {
            try {
                String Msg = bufferedReader.readLine();
                System.out.println(Msg);
                mapMsg(Msg, countTicketReq);
                countTicketReq++;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void mapMsg(String message, int countTicketReq) {
        String[] Msg = message.split(" ");
        String action = Msg[0];

        switch (action) {
            case "TicketReq:":
                Ticket ticket = new Ticket(message);
                ticket.setTicketNo(countTicketReq);
                ticketRep(ticket);
                break;
            case "TicketCall:":

                break;
            case "TicketAck:":

                break;
            case "TableAssign:":

                break;

            default: System.out.println("\t " + "error" + "");
                break;
        }
    }

    public void ticketRep(Ticket ticket) {
        String clientId = ticket.getClientId();
        int nPersons = ticket.getnPersons();
        int ticketNo = ticket.getTicketNo();
        System.out.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.flush();
    }
}
