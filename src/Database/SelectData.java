package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Account;
import Model.Company;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Own;
import Model.Stock;
import Util.Utility;
import java.sql.ResultSet;

public class SelectData {
    private static SelectData instance;
    private Utility utility;
    private Connection connection = null;
    
    private SelectData(){
        utility = Utility.getInstance();
        connection = utility.connectDatabase();
    }

    public static SelectData getInstance(){
        if(instance == null){
            synchronized(SelectData.class){
                if(instance == null){
                    instance = new SelectData();
                }
            }
        }
        return instance;
    }

    public List<OrdBuy> selectDataOrdBuy(String sql){
        List<OrdBuy> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }

      try{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            int traderAccountID = result.getInt(2);
            int stockID = result.getInt(3);
            int amount = result.getInt(4);
            int price = result.getInt(5);
            int exchangeAmount = result.getInt(6);
            amount -= exchangeAmount;
            OrdBuy ordBuy = new OrdBuy(stockID, traderAccountID, amount, price);
            ordBuy.setExchangeAmount(exchangeAmount);
            lstData.add(ordBuy);   
        }

      }catch(SQLException ex){
          ex.printStackTrace();
      }   
        return lstData;
    }

    public List<OrdSell> selectDataOrdSell(String sql){
        List<OrdSell> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }
      try{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            int traderAccountID = result.getInt(2);
            int stockID = result.getInt(3);
            int amount = result.getInt(4);
            int price = result.getInt(5);
            int exchangeAmount = result.getInt(6);
            amount = amount - exchangeAmount;
            OrdSell ordSell = new OrdSell(stockID, traderAccountID, amount, price);
            ordSell.setExchangeAmount(exchangeAmount);
            lstData.add(ordSell);       
        }

      }catch(SQLException ex){
          ex.printStackTrace();
      }   
        return lstData;
    }

    public List<Stock> selectDataStocks(String sql){
        List<Stock> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
    
            while(result.next()){
                int stockID = result.getInt(1);
                int companyID = result.getInt(2);
                String code = result.getString(3);
                int lastPrice = result.getInt(4);
                int minPrice = result.getInt(5);
                int maxPrice = result.getInt(6);   
                Stock stock = new Stock(companyID, code, lastPrice, minPrice, maxPrice);
                stock.setStockID(stockID);
                lstData.add(stock);       
            }
    
          }catch(SQLException ex){
              ex.printStackTrace();
          }   

        return lstData;
    }

    public List<Company> selectDataCompany(String sql){
        List<Company> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
    
            while(result.next()){
                int companyID = result.getInt(1);
                String name = result.getString(2);
                String address = result.getString(3);
                String phone = result.getString(4);
                String email = result.getString(5);
                String code = result.getString(6);   
                Company company = new Company(companyID, name, email, phone, address, code);
                lstData.add(company);       
            }
    
          }catch(SQLException ex){
              ex.printStackTrace();
          }   

        return lstData;
    }

    public List<Own> selectDataOwn(String sql){
        List<Own> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
    
            while(result.next()){
                int traderAccountID = result.getInt(1);
                int stockID = result.getInt(2);
                int amount = result.getInt(3);
                Own own = new Own(traderAccountID, stockID, amount);
                lstData.add(own);       
            }
    
          }catch(SQLException ex){
              ex.printStackTrace();
          }   

        return lstData;
    }

    public List<Account> selectDataAccount(String sql){
        List<Account> lstData = new ArrayList<>();

        if(connection == null){       
            return null;
        }

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
    
            while(result.next()){
                int accountID = result.getInt(1);
                String fName = result.getString(2);
                String lName = result.getString(3);
                String address = result.getString(4);
                String phone = result.getString(5);
                String email = result.getString(6);
                String username = result.getString(7);
                //String password = result.getString(8);
                int isAdmin = result.getInt(9);
                Account account = new Account();
                account.setFName(fName);
                account.setLName(lName);
                account.setAccountID(accountID);
                account.setAddress(address);
                account.setEmail(email);
                account.setPhone(phone);
                account.setUsername(username);
                account.setIsAdmin(isAdmin);
                lstData.add(account);       
            }
    
          }catch(SQLException ex){
              ex.printStackTrace();
          }   

        return lstData;
    }


}
