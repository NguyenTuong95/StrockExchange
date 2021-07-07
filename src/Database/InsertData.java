package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import Model.Account;
import Model.Company;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Own;
import Model.Stock;
import Model.Trader;
import Model.Transaction;
import Model.TransactionDetail;
import Util.Utility;

public class InsertData {
    private Utility utility;
    private Connection connection = null;

    private static InsertData instance;

    private InsertData(){
        utility = Utility.getInstance();
        connection = utility.connectDatabase();
    }

    public static InsertData getInstance(){
        if(instance == null){
            synchronized(InsertData.class){
                if(instance == null){
                    instance = new InsertData();
                }
            }
        }

        return instance;
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

        String sql = "INSERT INTO Transaction_Detail(orderBuyID, transactionID, orderSellID, createTime, amount, price) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, transactionDetail.getOrderBuyID());
            statement.setInt(2, transactionDetail.getTransactionID());
            statement.setInt(3, transactionDetail.getOrderSellID());
            statement.setTimestamp(4, transactionDetail.getCreateTime());
            statement.setInt(5, transactionDetail.getAmount());
            statement.setInt(6, transactionDetail.getPrice());

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
            statement.setTimestamp(2, transaction.getCreateTime());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void insertOrdBuy(OrdBuy ordBuy){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Order_Buy(traderAccountID, stockID, amount, price, exchangeAmount) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ordBuy.getTraderAccountID());
            statement.setInt(2, ordBuy.getStockID());
            statement.setInt(3, ordBuy.getAmount());
            statement.setInt(4, ordBuy.getPrice());
            statement.setInt(5, ordBuy.getExchangeAmount());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    public void insertOrdSell(OrdSell ordSell){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Order_Sell(traderAccountID, stockID, amount, price, exchangeAmount) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ordSell.getTraderAccountID());
            statement.setInt(2, ordSell.getStockID());
            statement.setInt(3, ordSell.getAmount());
            statement.setInt(4, ordSell.getPrice());
            statement.setInt(5, ordSell.getExchangeAmount());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    public void insertTrader(Trader trader){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Trader(traderAccountID, balance) VALUES (?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, trader.getTraderAccountID());
            statement.setFloat(2, trader.getBalance());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }



    public void insertStock(Stock stock){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Stock(companyID, code, lastPrice, minPrice, maxPrice) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, stock.getCompanyID());
            statement.setString(2, stock.getCode());
            statement.setInt(3, stock.getLastPrice());
            statement.setInt(4, stock.getMinPrice());
            statement.setInt(5, stock.getMaxPrice());
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void insertOwn(Own own){
        if(connection == null){       
            return;
        }
        String sql = "INSERT INTO Own(traderAccountID, stockID, amount) VALUES (?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, own.getTraderAccountID());
            statement.setInt(2, own.getStockID());
            statement.setInt(3, own.getAmount());
            
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
