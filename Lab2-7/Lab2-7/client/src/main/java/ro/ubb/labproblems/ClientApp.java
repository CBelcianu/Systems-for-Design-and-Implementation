package ro.ubb.labproblems;

import ro.ubb.labproblems.Service.ClientService;
import ro.ubb.labproblems.Tcp.TcpClient;
import ro.ubb.labproblems.UI.ClientConsole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(CommonService.SERVER_HOST, CommonService.SERVER_PORT);
        CommonService crs = new ClientService(executorService, tcpClient);
        ClientConsole ui = new ClientConsole(crs);
        ui.runConsole();
        executorService.shutdownNow();
        System.out.println("DISCONNECTED");
    }
}
