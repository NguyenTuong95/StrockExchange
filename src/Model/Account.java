package Model;

import Security.EncryptString;

public class Account implements Comparable<Account>{
    private int accountID;
    private String fName;
    private String lName;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private int isAdmin;
    private static int cnt = 0;


    public Account(){
        this.accountID = cnt++;
    }

    public Account(String fName, String lName, String address, 
    String phone, String email, String username, String password, int isAdmin){
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.isAdmin = isAdmin;  
        this.password = EncryptString.encrypt(password);    
       
    }

    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        
        return " --[ACCOUNT] ID: " + this.accountID + " fName: " + this.fName + " lName: " + this.lName
        + " Address: " + this.address + " phone: " + this.phone + " email: " + this.email + " --";
    }

    @Override
    public int compareTo(Account o) {
        // TODO Auto-generated method stub
        return this.username.compareTo(o.getUsername());
    }

    

}
