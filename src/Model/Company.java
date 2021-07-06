package Model;

public class Company {
    private int companyID;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String code;

    

    private static int cnt = 0;
    public Company(){
        companyID = cnt++;
    }

    public Company(int id, String name, String email, String phone, String address, String code){
        this.companyID = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.code = code;
    }

    public int getCompanyID() {
        return this.companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
