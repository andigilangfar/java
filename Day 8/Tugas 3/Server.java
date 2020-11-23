import java.io.*;
import java.net.*;
import java.io.FileOutputStream;
import java.util.Properties;

public class Server {
    static String data = "";
    static Socket s;
    public static void main(String[] args)
    {
        Boolean active = true;
        try {
            ReadFile(args[1]);
            connect(args[0], 1234);
            String serverrequest = "";
            while (active){
                DataInputStream request = new DataInputStream(s.getInputStream()); 
                serverrequest = request.readUTF(); 
                System.out.println(serverrequest); 
                DataOutputStream response = new DataOutputStream(s.getOutputStream()); 
                response.writeUTF(data); 
                response.flush();
             
            }
        }catch (Exception e){
            active = false;
        }
    }

   
    public static void connect(String config, int port)
    {
        try {
            Properties prop = new Properties();
            OutputStream output = null;
            output = new FileOutputStream(config);
            prop.setProperty("IP", "localhost"); 
            prop.setProperty("PORT", String.valueOf(port)); 
            prop.store(output, null); 
            ServerSocket ss=new ServerSocket(port); 
            System.out.println("Waiting Socket");
            s=ss.accept();
        }catch (Exception e){
            System.out.print(e);
        }
    }

   
    public static void ReadFile(String path)
    {
        try {
            FileReader fin = new FileReader(path); 
            int i;
            while((i=fin.read())!=-1){ 
                data += (char)i;
            }
        }catch (Exception e){System.out.println(e);}

    }
}
