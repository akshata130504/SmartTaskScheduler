import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;

    public ClientHandler(Socket socket) {

        this.socket = socket;

        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Enter your name:");
            clientName = reader.readLine();

            System.out.println(clientName + " joined the chat");

            Server.broadcastMessage("🔔 " + clientName + " joined the chat", this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        String message;

        try {

            while ((message = reader.readLine()) != null) {

                if (message.startsWith("@")) {

                    String[] parts = message.split(" ", 2);

                    if (parts.length > 1) {

                        String targetUser = parts[0].substring(1);
                        String privateMsg = "(Private) " + clientName + ": " + parts[1];

                        Server.sendPrivateMessage(targetUser, privateMsg);
                    }

                } else {

                    String fullMessage = clientName + ": " + message;

                    System.out.println(fullMessage);

                    Server.broadcastMessage(fullMessage, this);
                }
            }

        } catch (Exception e) {

            System.out.println(clientName + " disconnected");

        } finally {

            Server.removeClient(this);

            Server.broadcastMessage("❌ " + clientName + " left the chat", this);

            try {
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}