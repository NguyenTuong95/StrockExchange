package socket;

/**
 *
 * @author ManhHung
 */
import java.io.*;
import java.net.*;
import java.util.Queue;
import java.util.Scanner;

import Database.SelectData;
import Model.Account;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Stock;
import Security.EncryptString;


public class Client{
    static final int MODE_DEFAULT = 10;
    static final int MODE_EXIT = 0;
    static final int MODE_BUY = 1;
    static final int MODE_SELL = 2;
    static final int MODE_INFOR = 3;
    private static int mode = MODE_DEFAULT;

    private static OrdBuy ordBuy;
    private static OrdSell ordSell;
  
    private static SelectData selectData;

    public static void main(String[] args) throws Exception {
      File file = new File("data.txt");
      selectData = SelectData.getInstance();
      Scanner sc = new Scanner(System.in);
     
      login();

      while(mode != MODE_EXIT){
   
        start();

      } 
       
  }

  static void login(){
    Scanner sc = new Scanner(System.in);
    BufferedOutputStream bufferOut = null;

    while(true){
      System.out.print("Account: ");
      String account = sc.nextLine();
      System.out.print("Password: ");
      String pass = sc.nextLine();
      pass = EncryptString.encrypt(pass);

      String sql = "SELECT * FROM infor_account WHERE username = '" + account + "' AND password = " + "'" + pass +"'" + ";";

      Queue<Account> lstData = selectData.selectDataAccount(sql);
      if(lstData.size() != 0){
        System.out.println("Login Success! ");
        int traderAccountID = lstData.peek().getAccountID();
       
       
        int stockID = 10;
        //System.out.println("traderAccountID: " + traderAccountID);
        //System.out.println("stockID: " + stockID);
      
        try {
          FileWriter fw = new FileWriter("data.txt");
          fw.write(stockID);
          fw.write(traderAccountID);
          fw.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        break;
      }else{
        System.out.println("Wrong account or password!");
        System.out.println("Enter Again!");
      }

    }

  }

    public static void processMode(int mode) {
      int price;
      int amount;
      int stockID = 0;
      int traderAccountID = 0;
    
      FileReader fr;
      try {
        fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);
        stockID = br.read();
        traderAccountID = br.read();
        System.out.println("traderAccountID: " + traderAccountID);
        System.out.println("stockID: " + stockID);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
        

      Scanner sc = new Scanner(System.in);  
      switch(mode){
        
        case MODE_BUY:
          System.out.print("Nhap gia mua: ");
          price =  sc.nextInt();
          System.out.print("Nhap so luong mua: ");
          amount =  sc.nextInt();

          ordBuy = new OrdBuy(stockID, traderAccountID, amount, price);
          try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("OrdBuy.dat")));
            oos.writeObject(ordBuy);
            oos.close();
          } catch (FileNotFoundException e) {
     
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }finally{
            mode = MODE_DEFAULT;
          }
        break;
        case MODE_SELL:
          System.out.print("Nhap gia ban: ");
          price =  sc.nextInt();
          System.out.print("Nhap so luong ban: ");
          amount =  sc.nextInt();
          ordSell = new OrdSell(stockID, traderAccountID, amount, price);
          try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("OrdSell.dat")));
            oos.writeObject(ordSell);
            oos.close();
          } catch (FileNotFoundException e) {
     
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }finally{
            mode = MODE_DEFAULT;
          }
        break;

        case MODE_INFOR:
          String sql = "SELECT * FROM stock WHERE stockID = '" + stockID  + "'" + ";";
          Queue<Stock> lstData = selectData.selectDataStocks(sql);
          if(lstData.size() > 0){
            System.out.println(lstData.peek());
            
          }
        break;

      }

    }

    public static void start(){

      try{
        //  Tạo ra một socket để kết nối với socket server
      Socket connectToServer = new Socket("localhost", 8890);
        //  Tạo ra một luồng vào để nhận dữ liệu từ server
      DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
        //  Tạo ra một luồng ra để gửi dữ liệu đến server
      DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
        //  Tạo ra một luồng vào để nhập dữ liệu từ bàn phím
       DataInputStream str = new DataInputStream(System.in);

       //  Liên tục gửi dữ liệu nhập vào từ bàn phím cho server và nhận lại diện tích
     
          while(mode != MODE_EXIT){
          
            System.out.println("0. Thoat He thong");
            System.out.println("1. Dat Lenh mua");
            System.out.println("2. Dat lenh ban");
            System.out.println("3. Thong tin co phieu");   
            System.out.print("Nhap lenh: ");
       
            mode = Integer.parseInt(str.readLine());
            
            processMode(mode);
            //mode = Integer.parseInt(str.readLine());
              // Gửi dữ liệu cho server
            osToServer.writeInt(mode);
            osToServer.flush();
              // Nhận lại kết quả từ server
            //String revStr = isFromServer.readUTF();
            //System.out.println(revStr);

          }
        }catch(IOException ex){
                System.err.println(ex);
        }
    }
}
