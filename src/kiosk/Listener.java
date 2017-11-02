package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    private static final int[] TABLE_TYPE_NUM = {10,10,8,2,2};

    private Controller controller;

    private Queue queue;
    private Ticket ticket;
    Table table;

    public ArrayList<ArrayList<Table>> tables = new ArrayList<ArrayList<Table>>();

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Listener(Controller controller){
        this.controller = controller;
        int current_tableRow = 0;

        for(int i = 0; i < 5; i++) {
            tables.add(new ArrayList<Table>());
        }

        for(int i = 0; i < 5; i++) {
            //for(int tableRow = 0; tableRow < 2 || current_tableRow < 9; tableRow++) {
                for(int tableCol = 0; tableCol < TABLE_TYPE_NUM[i] ; tableCol++) {
                    tables.get(i).add(table = new Table(String.valueOf(current_tableRow)+"_"+String.valueOf(tableCol)));
                    System.out.print(String.valueOf(current_tableRow)+"_"+String.valueOf(tableCol)+" ");
                }
                current_tableRow++;
            //}
        }

        System.out.println(tables.get(0));

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
        int countTicketReq = 0;
        queue = new Queue();
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
                ticket = new Ticket(message);
                ticket.setTicketNo(countTicketReq);
                ticketRep(ticket);

                queue.addTicket(ticket);

                break;
            case "TicketAck:":

                break;
            case "CheckOut:":

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
