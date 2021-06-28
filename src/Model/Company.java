package Model;

public class Company {
    private int companyID;
    private String companyName;
    private String companyEmail;
    private String companyAddress;
    private String companyPhone;
    private static int cnt = 0;
    public Company(){
        companyID = cnt++;
    }

    public Company(String name, String email, String phone, String address){
        this.companyName = name;
        this.companyAddress = address;
        this.companyEmail = email;
        this.companyPhone = phone;
    }

    public int getCompanyID() {
        return this.companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return this.companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return this.companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    
}
