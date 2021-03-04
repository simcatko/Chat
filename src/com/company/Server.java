package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server");

        int portNumber = Integer.parseInt(args[0]);
        List<ServerClient> clients = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            int i = 10;
            while (i-- >0) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nove spojenie: " + clientSocket.getInetAddress().toString());
                ServerClient client = new ServerClient(clientSocket, clients);
                client.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

