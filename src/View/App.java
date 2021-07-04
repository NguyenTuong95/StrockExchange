package View;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import Database.InsertData;
import Model.Account;
import Model.Company;
import Util.Utility;

public class App {
    static final int NUMBER = 1001;
   
    public static void main(String[] args) throws Exception {
        Utility utility;
        //List <OrdSell> sellQueue = new ArrayList<>();

        //List <OrdBuy> buyQueue = new ArrayList<>();

        //Queue<OrdSell> pSells = new PriorityQueue<>();
        //Queue<OrdBuy> pBuys = new PriorityQueue<>();

        // System.out.println("Xác định và in ra giao dịch có số lượng nhỏ nhất");
        // System.out.println(sellQueue.stream().sorted((t1,t2)->t1.getAmount() - (t2.getAmount())).findFirst().toString());
        InsertData insertData = new InsertData();

        utility = Utility.getInstance();
        // for(int i = 0; i < NUMBER; i++){
        //     Account account = new Account();
        //     account = utility.generateAccount();
        //     insertData.insertAccountData(account);
        // }

        // String[] listCompany = utility.listCompany;
        // for(int i = 0; i < listCompany.length; i++){
        //     Company company = new Company();
        //     company.setName(listCompany[i]);
        //     company.setAddress("VietNam");
        //     company.setPhone(utility.listPhone[i%(utility.listPhone.length)]);
        //     company.setEmail("contact" + new Random().nextInt(10001) + "@gmail.com");
        //     company.setCode("" + new Random().nextInt(10001));
        //     insertData.insertCompanyData(company);
        // } 

    }



}
