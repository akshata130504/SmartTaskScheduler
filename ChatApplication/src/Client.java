import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(reader.readLine());
            String name = consoleReader.readLine();
            writer.println(name);

            Thread readThread = new Thread(() -> {

                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            readThread.start();

            String userMessage;

            while ((userMessage = consoleReader.readLine()) != null) {
                writer.println(userMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}