package Model;

public class User {
    int userID;
    String userName;
    String userEmail;
    String userAddress;
    String userPassword;
    String userPhone;

    private static int cnt = 1;
    public User(){
        this.userID = cnt++;
    }
    public User(String name, String email, String phone,  String address, String password){ 
        this.userID = cnt++;     
        this.userName = name;
        this.userEmail = email;
        this.userAddress = address;
        this.userPassword =  "" + password.hashCode();
        this.userPhone = phone;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {

        return "[User ID: " + userID +" Name: " + userName + 
        " email: "+ userEmail +  " phone: " + userPhone + " address: " + userAddress +"]";
    }



}
