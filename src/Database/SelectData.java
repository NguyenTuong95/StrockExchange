package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.OrdBuy;
import Model.OrdSell;
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

    public Queue<OrdBuy> selectDataOrdBuy(){
        Queue<OrdBuy> lstData = new PriorityQueue<>();

        if(connection == null){       
            return null;
        }
        
        String sql = "SELECT * FROM Order_Buy WHERE exchangeAmount < amount";

      try{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            int traderAccountID = result.getInt(2);
            int stockID = result.getInt(3);
            int amount = result.getInt(4);
            int price = result.getInt(5);
            int exchangeAmount = result.getInt(6);
            //if(amount > exchangeAmount){
            amount -= exchangeAmount;
            OrdBuy ordBuy = new OrdBuy(stockID, traderAccountID, amount, price, exchangeAmount);
            lstData.add(ordBuy);
           //}     
        }

      }catch(SQLException ex){
          ex.printStackTrace();
      }   
        return lstData;
    }

    public Queue<OrdSell> selectDataOrdSell(){
        Queue<OrdSell> lstData = new PriorityQueue<>();

        if(connection == null){       
            return null;
        }
        
        String sql = "SELECT * FROM Order_Sell WHERE exchangeAmount < amount";

      try{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            int traderAccountID = result.getInt(2);
            int stockID = result.getInt(3);
            int amount = result.getInt(4);
            int price = result.getInt(5);
            int exchangeAmount = result.getInt(6);
            //if(amount > exchangeAmount){
            amount = amount - exchangeAmount;
            OrdSell ordSell = new OrdSell(stockID, traderAccountID, amount, price, exchangeAmount);
            lstData.add(ordSell);
            //}          
        }

      }catch(SQLException ex){
          ex.printStackTrace();
      }   
        return lstData;
    }



}
