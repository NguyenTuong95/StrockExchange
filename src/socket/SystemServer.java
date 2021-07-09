package socket;

import Model.Account;
import Model.OrdBuy;
import Model.OrdSell;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.*;

import Controller.RequestController;
import Controller.TransactionController;

public class SystemServer {
    public static ConcurrentSkipListMap<Account, Socket> listUsers;
    public static ConcurrentSkipListMap<Integer, PriorityBlockingQueue<OrdBuy>> mapOrderBuy;
    public static ConcurrentSkipListMap<Integer, PriorityBlockingQueue<OrdSell>> mapOrderSell;
    public static ExecutorService pool;

    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        try {
            serverSocket = new ServerSocket(20000);
        } catch (IOException exception) {
            System.out.println("Server socket create failed");
            exception.printStackTrace();
        }

        pool = Executors.newCachedThreadPool();
        listUsers = new ConcurrentSkipListMap<>();
        mapOrderSell = new ConcurrentSkipListMap<>();
        mapOrderBuy = new ConcurrentSkipListMap<>();

        TransactionController transactionController = new TransactionController();
        pool.execute(transactionController);

        while (true) {
            RequestController task = null;
            try {
                socket = serverSocket.accept();
                task = new RequestController(socket);
            } catch (IOException e) {
                System.out.println("Server socket is not accepted");
                e.printStackTrace();
            }
            System.out.println("Accepted connect port: " + socket.getPort());
            pool.execute(task);
        }

    }
}
