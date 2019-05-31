package ro.ubb.labproblems.Tcp;

import ro.ubb.labproblems.Domain.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    private ExecutorService executorService;
    private int port;

    private Map<String, UnaryOperator<Message>> methodHandlers;

    public TcpServer(ExecutorService executorService, int port) {
        this.executorService = executorService;
        this.port = port;

        methodHandlers = new HashMap<>();
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    private void send(ObjectOutputStream oos, Object obj) throws IOException {
        oos.writeObject(obj);
    }

    private Message receive(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (Message)ois.readObject();
    }

    public void start() {
        System.out.println("[SERVER] Starting...");
        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("[SERVER] Successfully started");
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("[SERVER] Client connected");
                executorService.submit(new ClientHandler(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("[SERVER] There was a problem while starting the server", e);
        }
    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())
            ) {
                Message request = receive(ois);
                System.out.println("[SERVER] Receiving request: " + request.getHeader());

                Message response = methodHandlers.get(request.getHeader()).apply(request);

                System.out.println("[SERVER] Sending response: " + response.getHeader());
                send(oos, response);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("server - data " +
                        "exchange" +
                        " " +
                        "error", e);
            } finally {
                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
