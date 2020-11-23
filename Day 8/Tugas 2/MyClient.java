import java.io.*;  
import java.net.*;  
import java.util.Properties;
import java.util.regex.*; 

public class MyClient{
    public static InputStreamReader r = new InputStreamReader(System.in);
    public static BufferedReader br = new BufferedReader(r);
    public static Properties prop = new Properties();
    public static InputStream input = null;
    public static Socket s;
    public static void main (String[] args){

        try {
            System.out.print("Username : ");
            String loginUser = br.readLine();
            if(Pattern.matches("[abc][^abc][^abc][^abc]", loginUser)){
                    System.out.print("Password : ");
                    String loginPass = br.readLine();
                if (Pattern.matches("[abc][^abc][^abc][^abc]", loginPass)) {
                    FileReader ruser=new FileReader(".\\Cusername.txt");    
                    int i = 0 ; 
                    String username = "";   
                    while((i=ruser.read())!=-1)    
                        username += (char)i;
                    ruser.close();
                    FileReader rpass=new FileReader(".\\Cpassword.txt");    
                    i = 0 ; 
                    String password = "";   
                    while((i=rpass.read())!=-1)    
                        password += (char)i;
                    rpass.close();
                    System.out.println(username);
                    System.out.println(password);
                    if(loginUser.equals(username) && loginPass.equals(password)){
                        showMenu();
                    }else{
                        System.out.println("Username or Password doesnt Exist !");
                    }
                }else{
                    System.out.println("Wrong Password");
                }
            }else{
                System.out.println("Wrong Username");
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        // try{
        //     boolean loginUser = 
        //     System.out.println("Enter Your Username : ");
        //     String username = br.readLine();
        //     System.out.println("Enter Your Password : ");
        //     String password = br.readLine();
        //     if(Pattern.matches("[abc][^abc][^abc][^abc]", username)&&Pattern.matches("[abc][^abc][^abc][^abc]", password)){
        //         showMenu();
        //     }
        // }catch(Exception e){
        //     System.out.println(e);
        // }

    public static void showMenu(){
        String choose ="";
        try{
            while(!choose.equals("4")){
                System.out.println("MAIN MENU");
                System.out.println("1. Connect to Socket");
                System.out.println("2. Send Data");
                System.out.println("3. Close Socket");
                System.out.println("4. Exit");
                choose = br.readLine();
                switch(choose){
                    case "1": menuSatu();
                    break;
                    case "2": menuDua();
                    break;
                    case "3": menuTiga();
                    break;
                    default: break;
                }
            }
        }catch (Exception e){
            System.out.println (e);
        }
    }

    public static void menuSatu(){
        try {
            // Properties prop = new Properties();
            input = new FileInputStream("config.properties");
            prop.load(input);
            int port = Integer.parseInt(prop.getProperty("PORT"));
            String ip = prop.getProperty("IP");
            s = new Socket(ip, port);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void menuDua(){
        String clientresponse="";
        try {
            FileReader fr = new FileReader(".\\data.txt");
            int i;
            String data = "";
            while((i = fr.read()) !=-1) {
                data += (char)i;
            }
            fr.close();
            DataOutputStream clientdos = new DataOutputStream(s.getOutputStream());
            clientdos.writeUTF(data);
            clientdos.flush();
            DataInputStream clientdis = new DataInputStream(s.getInputStream());
            clientresponse = clientdis.readUTF();
            System.out.println(clientresponse);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void menuTiga(){
        try{
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
