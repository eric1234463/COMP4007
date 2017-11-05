package kiosk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    private static final int[] TABLE_TYPE_COL = {10,10,8,2,2};

    private Controller controller;

    private ArrayList<Queue> queues = new ArrayList<Queue>();
    private Table table;

    public ArrayList<ArrayList<Table>> tables = new ArrayList<ArrayList<Table>>();

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Listener(Controller controller){
        this.controller = controller;
        int tableRow = 0;

        for(int i = 0; i < 5; i++) {
            tables.add(new ArrayList<Table>());
            String queueID = "#queue_" + i;
            Queue queue = new Queue(queueID);
            queues.add(queue);
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

                    assignQueue(ticket);
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

    public void assignQueue(Ticket ticket) {
        Integer index = this.findTicketIndex(ticket);
        queues.get(index).addTicket(ticket);
        controller.updateQueue(queues.get(index));
    }

    public void ticketRep(Ticket ticket) {
        String clientId = ticket.getClientId();
        int nPersons = ticket.getnPersons();
        int ticketNo = ticket.getTicketNo();
        //System.out.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.println("TicketRep: "+clientId+" "+nPersons+" "+ticketNo+"");
        printWriter.flush();
    }

    public Integer findTicketIndex(Ticket ticket){
        switch (ticket.getnPersons()) {
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
                return 3;
            case 9:
            case 10:
                return 4;
        }
        return 0;
    }

    public boolean tryAssignTable(Ticket ticket) {
        Integer index = this.findTicketIndex(ticket);
        for (Table table: tables.get(index)) {
            if(table.getEmpty()) {
                controller.setSeat(table);
                table.assignTable(ticket.getTicketNo(),ticket.getnPersons());
                controller.updateLastTicketCall(table);
                ticket.setTableNo(table.getTableNo());
                return true;
            }
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
