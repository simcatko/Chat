package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClient extends Thread {
    private final Socket socket;

    ServerClient(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {


            System.out.println("MyThread running");
            PrintWriter out =
                    new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()));
            String inputLine, outputLine;

            // Initiate conversation with client
            out.println("Ahoj client");

            while ((inputLine = in.readLine()) != null) {
                outputLine = inputLine;
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


