package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Database.InsertData;
import Model.Account;
import Model.Company;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Own;
import Model.Stock;
import Model.Trader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utility {

    private static Utility instance = null;
    static final int NUMBER = 333;

    private Utility(){}

    public static Utility getInstance(){
        if(instance == null){
            synchronized(Utility.class){
                if(instance == null){
                    instance = new Utility();
                }
            }
        }
        return instance;
    }

    public static final int MAX_NUMBER = 100001;
    public static final String[] listLName = {"Tuong","Manh","Hung","Huy","Thanh","Linh","Quan","Quynh","Hue","Ha",
                                                "Kien", "Thanh", "Thu", "Phuong", "Phan", "Manh", "Quynh", "Dam",
                                                 "Vinh", "Hung", "Chi", "Minh", "Hung", "Hanh", "Thao", "Ha", "Xuan"};

    public static final String[] listFName = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ",
                                             "Đặng", "Bùi", "Đỗ", "Hồ", "Ngô", "Dương", "Lý"};


    public static final String[] listAddress = {"An Giang","Bà Rịa – Vũng Tàu","Bạc Liêu","Bắc Giang","Bắc Kạn",
                                                "Bắc Ninh","Bến Tre","Bình Dương","Bình Định","Bình Phước","Bình Thuận",
                                                "Cà Mau","Cao Bằng","Cần Thơ","Đà Nẵng","Đắk Lắk","Đắk Nông","Điện Biên",
                                                "Đồng Nai","Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Nội",                                                    	
                                                "Hà Tĩnh","Hải Dương","Hải Phòng","Hậu Giang","Hòa Bình",
                                                "Thành phố Hồ Chí Minh","Hưng Yên","Khánh Hòa","Kiên Giang",
                                                "Kon Tum","Lai Châu","Lạng Sơn","Lào Cai","Lâm Đồng",
                                                "Long An","Nam Định","Nghệ An","Ninh Bình","Ninh Thuận",
                                                "Phú Thọ","Phú Yên","Quảng Bình","Quảng Nam","Quảng Ngãi",
                                                "Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh",
                                                "Thái Bình","Thái Nguyên","Thanh Hóa","Thừa Thiên Huế",
                                                "Tiền Giang","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái",
    };
    
    public static String[] listCompany = {"CÔNG TY TNHH SAMSUNG ELECTRONICS VIỆT NAM THÁI NGUYÊN","TẬP ĐOÀN ĐIỆN LỰC VIỆT NAM",
                                        "TẬP ĐOÀN DẦU KHÍ VIỆT NAM","TẬP ĐOÀN CÔNG NGHIỆP - VIỄN THÔNG QUÂN ĐỘI","TẬP ĐOÀN XĂNG DẦU VIỆT NAM",
                                        "TẬP ĐOÀN VINGROUP - CTCP","TẬP ĐOÀN CÔNG NGHIỆP THAN - KHOÁNG SẢN VIỆT NAM","NGÂN HÀNG NÔNG NGHIỆP VÀ PHÁT TRIỂN NÔNG THÔN VIỆT NAM",
                                        "NGÂN HÀNG TMCP ĐẦU TƯ VÀ PHÁT TRIỂN VIỆT NAM","CÔNG TY CP ĐẦU TƯ THẾ GIỚI DI ĐỘNG","CÔNG TY CP LỌC HÓA DẦU BÌNH SƠN",
                                        "TỔNG CÔNG TY HÀNG KHÔNG VIỆT NAM - CTCP","NGÂN HÀNG TMCP CÔNG THƯƠNG VIỆT NAM","CÔNG TY CP TẬP ĐOÀN VÀNG BẠC ĐÁ QUÝ DOJI",
                                        "NGÂN HÀNG TMCP NGOẠI THƯƠNG VIỆT NAM","TỔNG CÔNG TY DẦU VIỆT NAM - PVOIL","TỔNG CÔNG TY KHÍ VIỆT NAM - CTCP",
                                        "CÔNG TY CP CHĂN NUÔI C.P. VIỆT NAM","CÔNG TY CP TẬP ĐOÀN HÒA PHÁT","CÔNG TY CP ÔTÔ TRƯỜNG HẢI",
                                        "NGÂN HÀNG TMCP VIỆT NAM THỊNH VƯỢNG","CÔNG TY CP SỮA VIỆT NAM","TẬP ĐOÀN BƯU CHÍNH VIỄN THÔNG VIỆT NAM",
                                        "CÔNG TY CP HÀNG KHÔNG VIETJET","TỔNG CÔNG TY PHÁT ĐIỆN 3 - CTCP","NGÂN HÀNG TMCP SÀI GÒN",
                                        "TỔNG CÔNG TY DỊCH VỤ VIỄN THÔNG","LIÊN DOANH VIỆT - NGA VIETSOVPETRO","TẬP ĐOÀN BẢO VIỆT","NGÂN HÀNG TMCP QUÂN ĐỘI",
                                        "CÔNG TY CP TẬP ĐOÀN MASAN","CÔNG TY CP TẬP ĐOÀN THÀNH CÔNG","CÔNG TY CP HYUNDAI THÀNH CÔNG VIỆT NAM",
                                        "TỔNG CÔNG TY ĐIỆN LỰC TP. HÀ NỘI","TỔNG CÔNG TY PHÁT ĐIỆN 1","TỔNG CÔNG TY CP BIA RƯỢU NƯỚC GIẢI KHÁT SÀI GÒN",
                                        "NGÂN HÀNG TMCP SÀI GÒN THƯƠNG TÍN","NGÂN HÀNG TMCP KỸ THƯƠNG VIỆT NAM","TỔNG CÔNG TY THĂM DÒ KHAI THÁC DẦU KHÍ - CÔNG TY TNHH NHÀ NƯỚC MTV",
                                        "TỔNG CÔNG TY ĐIỆN LỰC DẦU KHÍ VIỆT NAM - CTCP","TỔNG CÔNG TY VIỄN THÔNG MOBIFONE","LIÊN HIỆP HỢP TÁC XÃ THƯƠNG MẠI TP. HỒ CHÍ MINH (CO.OP MART)",
                                        "TỔNG CÔNG TY THÉP VIỆT NAM - CTCP","NGÂN HÀNG TMCP Á CHÂU","TỔNG CÔNG TY BẢO VIỆT NHÂN THỌ","CÔNG TY TNHH QUỐC TẾ UNILEVER VIỆT NAM",
                                        "NGÂN HÀNG TMCP SÀI GÒN - HÀ NỘI","CÔNG TY TNHH MTV NHIÊN LIỆU HÀNG KHÔNG VIỆT NAM (SKYPEC)","CÔNG TY CP FPT","CÔNG TY CP TẬP ĐOÀN HOA SEN"

    };

    public static final String[] listPhone = {"0564356.442","0564575.940","0564838.173","0564991.748","0565773.647","0565809.408",
                                                "0564827.039","0565803.478","0565818.377","0823114.361","0823583.480","0847812.702",
                                                "084803.09.41","0793071.052","0793073.121","0793073.357","0793073.659","0375851.480",
                                                "0376295.782","0865420.875","0865509.764","081.9933.394","0936334.100","0916576.314",
                                                "0936334.328","0936334.861","0936335.425","0936336.317","0936336.744","0936336.844",
                                                "0936436.263","0945.326.784","0949.411.794","0949.446.310","0949.473.093","0949.644.830",
                                                "0949.733.085","0949.733.492","0961176.714","0961984.507","0967712.744","0974196.530",
                                                "0976195.674","0976561.013","09.6171.4144","0961759.475","0963764.069","096843.0803",
                                                "0976972.759","0964081.738"};

    

    public Connection connectDatabase(){
        String urlMySQL = "jdbc:mysql://localhost/stockexchange";
        Connection con =  null;
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");// nap driver
        } 
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }      
         
         try {
            con = DriverManager.getConnection(urlMySQL,"root","12345678");//ket noi
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return con;
    }
                                                
    public Account generateAccount(){        
        int index = new Random().nextInt(100001);
        String lName = listLName[new Random().nextInt(listLName.length)];
        String fName = listFName[new Random().nextInt(listFName.length)];
        String phone = listPhone[new Random().nextInt(listPhone.length)];
        String address = listAddress[new Random().nextInt(listAddress.length)];
        String username = lName + index;
        String email = username + "@gmail.com";
        String password = username;
        int isAdmin = (index % 31 == 0) ? 1 : 0;
        Account account = new Account(fName, lName, address, phone, email, username, password, isAdmin);
        return account;
    }

    public void generateData(){

        InsertData insertData = InsertData.getInstance();

        for(int i = 0; i < NUMBER; i++){
            Account account = new Account();
            account = generateAccount();
            insertData.insertAccountData(account);
        }

       
        for(int i = 0; i < listCompany.length; i++){
            Company company = new Company();
            company.setName(listCompany[i]);
            company.setAddress("VietNam");
            company.setPhone(listPhone[i%(listPhone.length)]);
            company.setEmail("contact" + new Random().nextInt(10001) + "@gmail.com");
            company.setCode("" + new Random().nextInt(10001));
            insertData.insertCompanyData(company);
        } 

        
        for(int i = 0; i < NUMBER; i++){
            Stock stock = new Stock();
            stock.setCompanyID(3 + new Random().nextInt(49));
            stock.setCode("" + new Random().nextInt(10001));
            stock.setLastPrice(150+ new Random().nextInt(150));
            stock.setMinPrice(100 + new Random().nextInt(100));
            stock.setMaxPrice(300 + new Random().nextInt(300));
            insertData.insertStock(stock);
        }


        for(int i = 0; i < NUMBER; i++){
            Trader trader = new Trader();
            trader.setTraderAccountID(6 + new Random().nextInt(NUMBER-3));
            trader.setBalance(150 + new Random().nextInt(300));
            insertData.insertTrader(trader);
        }


        for(int i = 0; i < NUMBER; i++){
            Own own = new Own();
            own.setTraderAccountID(6 + new Random().nextInt(NUMBER-3));
            own.setStockID(3 + new Random().nextInt(NUMBER-5));
            own.setAmount(1000 + new Random().nextInt(3000));
            insertData.insertOwn(own);
            
        }

        for(int cnt = 0 ; cnt < 1; cnt++){
            int stockID = new Random().nextInt(33);
            for(int i = 0; i < 50; i++){
                OrdBuy ordBuy = genDataOrdBuy(stockID);
                OrdSell ordSell = genDataOrdSell(stockID);
                insertData.insertOrdBuy(ordBuy);
                insertData.insertOrdSell(ordSell);
            }

        }

    }

    private OrdBuy genDataOrdBuy(int stockID){
        int traderAccountID = 6 + new Random().nextInt(100);
        int amount = 100 + new Random().nextInt(100);
        int price = 200 + new Random().nextInt(100); 
        OrdBuy ordBuy = new OrdBuy(stockID, traderAccountID, amount, price, 0);
        try{
            Thread.sleep(30);
        }catch(InterruptedException ie){
            ie.printStackTrace();
    }

        return ordBuy;
    }

    private OrdSell genDataOrdSell(int stockID){
        int traderAccountID = 6 + new Random().nextInt(100);
        int amount = 100 + new Random().nextInt(100);
        int price = 200 + new Random().nextInt(100); 
        OrdSell ordSell = new OrdSell(stockID, traderAccountID, amount, price, 0);
        try{
            Thread.sleep(120);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        return ordSell;
    }

}
