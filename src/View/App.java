package View;
import Controller.StockController;
import Util.Utility;

public class App {
    public static void main(String[] args) throws Exception {
        Utility utility;
        utility = Utility.getInstance();

        //utility.generateData();


        Thread t = new Thread(new StockController(10));
        t.start();
            

    }



}
