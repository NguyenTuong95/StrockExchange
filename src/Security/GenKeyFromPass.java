package Security;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
//import static security.desKeyGenFromPass.EncryptFile.secretkey;

/**
 *
 * @author Hung
 */
public class GenKeyFromPass { 

    private static GenKeyFromPass instance = null;

    private GenKeyFromPass(){}

    public static GenKeyFromPass getInstance(){
        if(instance == null){
            synchronized(GenKeyFromPass.class){
                if(instance == null){
                    instance = new GenKeyFromPass();
                }
            }
        }
        return instance;
    }


    public  SecretKey secretkey(String pass) throws Exception {
        byte[] passbyte = pass.getBytes();
        DESKeySpec keyspec = new DESKeySpec(passbyte);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey Deskey = keyFactory.generateSecret(keyspec);
        return Deskey;
    }

    public  String generatePass(String pass) throws Exception{
        SecretKey DesKey = secretkey(pass);
       // System.out.println("key gen= " + Base64.getEncoder().encodeToString(
           //     DesKey.getEncoded()));

        return Base64.getEncoder().encodeToString(
            DesKey.getEncoded());

    }

}