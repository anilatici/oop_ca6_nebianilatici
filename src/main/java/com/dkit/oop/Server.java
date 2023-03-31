package com.dkit.oop;

import com.dkit.oop.DAOs.MySqlTeamDao;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() {
        try
        {
            ServerSocket ss = new ServerSocket(8080); // Create a server socket to listen for connections 8080
            System.out.println("Server started");
            int clientNumber = 0;

            while (true) {
                Socket socket = ss.accept(); // Wait for a client to connect
                System.out.println("Client connected");
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " connected");
                System.out.println("Starting thread for client " + clientNumber);

                Thread t = new Thread(new ClientHandler(socket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started for client " + clientNumber);
                System.out.println("Server: Listening for further connections");
            }

        } catch (IOException e) {
            System.out.println("Server: IO exception: " + e);
        }
        System.out.println("Server: Server is shutting down");
    }

    public class ClientHandler implements Runnable
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true);

                this.socket = clientSocket;
                this.clientNumber = clientNumber;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String message;
            MySqlTeamDao serverDao = new MySqlTeamDao();
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                    if (message.startsWith("Echo"))
                    {
                        message = message.substring(5); // strip off the 'Echo ' part
                        socketWriter.println(message);  // send message to client
                    }
                    else if (message.startsWith("Find Team By ID:"))
                    {
                        String jsonQuery = message.substring(16);
                        Query sqlQuery = gson.fromJson(jsonQuery, Query.class);

                        Team team = serverDao.findTeamById(sqlQuery);
                        String jsonTeam = gson.toJson(team);
                        socketWriter.println(jsonTeam);
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Server: (ClientHandler): Client " + clientNumber + " disconnected");
        }
    }
}
