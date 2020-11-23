import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.util.Scanner;
import java.util.Properties;

public class MyServer{
    public static Scanner scan = new Scanner(System.in);
    public static InputStreamReader r = new InputStreamReader(System.in);
    public static BufferedReader br = new BufferedReader(r);
    public static Properties prop = new Properties();
    public static InputStream input = null;
    public static void main(String []args){
        try {
            System.out.print("Username : ");
            String loginUser = br.readLine();
            if(Pattern.matches("[abc][^abc][^abc][^abc]", loginUser)){
                    System.out.print("Password : ");
                    String loginPass = br.readLine();
                if (Pattern.matches("[abc][^abc][^abc][^abc]", loginPass)) {
                    FileReader ruser=new FileReader(".\\Susername.txt");    
                    int i = 0 ; 
                    String username = "";   
                    while((i=ruser.read())!=-1)    
                        username += (char)i;
                    ruser.close();
                    FileReader rpass=new FileReader(".\\Spassword.txt");    
                    i = 0 ; 
                    String password = "";   
                    while((i=rpass.read())!=-1)    
                        password += (char)i;
                    rpass.close();
                    System.out.println(username);
                    System.out.println(password);
                    if(loginUser.equals(username) && loginPass.equals(password)){
                        // Properties prop = new Properties();
                OutputStream output = null;
                int socket = 1234;
                output = new FileOutputStream("config.properties");
                prop.setProperty("IP", "localhost");
                prop.setProperty("PORT", String.valueOf(socket));
                prop.store(output, null);
                ServerSocket ss = new ServerSocket(socket);
                System.out.println("Getting Socket");
                Socket s = ss.accept();
                System.out.println("Get Data");
                String data = "";
                while(!data.equals("exit")) {
                    DataInputStream serverdis = new DataInputStream(s.getInputStream());
                    data = (String)serverdis.readUTF();
                    String[] data1 = data.split("\\n");
                    for (String string : data1) {
                        String[] data2 = string.split("\\,");
                        System.out.println(String.join("\n", "Nama : " + data2[0], "Nilai Fisika : " + data2[1], "Nilai Biologi : " + data2[2], "Nilai Kimia : " + data2[3]));
                    }
                    DataOutputStream serverdos = new DataOutputStream(s.getOutputStream());
                    serverdos.writeUTF("Done");
                    serverdos.flush();
                        }
                    }else{
                        System.out.println("Wrong Username or Password");
                    }         
                } 
            }
        }catch (Exception e) {
            System.out.print(e);
        }
    }
}