package socket;

import Model.Account;
import Model.Stock;
import Util.RequestType;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import Controller.AccountController;
import Database.SelectData;
import java.util.Scanner;

public class SystemClient extends AccountController{

    private static AccountController mTraderController;
    private static Account mTrader;
    private static SelectData selectData;

    public SystemClient() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {

        mTraderController = new SystemClient();
        mTrader = null;
        selectData = SelectData.getInstance();
        try {
            int choice = -1;
            String read = null;
            while (true) {
                System.out.println("Please chose :1 - login, 2 - sign up");
                read = mScanner.nextLine();
                choice = Integer.parseInt(read);

                if (choice != 1 && choice != 2) {
                    System.out.println("Please chose log in or sign up by press num 1 or 2");
                } else {
                    break;
                }
            }

            Socket socket = new Socket("localhost", 20000);
            System.out.println("Client socket port" + socket.getLocalPort());

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            if (choice == 2) {
                doSignUp(in, out);
            } else {
                doLogin(in, out);
            }

            if (socket != null)
                socket.close();
            if (in != null)
                in.close();
            if (out != null)
                out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void doSignUp(ObjectInputStream in, ObjectOutputStream out) throws SQLException, NoSuchAlgorithmException, IOException, ClassNotFoundException {

        while (true) {
            if (mTraderController.signUp(RequestType.TRADER_SIGN_UP, in , out)) {
                System.out.println("Do you want to log in now? 1 - Yes, 2 - No");

                String read = mScanner.nextLine();

                int choice = Integer.parseInt(read);

                if (choice == 1) {
                    doLogin(in, out);
                    break;
                } else if(choice == 2) {
                    break;
                }
            }
        }
    }

    private static void doLogin(ObjectInputStream in, ObjectOutputStream out) throws SQLException, IOException, ClassNotFoundException {
        mTrader = mTraderController.login(RequestType.TRADER_LOG_IN, in, out);

        int choice = -1;
        String read = null;
        while (mTrader == null) {
            System.out.println("Do you want to try again? 1 - Yes, 2 - No");

            read = mScanner.nextLine();

            choice = Integer.parseInt(read);
            System.out.println("We choice : " + choice);
            if (choice == 2) {
                return;
            } else if (choice == 1) {
                mTrader = mTraderController.login(RequestType.TRADER_LOG_IN, in, out);
            } else {
                System.out.println("Please chose 1 or 2!!!");
            }
        }

        while (true) {
            showMenu();
            read = mScanner.nextLine();
            choice = Integer.parseInt(read);

            switch (choice) {
                case 1:
                    mTraderController.logout(mTrader.getUsername(), in, out);
                    return;
                case 2:
                    getListCurrentStock();
                    break;
                case 3:
                    addBalance();
                    break;
                case 4:

                break;
            }
        }
    }

    private static void addBalance() {
        
    }

    public static void addAmount(){
        
    }

    private static void getListCurrentStock() {
        String sql = "SELECT * FROM stock;";
          List<Stock> lstData = selectData.selectDataStocks(sql);
          for(Stock item : lstData){
            System.out.println(item);
            
          }
    }

    private static void showMenu() {
        System.out.println("You are logged in with trader account id: " + mTrader.getUsername());
        System.out.println("------------------------------------------------");
        System.out.println("1. Log out");
        System.out.println("2. Get list current stock");
        System.out.println("3. Add balance");
        System.out.println("4. Add Amount");

        System.out.println("------------------------------------------------");
        System.out.println("Please chose your option!!!!");
    }
}
