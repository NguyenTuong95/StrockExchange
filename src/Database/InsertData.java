package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import Model.Account;
import Model.Company;
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


}
