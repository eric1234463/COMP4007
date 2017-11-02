package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    private static final int[] TABLE_TYPE_COL = {10,10,8,2,2};

    private Controller controller;

    private Queue queue;
    private Table table;

    public ArrayList<ArrayList<Table>> tables = new ArrayList<ArrayList<Table>>();

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Listener(Controller controller){
        this.controller = controller;
        int tableRow = 0;

        for(int i = 0; i < 5; i++) {
            tables.add(new ArrayList<Table>());
        }

        for(int i = 0; i < 5; i++) {
            for(int tableCol = 0; tableCol < TABLE_TYPE_COL[i] ; tableCol++) {
                tables.get(i).add(table = new Table("#table_" + String.valueOf(tableRow)+"_"+String.valueOf(tableCol),true));
                //System.out.print(String.valueOf(current_tableRow)+"_"+String.valueOf(tableCol)+" ");
            }
            tableRow++;
        }

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
        int countTicketReq = 1;
        queue = new Queue();
        while(true) {
            try {
                String Msg = bufferedReader.readLine();
                System.out.println(Msg);
                mapMsg(Msg, countTicketReq);
                countTicketReq+=1;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void mapMsg(String message, int countTicketReq) {
        String[] Msg = message.split(" ");
        String action = Msg[0];
        Ticket ticket;

        switch (action) {
            case "TicketReq:":
                ticket = new Ticket(message);
                ticket.setTicketNo(countTicketReq);
                ticketRep(ticket);

                if (tryAssignTable(ticket)){
                    try {
                        Thread.sleep(1000);
                        ticketCall(ticket);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    queue.addTicket(ticket);
                }

                break;
            case "TicketAck:":
                ticket = new Ticket(Integer.valueOf(Msg[1]),Msg[2],Integer.valueOf(Msg[3]));
                TableAssign(ticket);
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
        //System.out.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.flush();
    }

    public boolean tryAssignTable(Ticket ticket) {

        switch (ticket.getnPersons()) {
            case 1:
            case 2:
                for (Table table: tables.get(0)) {
                    if(table.getEmpty()) {
                        table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                        controller.setSeat(table);
                        ticket.setTableNo(table.getTableNo());
                        return true;
                    }
                }
                break;
            case 3:
            case 4:
                for (Table table: tables.get(1)) {
                    if(table.getEmpty()) {
                        table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                        controller.setSeat(table);
                        ticket.setTableNo(table.getTableNo());
                        return true;
                    }
                }
                break;
            case 5:
            case 6:
                for (Table table: tables.get(2)) {
                    if(table.getEmpty()) {
                        table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                        controller.setSeat(table);
                        ticket.setTableNo(table.getTableNo());
                        return true;
                    }
                }
                break;
            case 7:
            case 8:
                for (Table table: tables.get(3)) {
                    if(table.getEmpty()) {
                        table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                        controller.setSeat(table);
                        ticket.setTableNo(table.getTableNo());
                        return true;
                    }
                }
                break;
            case 9:
            case 10:
                for (Table table: tables.get(4)) {
                    if(table.getEmpty()) {
                        table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                        controller.setSeat(table);
                        ticket.setTableNo(table.getTableNo());
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public void ticketCall(Ticket ticket) {
        int ticketNo = ticket.getTicketNo();
        String tableNo = ticket.getTableNo();

        printWriter.println("TicketCall: "+ticketNo+" "+tableNo+"");
        printWriter.flush();
    }

    public void TableAssign(Ticket ticket) {
        int ticketNo = ticket.getTicketNo();
        String tableNo = ticket.getTableNo();

        printWriter.println("TableAssign: "+ticketNo+" "+tableNo+"");
        printWriter.flush();
    }

}
