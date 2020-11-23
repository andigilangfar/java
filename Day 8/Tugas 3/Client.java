package com.company;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.io.DataOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Client {
    static InputStreamReader r=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(r);
    static Socket s;
    static String data;

    //Main Method
    public static void main(String[] args) {
        try{
            showMenu();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void menuSatu(){
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream("C:\\Users\\btpnshifted\\Documents\\javacoba\\cobajava1\\Day8\\Tugas3\\config.properties");
            prop.load(input);
            String ip = prop.getProperty("IP");
            int port = Integer.parseInt(prop.getProperty("PORT"));
            s = new Socket(ip,port);
            DataOutputStream clientdos = new DataOutputStream(s.getOutputStream());
            clientdos.writeUTF("Request Data");
            clientdos.flush();
            DataInputStream clientdis = new DataInputStream(s.getInputStream());
            data = clientdis.readUTF();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void menuDua(){
        try {
            String output = "";
            String[] split1 = data.split("\\n");
            for (String string : split1) {
                String[] data2 = string.split("\\,");
                for (int i = 0; i < data2.length; i++) {
                    if(i==0){
                        output += "Nama : " + data2[i] + "\n";
                    }else if (i==1) {
                        output += "Nilai Fisika : " + data2[i] + "\n";
                    }else if (i==2) {
                        output += "Nilai Biologi : " + data2[i] + "\n";
                    }else if (i==3) {
                        output += "Nilai Kimia : " + data2[i] + "\n\n";
                    }
                }
            }
            FileWriter fr = new FileWriter("Process.txt");
            fr.write(output);
            fr.close();
        }catch (Exception e){
            System.out.print(e);
        }
    }

    public static void menuTiga(){ //dis
        try {
            String output = "";
            String[] split1 = data.split("\\n");

            for (String string : split1) {
                String[] data2 = string.split("\\,");
                for (int i = 0; i < data2.length; i++) {
                    if(i==0){
                        output += "Nama : " + data2[i] + "\n";
                    }else if (i==1) {
                        output += "Nilai Fisika : " + data2[i] + "\n";
                    }else if (i==2) {
                        output += "Nilai Biologi : " + data2[i] + "\n";
                    }else if (i==3) {
                        output += "Nilai Kimia : " + data2[i] + "\n\n";
                    }
                }
            }
            PrintToScreen t1 = new PrintToScreen(output);
            Average t2 = new Average(data);
            FTPUpload t3 = new FTPUpload();
            t1.start();
            t2.start();
            t3.start();
        }catch (Exception e){System.out.println(e);}
    }


    public static void menuEmpat(){
        try {
            String server = "ftp.myth.co.id";
            int port = 21;
            String user = "ftpuser@myth.co.id";
            String pass = "password";

            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                String remoteFile1 = "/download/Average.txt";
                File downloadFile1 = new File("C:\\Download\\Average.txt");
                OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
                outputStream1.close();

                if (success) {
                    System.out.println("File has been Downloaded");
                }
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void menuLima(){
        try {
            s.close();
            System.out.println("Disconnected");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void showMenu(){
        try {
            int choose = 0;
            while(choose!=99){
                // Print Menu
                System.out.println("Main Menu");
                System.out.println("1. Connect Socket");
                System.out.println("2. Create File Process");
                System.out.println("3. Print Data)");
                System.out.println("4. Download Average");
                System.out.println("5. Close All Connection");
                System.out.println("99. Exit");
                System.out.print("Choose Your Menu : ");
                choose = Integer.parseInt(br.readLine());
                switch (choose){
                    case 1:
                        menuSatu();
                        break;
                    case 2:
                        menuDua();
                        break;
                    case 3:
                        menuTiga();
                        break;
                    case 4:
                        menuEmpat();
                        break;
                    case 5:
                        menuLima();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

