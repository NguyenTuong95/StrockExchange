package Controller;

import Model.Account;

import Util.RequestType;
import socket.SystemServer;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import Database.InsertData;
import Database.SelectData;

public class RequestController implements Runnable {
    private Socket mScoket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private SelectData selectData;
    private InsertData insertData;
    
    public RequestController(Socket socket) {
        this.mScoket = socket;

        System.out.println("Created Requestcontroller " + this);
    }

    public RequestController() {

    }

    public void call() throws IOException, ClassNotFoundException, SQLException {


    }

    private boolean isLogin(int requestType, Account account) throws IOException {
        if (!SystemServer.listUsers.containsValue(this.mScoket)) {
            String msg = "You have to log in to do this function!!!";

            Response response = new Response(requestType, ResponseResult.FAILED, msg);

            System.out.println("Check log in status : " + response.toString());

            out.writeObject(response);
            out.flush();

            return false;
        }

        return true;
    }


    private void doLogin(int requestType, Account acc) throws SQLException, IOException {
        System.out.println("Doing log in");
        String username, password;

        username = acc.getUsername();
        password = acc.getPassword();

        String sql = "SELECT * FROM infor_account WHERE username = '" + username + "' AND password = " + "'" + password +"'" + ";";

        selectData = SelectData.getInstance();

        List<Account> lstData = (List<Account>) selectData.selectDataAccount(sql);
      

        Response response = null;

        if (lstData.isEmpty() != true) {
            Account account = lstData.get(0);

            SystemServer.listUsers.put(account, this.mScoket);
            response = new Response(requestType, ResponseResult.SUCCESS, account);
        } else {
            response = new Response(requestType, ResponseResult.FAILED, acc);
        }

        System.out.println(response.toString());

        out.writeObject(response);
        out.flush();
    }

    private void doSignUp(int requestType, Account acc) throws SQLException, IOException, ClassNotFoundException {

        String username;
        username = acc.getUsername();

        selectData = SelectData.getInstance();
        String sql = "SELECT * FROM infor_account WHERE username = '" + username + "'"  + ";";

        selectData = SelectData.getInstance();
        insertData = InsertData.getInstance();

        List<Account> lstData = (List<Account>) selectData.selectDataAccount(sql);
        
        Response response = null;

        if (lstData.isEmpty() != true) {
         
            String msg = "Account " + acc.getUsername() + " already exists";
            response = new Response(requestType, ResponseResult.FAILED, msg, acc);
            out.writeObject(response);
            out.flush();
            return;
        }else{
            insertData.insertAccountData(acc);
            String msg = "Create account " + acc.getUsername() + "success!";
            response = new Response(requestType, ResponseResult.SUCCESS, msg, acc);
            out.writeObject(response);
            out.flush();
            return;
        }

    }

    @Override
    public void run() {
        System.out.println("run");
        try {
            Request rq = null;

            out = new ObjectOutputStream(mScoket.getOutputStream());
            in = new ObjectInputStream(mScoket.getInputStream());

            while (true) {

                rq = (Request) in.readObject();

                int requestType = rq.getType();
                Account acc = rq.getAcc();

                switch (requestType) {
                    case RequestType.TRADER_LOG_IN:
                        doLogin(requestType, acc);
                        break;
                    case RequestType.ADMIN_LOG_IN:
                        doLogin(requestType, acc);
                        break;
                    case RequestType.TRADER_SIGN_UP:
                        doSignUp(requestType, acc);
                        break;
                    case RequestType.ADMIN_SIGN_UP:
                        doSignUp(requestType, acc);
                        break;
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
