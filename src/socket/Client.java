package socket;

/**
 *
 * @author ManhHung
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

import Model.OrdBuy;
import Model.OrdSell;

public class Client{
    static final int MODE_DEFAULT = 0;
    static final int MODE_BUY = 1;
    static final int MODE_SELL = 2;
    private static int mode = 1;
    private static int stockID;
    private static int traderAccountID;
    private static OrdBuy ordBuy;
    private static OrdSell ordSell;


    public static void main(String[] args) throws Exception {
      // try{
      //   //  Tạo ra một socket để kết nối với socket server
      // Socket connectToServer = new Socket("localhost", 8888);
      //   //  Tạo ra một luồng vào để nhận dữ liệu từ server
      // DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
      //   //  Tạo ra một luồng ra để gửi dữ liệu đến server
      // DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
      //   //  Tạo ra một luồng vào để nhập dữ liệu từ bàn phím
      //  DataInputStream str = new DataInputStream(System.in);
      //  //  Liên tục gửi dữ liệu nhập vào từ bàn phím cho server và nhận lại diện tích
      //   while(true){
      //        // Nhập bán kính từ bàn phím
      //     System.out.print("Hay cho biet ban kinh: ");
      //     double radius = Double.parseDouble(str.readLine());
      //        // Gửi dữ liệu cho server
      //     osToServer.writeDouble(radius);
      //     osToServer.flush();
      //        // Nhận lại kết quả từ server
      //     double area = isFromServer.readDouble();
      //     System.out.println("Dien tich hinh tron nhan duoc tu Server: " +	area);
      //           }
      //   }catch(IOException ex){
      //           System.err.println(ex);
      //   }
      /*
      Scanner sc = new Scanner(System.in); 
      while(true){
        System.out.print("stockID: ");
        stockID = sc.nextInt();
        System.out.print("traderAccountID: ");
        traderAccountID = sc.nextInt();
        start();

      } 
      */      

  }


    public static void prepareData() {
      Scanner sc = new Scanner(System.in); 
      do{
          System.out.println("0. thoat He thong");
          System.out.println("1. Dat Lenh mua");
          System.out.println("2. Dat lenh ban");   
          System.out.println("Nhap Mode: ");
          mode = sc.nextInt();
          processMode(mode);

      }while(mode != 0);

    }

    public static void processMode(int mode) {
      int price;
      int amount;
      Scanner sc = new Scanner(System.in); 

      switch(mode){
        case MODE_BUY:
          System.out.print("Nhap gia mua: ");
          price =  sc.nextInt();
          System.out.print("Nhap so luong mua: ");
          amount =  sc.nextInt();
          ordBuy = new OrdBuy(stockID, traderAccountID, amount, price);
          try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/OrdBuy.dat")));
            oos.writeObject(ordBuy);
            oos.close();
          } catch (FileNotFoundException e) {
     
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        break;
        case MODE_SELL:
          System.out.print("Nhap gia ban: ");
          price =  sc.nextInt();
          System.out.print("Nhap so luong ban: ");
          amount =  sc.nextInt();
          ordSell = new OrdSell(stockID, traderAccountID, amount, price);
          try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/OrdSell.dat")));
            oos.writeObject(ordSell);
            oos.close();
          } catch (FileNotFoundException e) {
     
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        break;

      }

    }

    public static void start(){

      try{
        //  Tạo ra một socket để kết nối với socket server
      Socket connectToServer = new Socket("localhost", 8888);
        //  Tạo ra một luồng vào để nhận dữ liệu từ server
      DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
        //  Tạo ra một luồng ra để gửi dữ liệu đến server
      DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
        //  Tạo ra một luồng vào để nhập dữ liệu từ bàn phím
       //DataInputStream str = new DataInputStream(System.in);

       //  Liên tục gửi dữ liệu nhập vào từ bàn phím cho server và nhận lại diện tích
     
          while(mode != MODE_DEFAULT){
            prepareData();
              // Gửi dữ liệu cho server
            osToServer.writeInt(mode);
            osToServer.flush();
              // Nhận lại kết quả từ server
            String revStr = isFromServer.readUTF();
            System.out.println(revStr);

          }
        }catch(IOException ex){
                System.err.println(ex);
        }
    }
}
