package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import Model.Account;
import Model.Company;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Trader;
import Model.Transaction;
import Model.TransactionDetail;
import Util.Utility;

public class InsertData {
    private Utility utility;
    private Connection connection = null;
    public InsertData(){
        utility = Utility.getInstance();
        connection = utility.connectDatabase();
    }

    public void insertAccountData(Account account) {
        if(connection == null){       
            return;
        }

        String sql = "INSERT INTO Infor_Account (fName, lName, address, phone, email, username, password, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, account.getFName());
            statement.setString(2, account.getLName());
            statement.setString(3, account.getAddress());
            statement.setString(4, account.getPhone());
            statement.setString(5, account.getEmail());
            statement.setString(6, account.getUsername());
            statement.setString(7, account.getPassword());
            statement.setInt(8, account.getIsAdmin());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertCompanyData(Company company){
        if(connection == null){       
            return;
        }

        String sql = "INSERT INTO Company(name, address, phone, email, code) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setString(3, company.getPhone());
            statement.setString(4, company.getEmail());
            statement.setString(5, company.getCode());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void insertTransactionDetail(TransactionDetail transactionDetail){
        if(connection == null){       
            return;
        }

        String sql = "INSERT INTO Transaction_Detail(orderBuyID, transactionID, orderSellID, amount, price) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, transactionDetail.getOrderBuyID());
            statement.setInt(2, transactionDetail.getTransactionID());
            statement.setInt(3, transactionDetail.getOrderSellID());
            statement.setInt(4, transactionDetail.getAmount());
            statement.setFloat(5, transactionDetail.getPrice());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertTransaction(Transaction transaction){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Transaction(transactionID, createTime) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, transaction.getTransactionID());
            statement.setDate(2, transaction.getCreateTime());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void insertOrdBuy(OrdBuy ordBuy){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Order_Buy(orderBuyID, traderAccountID, stockID, createTime, initAmount, exchangeAmount) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ordBuy.getOrderBuyID());
            statement.setInt(2, ordBuy.getTradeAccountID());
            statement.setInt(3, ordBuy.getStockID());
            statement.setDate(4, ordBuy.getCreateTime());
            statement.setInt(5, ordBuy.getInitAmount());
            statement.setInt(6, ordBuy.getExchangedAmount());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    public void insertOrdSell(OrdSell ordSell){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Order_Sell(orderSellID, traderAccountID, stockID, createTime, initAmount, exchangeAmount) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ordSell.getOrderSellID());
            statement.setInt(2, ordSell.getTradeAccountID());
            statement.setInt(3, ordSell.getStockID());
            statement.setDate(4, ordSell.getCreateTime());
            statement.setInt(5, ordSell.getInitAmount());
            statement.setInt(6, ordSell.getExchangedAmount());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    public void insertTrader(Trader trader){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Trader(traderAccount, balance) VALUES (?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, trader.getTraderAccountID());
            statement.setFloat(2, trader.getBalance());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }


    }

}
