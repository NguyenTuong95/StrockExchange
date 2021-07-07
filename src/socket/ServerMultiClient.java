package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMultiClient {
  
    public static void main(String[] args) throws IOException {
        ServerSocket server;
        Socket socket;
        server = new ServerSocket(8000);
        // phuc vu nhieu client
        while (true) {
            socket = server.accept();
            ForMultiClient forClient = new ForMultiClient(socket);
            forClient.start();
        }

    }
}
class ForMultiClient extends Thread {
    Socket socket;
    public ForMultiClient (Socket socket){
         this.socket = socket;
    }
    public void run() {
        DataInputStream in;
        DataOutputStream out;
        try {
            // Tao ra cac luong dua tren socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            double a = in.readDouble();
            System.out.println(a);
            out.writeDouble(a*a*Math.PI);

        } catch (IOException ex) {
            Logger.getLogger(ForMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

