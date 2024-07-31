import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(6001);
            System.out.println("Server started, waiting for clients...");

            // Accept two clients
            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 connected.");
            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 connected.");

            // Create input and output streams for both clients
            DataInputStream din1 = new DataInputStream(client1.getInputStream());
            DataOutputStream dout1 = new DataOutputStream(client1.getOutputStream());
            DataInputStream din2 = new DataInputStream(client2.getInputStream());
            DataOutputStream dout2 = new DataOutputStream(client2.getOutputStream());

            // Start a thread for each client to handle incoming messages
            new Thread(() -> handleClient(din1, dout2)).start();
            new Thread(() -> handleClient(din2, dout1)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(DataInputStream input, DataOutputStream output) {
        try {
            while (true) {
                String message = input.readUTF();
                output.writeUTF(message); // Forward the message to the other client
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
