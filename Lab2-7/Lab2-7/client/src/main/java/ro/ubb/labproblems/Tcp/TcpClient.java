package ro.ubb.labproblems.Tcp;

import ro.ubb.labproblems.Domain.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpClient {
    private String host;
    private int port;

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void send(ObjectOutputStream oos, Object obj) throws IOException {
        oos.writeObject(obj);
    }

    private Message receive(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (Message)ois.readObject();
    }

    public Message sendAndReceive(Message request) {
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            System.out.println("[CLIENT] Sending request " + request.getHeader());
            send(oos, request);

            Message response = receive(ois);
            System.out.println("[CLIENT] Receiving response: " + response.getHeader());

            return response;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("[CLIENT] There was a problem while connecting to the server.");
        }
    }
}
