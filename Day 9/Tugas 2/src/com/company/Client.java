package com.company;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.io.DataOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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

    public static void menuTiga() { //dis
        try {
            String output = "";
            JSONArray arr = (JSONArray) JSONValue.parse(data);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject objData = (JSONObject) arr.get(i);
                output = "Nama : " + objData.get("nama") + "\n";
                output = "Nilai Fisika : " + objData.get("fisika") + "\n";
                output = "Nilai Kimia : " + objData.get("kimia") + "\n";
                output = "Nilai Biologi : " + objData.get("biologi") + "\n";
                PrintToScreen t1 = new PrintToScreen(output);
                FTPUpload t3 = new FTPUpload(data);
                t1.start();
                t3.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//    public static void menuEmpat(){
//        try {
//            if (!s.isClosed()){
//                s.close();
//                System.out.println("Disconnect");
//            }else{
//                System.out.println("Not Connected");
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
        public static void menuEmpat(){
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
                    System.out.println("4. Close Connection");
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
//                        case 5:
//                            menuLima();
//                            break;
                        default:
                            break;
                    }
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

