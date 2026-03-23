import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000");

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);

                new Thread(clientHandler).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {

        for (ClientHandler client : clients) {

            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void sendPrivateMessage(String username, String message) {

        for (ClientHandler client : clients) {

            if (client.getClientName().equals(username)) {
                client.sendMessage(message);
                break;
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}