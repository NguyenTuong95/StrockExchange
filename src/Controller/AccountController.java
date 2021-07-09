package Controller;

import Model.Account;
import Model.AccountType;
import Util.RequestType;

import java.io.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountController {
    protected static Scanner mScanner = new Scanner(System.in);

    public AccountController() throws SQLException {

    };

    protected boolean signUp(int requestType, ObjectInputStream in, ObjectOutputStream out) throws NoSuchAlgorithmException, SQLException, IOException, ClassNotFoundException {

        String fName;
        String lName;
        String address;
        String phone;
        String username;
        String password;
        String email;

        System.out.println("Please enter first name");
        fName = mScanner.nextLine();

        System.out.println("Please enter last name");
        lName = mScanner.nextLine();

        System.out.println("Please enter address");
        address = mScanner.nextLine();

        System.out.println("Please enter phone");
        phone = mScanner.nextLine();

        System.out.println("Please enter email");
        email = mScanner.nextLine();

        System.out.println("Please enter username");
        username = mScanner.nextLine();

        System.out.println("Please enter password");
        password = mScanner.nextLine();

        Account ac =new Account(fName, lName, address, phone, email, username, password, 0);

        if (requestType == RequestType.ADMIN_SIGN_UP) {
            ac.setIsAdmin(1);
        } 

        Request request = new Request(requestType, ac);

        out.writeObject(request);
        out.flush();

        Response response = null;

        while (response == null) {
            response = (Response) in.readObject();
        }

        if (response != null && response.getResult() == ResponseResult.SUCCESS) {
            System.out.println("Account register sucess!");
            return true;
        }

        return false;
    }

    protected Account login(int requestType, ObjectInputStream in, ObjectOutputStream out) throws SQLException, IOException, ClassNotFoundException {
        String username = null;
        String password = null;

        System.out.println("Please enter username");
        while (username == null) {
            username = mScanner.nextLine();
        }

        System.out.println("Please enter your password");
        password = mScanner.nextLine();
        Account account = null;
        int type = -1;

        if (requestType == RequestType.ADMIN_LOG_IN) {
            type = AccountType.ADMIN;
        } else {
            type = AccountType.TRADER;
        }

        account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setIsAdmin(type);

        Request request = new Request(requestType, account);

        out.writeObject(request);
        out.flush();

        Response response = null;
        while (response == null) {
            response = (Response) in.readObject();
        }

        return response.getAcc();
    }

    public void logout(String username, ObjectInputStream in, ObjectOutputStream out) {
        Account account;
    }
}
