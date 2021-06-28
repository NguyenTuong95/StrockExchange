package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Model.User;


public class Utility {
    public static final int MAX_NUMBER = 1001;
    public static final String[] listName = {"Tuong","Manh","Hung","Huy","Thanh","Linh","Quan","Quynh","Hue","Ha",
    "Kien", "Thanh", "Thu", "Phuong", "Phan", "Manh", "Quynh", "Dam", "Vinh", "Hung", "Chi"};
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

    public static final String[] listPhone = {"0564356.442","0564575.940","0564838.173","0564991.748","0565773.647","0565809.408",
                                                "0564827.039","0565803.478","0565818.377","0823114.361","0823583.480","0847812.702",
                                                "084803.09.41","0793071.052","0793073.121","0793073.357","0793073.659","0375851.480",
                                                "0376295.782","0865420.875","0865509.764","081.9933.394","0936334.100","0916576.314",
                                                "0936334.328","0936334.861","0936335.425","0936336.317","0936336.744","0936336.844",
                                                "0936436.263","0945.326.784","0949.411.794","0949.446.310","0949.473.093","0949.644.830",
                                                "0949.733.085","0949.733.492","0961176.714","0961984.507","0967712.744","0974196.530",
                                                "0976195.674","0976561.013","09.6171.4144","0961759.475","0963764.069","096843.0803",
                                                "0976972.759","0964081.738"};

    public static User generateUser(){
        String name  = listName[new Random().nextInt(listName.length)];
        User user = new User(name, name + "@gmail.com", 
        listPhone[new Random().nextInt(listPhone.length)], 
        listAddress[new Random().nextInt(listAddress.length)], name);
        return user;
    }


                                                

}
