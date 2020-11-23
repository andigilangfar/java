import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.*;
import java.util.*;
import java.net.*;
public class Server{
    public static void main(String[] args){
        //CONFIG PROPERTIES
        Properties prop = new Properties();
        InputStream in = null;
        OutputStream out = null;
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        //CONFIG SOCKET
        try{
            out = new FileOutputStream(args[0]);
            prop.setProperty("server", "localhost");
            prop.setProperty("port", "1111");
            prop.store(out, null);
            in = new FileInputStream(args[0]);
            prop.load(in);
            int serv = Integer.parseInt(prop.getProperty("port"));
            ServerSocket ss= new ServerSocket(serv);
            Socket s= ss.accept ();//establishes connection
            DataInputStream serverdis= new DataInputStream(s.getInputStream());
            DataOutputStream serverdos= new DataOutputStream(s.getOutputStream());
            String clienttoServer="";
            String servertoClient="";
            while(!servertoClient.toLowerCase().equals("exit")){
                clienttoServer = serverdis.readUTF();
                System.out.println("Message : "+clienttoServer);
                System.out.println("Enter your Message to Client : ");
                servertoClient = br.readLine();
                serverdos.writeUTF(servertoClient);
                serverdos.flush();
            }
            serverdos.close();
            ss.close();
        }catch (Exception e){
                System.out.println (e);
            } finally {
		    if (out != null) {
			    try {
				    out.close();
			    } catch (IOException e) {
				    e.printStackTrace();
			    }
            }
        }
    }
}