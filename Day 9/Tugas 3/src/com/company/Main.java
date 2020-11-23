package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static Scanner keyboard = new Scanner(System.in);
    public static Support support = new Support();
    public static boolean LoggedIn = false;
    public static Conn jdbc;
    public static void main(String[] args) {
        do {
            support.clearScreen();
            System.out.println("LOGIN");
            System.out.print("Username : ");
            String username = keyboard.nextLine();
            System.out.print("Password : ");
            String password = keyboard.nextLine();
            try {
                jdbc = new Conn();
                jdbc.start();
                doLogin(username, password);
                if (LoggedIn) {
                    System.out.println("Berhasil Login");
                    support.pressAnyKeyToContinue();
                    menu();
                    //do something after login
                    System.out.println("Shutdown the Server");

                    System.out.println("Done!");
                } else {
                    System.out.println("Gagal Login");
                    support.pressAnyKeyToContinue();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!LoggedIn);
        System.out.println("Closing the Connection");
        try {
            jdbc.getConn().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Closed!");
    }

    public static void doLogin(String email, String pswd) throws IOException {
        boolean emailCorrect = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).matches();
        boolean pswdCorrect = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$").matcher(pswd).matches();
        if (emailCorrect && pswdCorrect){
            Auth auth = new Auth(email,pswd);
            LoggedIn = auth.Login(email,pswd,jdbc);
        }else {
            System.out.println("Password harus lebih dari 8 character, ada huruf besar dan kecil, memiliki special character");
        }
    }

    public static void menu() throws UnknownHostException, IOException {
        int keyInput;
        do {
            support.clearScreen();
            System.out.println("MENU\n1. Input Data to DB\n2. Edit Data from DB\n3. Delete Data from DB\n0. Exit\n");
            System.out.print("Pilih Menu : ");
            keyInput = Integer.parseInt(keyboard.nextLine());
            switch (keyInput) {
                case 1:
                    support.clearScreen();
                    System.out.print("Fullname : ");
                    String fullname = keyboard.nextLine();
                    System.out.print("Nickname : ");
                    String name = keyboard.nextLine();
                    System.out.print("Address : ");
                    String address = keyboard.nextLine();
                    System.out.print("Status : ");
                    String status = keyboard.nextLine();
                    System.out.print("Physics Grade : ");
                    int physics = Integer.parseInt(keyboard.nextLine());
                    System.out.print("Calculus Grade : ");
                    int calculus = Integer.parseInt(keyboard.nextLine());
                    System.out.print("Biology Grade : ");
                    int biology = Integer.parseInt(keyboard.nextLine());
                    Siswa siswa= new Siswa(fullname,name,address,status,physics,calculus,biology);
                    siswa.inputData(jdbc);
                    support.pressAnyKeyToContinue();
                    break;
                case 2:
                    support.clearScreen();
                    Siswa edit = new Siswa();
                    edit.showData(jdbc);
                    System.out.print("Edit ID Data : ");
                    int id = Integer.parseInt(keyboard.nextLine());
                    System.out.print("Fullname : ");
                    String fullname1 = keyboard.nextLine();
                    System.out.print("Nickname : ");
                    String name1 = keyboard.nextLine();
                    System.out.print("Address : ");
                    String address1 = keyboard.nextLine();
                    System.out.print("Status : ");
                    String status1 = keyboard.nextLine();
                    System.out.print("Physics Grade : ");
                    int physics1 = Integer.parseInt(keyboard.nextLine());
                    System.out.print("Calculus Grade : ");
                    int calculus1 = Integer.parseInt(keyboard.nextLine());
                    System.out.print("Biology Grade : ");
                    int biology1 = Integer.parseInt(keyboard.nextLine());
                    edit.editData(jdbc,id,fullname1,name1,address1,status1,physics1,calculus1,biology1);
                    support.clearScreen();
                    edit.showData(jdbc);
                    support.pressAnyKeyToContinue();
                    break;
                case 3:
                    support.clearScreen();
                    Siswa delete = new Siswa();
                    delete.showData(jdbc);
                    System.out.print("Delete ID Data : ");
                    int id1 = Integer.parseInt(keyboard.nextLine());
                    delete.deleteData(jdbc,id1);
                    support.clearScreen();
                    delete.showData(jdbc);
                    support.pressAnyKeyToContinue();
                    break;
                default:
                    break;
            }
        } while (keyInput != 0);
    }
}
