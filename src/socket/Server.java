
package socket;

import java.io.*;
import java.net.*;

import Database.InsertData;
import Model.OrdBuy;
import Model.OrdSell;
import Util.PublicData;

public class Server {
    private static PublicData publicData;
    
    public static void main(String arg[]) throws IOException {
        publicData = PublicData.getInstance();
        publicData.setStockID(10);
          multiClient();
    }
    
    public static void multiClient() {
        try {
            // Tạo ra socket server
            ServerSocket serverSocket = new ServerSocket(8890);
            //while (true) {
                //Socket connectToClient = serverSocket.accept();
                ThreadServer thread1 = new ThreadServer(serverSocket.accept());
                Thread thread = new Thread (thread1);
                thread.start();
            //}
        }
        catch (IOException e){
            System.out.println(e.getMessage());

        }
    }

}

class ThreadServer implements Runnable {
    static final int MODE_DEFAULT = 10;
    static final int MODE_EXIT = 0;
    static final int MODE_BUY = 1;
    static final int MODE_SELL = 2;
    static final int MODE_INFOR = 3;

    InsertData insertData;
    Socket sk;
 public  ThreadServer(Socket sk) throws IOException
 {
      this.sk= sk;
      insertData = InsertData.getInstance();
 }
    @Override
    public void run()
    {
        try {
         DataInputStream isFromClient = new DataInputStream(sk.getInputStream());
            // Tạo ra một luồng ra để gửi dữ liệu cho Client
         DataOutputStream osToClient = new DataOutputStream(
             sk.getOutputStream());
            // Liên tục nhận, xử lý và gửi kết quả lại cho Client
            int  mode = MODE_DEFAULT;
            while (mode != MODE_EXIT) {
                // Đọc một số double từ Client
                mode = isFromClient.readInt();
                System.out.println("mode: " + mode);
                processMode(mode);

               
                //double area = radius * radius * Math.PI;
                // Gửi kết quả: diện tích kiểu double cho Client
                //osToClient.writeDouble(area);
                //osToClient.flush();
                //System.out.//
              //println("Hinh tron co ban kinh: " + radius + " co dien tich: " + area);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    void processMode(int mode){
        switch(mode){
            case MODE_BUY:
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/OrdBuy.dat")));
                    OrdBuy ordBuy = (OrdBuy) ois.readObject();
                    System.out.println(ordBuy);
                    insertData.insertOrdBuy(ordBuy);
                    ois.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
                    mode = MODE_DEFAULT;
                  }
            break;
            case MODE_SELL:
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/OrdSell.dat")));
                    OrdSell ordSell = (OrdSell) ois.readObject();
                    System.out.println(ordSell);
                    insertData.insertOrdSell(ordSell);
                    ois.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
                    mode = MODE_DEFAULT;
                  }

            break;
        }
    }

}
