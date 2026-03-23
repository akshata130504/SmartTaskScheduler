import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChatClientGUI {

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClientGUI() {

        frame = new JFrame("Chat Client");
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        messageField = new JTextField();
        sendButton = new JButton("Send");

        JScrollPane scrollPane = new JScrollPane(chatArea);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        connectToServer();

        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 5000);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            String name = JOptionPane.showInputDialog(frame, "Enter your name:");
            writer.println(name);

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        chatArea.append(message + "\n");
                    }
                } catch (Exception e) {
                    chatArea.append("Disconnected from server.\n");
                }
            });

            readThread.start();

        } catch (Exception e) {
            chatArea.append("Connection failed.\n");
        }
    }

    private void sendMessage() {
        String message = messageField.getText();

        if (!message.isEmpty()) {
            writer.println(message);
            messageField.setText("");
        }
    }

    public static void main(String[] args) {
        new ChatClientGUI();
    }
}