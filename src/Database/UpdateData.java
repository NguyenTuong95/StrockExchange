package Database;

import Util.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.OrdSell;

public class UpdateData {
    
    private Utility utility;
    private Connection connection = null;
    public UpdateData(){
        utility = Utility.getInstance();
        connection = utility.connectDatabase();
    }

  

}
