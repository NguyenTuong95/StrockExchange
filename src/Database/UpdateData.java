package Database;

import Util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Own;
import Model.Trader;

public class UpdateData {
    
    private static UpdateData instance;

    private Utility utility;
    private Connection connection = null;
 

    private UpdateData(){
        utility = Utility.getInstance();
        connection = utility.connectDatabase();
    }

    public static UpdateData getInstance(){
        if(instance == null){
            synchronized(UpdateData.class){
                if(instance == null){
                    instance = new UpdateData();
                }
            }
        }
        return instance;
    }

    public void updateTrader(Trader trader){
        if(connection == null){       
            return;
        }

        try {
            String sql = "UPDATE Trader SET balance=? WHERE traderAccountID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, trader.getBalance());
            statement.setInt(2, trader.getTraderAccountID());
            statement.executeUpdate();

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

    }
    
    public void updateOwn(Own own){
        if(connection == null){       
            return;
        }
        try {
            String sql = "UPDATE Trader SET amount=? WHERE traderAccountID=? AND stockID=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, own.getAmount());
            statement.setInt(2, own.getTraderAccountID());
            statement.setInt(3, own.getStockID());
            statement.executeUpdate();

        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }


}
