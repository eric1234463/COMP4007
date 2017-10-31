package kiosk;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable{
    private static final int LISTEN_PORT = 54321;
    private Contoller contoller;

    public Listener(Contoller contoller){
        this.contoller = contoller;
    }

    @Override
    public void run() {
        DataInputStream din;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Contoller listening requests...");
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    din = new DataInputStream(socket.getInputStream());
                    System.out.println("\tWAIT Command ...");
                    while (true) {
                        String Command = din.readLine();
                        System.out.println("\t " + Command + "");
                    }
                } catch(Exception ex) {
                    System.out.println("\tClient Disconnected ...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
