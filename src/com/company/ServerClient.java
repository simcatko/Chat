package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerClient extends Thread {
    private final Socket socket;
    private final List<ServerClient> clients;
    private BufferedReader in;
    private PrintWriter out;

    ServerClient(Socket socket, List<ServerClient> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public void println(String x) {
        System.out.println("Sending message to client");
        this.out.println(x);
    }

    public void run() {
        try {
            System.out.println("MyThread running");
            this.out =
                    new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()));
            this.clients.add(this);
            String inputLine, outputLine;

            // Initiate conversation with client
            out.println("Ahoj client");
            System.out.println("debug");
            while ((inputLine = in.readLine()) != null) {
                outputLine = inputLine;

                for (ServerClient peer : this.clients) {
                    if (peer != this) {
                        peer.println(outputLine);
                    }
                }
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


