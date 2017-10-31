package kiosk;

import java.net.*;
import java.io.*;


public class Server {
    private static final int LISTEN_PORT = 54321;

    public void connect(){
        DataInputStream din;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server listening requests...");
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
