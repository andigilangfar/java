import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.*;
import java.util.*;
import java.net.*;
public class Client{
    public static void main(String[]args){
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        InputStream in = null;
        //CONFIG PROPERTIES
        Properties prop = new Properties();
        //CONFIG SOCKET
        try{
            in = new FileInputStream(args[0]);
            prop.load(in);
            String host = prop.getProperty("server");
            int serv = Integer.parseInt(prop.getProperty("port"));
            Socket s = new Socket(host, serv);
            DataInputStream clientdis = new DataInputStream(s.getInputStream());
            DataOutputStream clientdos = new DataOutputStream(s.getOutputStream());
            String clienttoServer="";
            String servertoClient="";
            while(!clienttoServer.toLowerCase().equals("exit")){
                System.out.print("Chat To Server : ");
                clienttoServer = br.readLine();
                clientdos.writeUTF(clienttoServer);
                clientdos.flush();
                servertoClient = clientdis.readUTF();
                System.out.println("Message From Server : "+servertoClient);
            }
            clientdos.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}