package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class MainWorker{
    static ArrayList<Staff> dataStaff = new ArrayList<>();
    static ArrayList<Manager> dataManager = new ArrayList<>();
    static InputStreamReader r=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(r);
    static Integer totalGajiManager = 0;
    static Integer totalGajiStaff = 0;
    public static void main(String[] args) {
        try {
           showMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addWorker(){
        String choose = "";
        String nama;
        int id = 0;
        while(!choose.equals("3")){
            try {
                System.out.println("Add Menu");
                System.out.println("1. Add Manager");
                System.out.println("2. Add Staff");
                System.out.println("3. Exit");
                System.out.print("Choose your Menu : ");
                choose = br.readLine();
                switch (choose) {
                    case "1":
                        System.out.print("Input id Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.print("Input Nama : ");
                        nama = br.readLine();
                        String telp = "";
                        ArrayList dataTelp = new ArrayList();
                        while(!telp.equals("done")){
                            System.out.print("Input No Telepon : ");
                            String Telptemp = br.readLine();
                            if (Telptemp.toLowerCase().equals("done")){
                                break;
                            }else{
                                dataTelp.add(Telptemp);
                            }
                        }
                        Manager manager = new Manager(id, nama, dataTelp);
                        dataManager.add(manager);
                        break;
                    case "2":
                        System.out.print("Input id Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.print("Input Nama : ");
                        nama = br.readLine();
                        String email = "";
                        JSONArray dataEmail = new JSONArray();
                        while(!email.toLowerCase().equals("done")){
                            System.out.print("Input Email : ");
                            String Emailtemp = br.readLine();
                            if (Emailtemp.equals("done")){
                                break;
                            }else{
                                dataEmail.add(Emailtemp);
                            }
                        }
                        Staff staff = new Staff(id, nama, dataEmail);
                        dataStaff.add(staff);
                        break;
                    case "3":
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void createJson(){
        JSONArray arrManager = new JSONArray();
        for (Manager mngr:dataManager) {
            JSONObject Managertemp = new JSONObject();
            Managertemp.put("ID :", mngr.getId());
            Managertemp.put("Nama :", mngr.getNama());
            Managertemp.put("Gaji Pokok :", mngr.getGapok());
            Managertemp.put("Absensi Hari :", mngr.getAbsen());
            Managertemp.put("Tunjangan Pulsa :", mngr.getTunjanganPulsa());
            Managertemp.put("Tunjangan Transportasi :", mngr.getTunjanganEntertaint());
            Managertemp.put("Tunjangan Entertainment :", mngr.getTunjanganTransport());

            JSONArray Telepontemp = new JSONArray();
            for (String data:mngr.getTelp()){
                Telepontemp.add(data);
            }
            Managertemp.put("No Telepon :", Telepontemp);
            arrManager.add(Managertemp);
        }
        JSONArray arrStaff = new JSONArray();
        for (Staff stf:dataStaff) {
            JSONObject Stafftemp = new JSONObject();
            Stafftemp.put("ID", stf.getId());
            Stafftemp.put("Nama", stf.getNama());
            Stafftemp.put("Gaji Pokok", stf.getGapok());
            Stafftemp.put("Absensi Hari", stf.getAbsen());
            Stafftemp.put("Tunjangan Pulsa", stf.getTunjanganPulsa());
            Stafftemp.put("Tunjangan Makan", stf.getTunjanganMakan());
            JSONArray Emailtemp = new JSONArray();
            for (String data:stf.getEmail()){
                Emailtemp.add(data);
            }
            Stafftemp.put("Email", Emailtemp);
            arrStaff.add(Stafftemp);
        }
        try {
            String output1 = arrManager.toJSONString();
            String output2 = arrStaff.toString();
            FileWriter fwManager = new FileWriter("C:\\Users\\btpnshifted\\IdeaProjects\\Day9Tugas1\\src\\com\\company\\manager.txt");
            FileWriter fwStaff = new FileWriter("C:\\Users\\btpnshifted\\IdeaProjects\\Day9Tugas1\\src\\com\\company\\staff.txt");
            fwManager.write(output1);
            fwManager.flush();
            fwManager.close();
            fwStaff.write(output2);
            fwStaff.flush();
            fwStaff.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void ReadJson(){
        try {
            System.out.print("Input File Name : ");
            String file = br.readLine();
            FileReader fr = new FileReader("C:\\Users\\btpnshifted\\IdeaProjects\\Day9Tugas1\\src\\com\\" +file);
            //C:\Users\btpnshifted\IdeaProjects\Day9Tugas1
            int i;
            String input = "";
            while((i=fr.read())!=-1){
                input += (char)i;
            }
            JSONArray arr= (JSONArray) JSONValue.parse(input);
            for (int j = 0; j < arr.size(); j++) {
                JSONObject tempData = (JSONObject) arr.get(j);
                System.out.println("ID : " + tempData.get("ID"));
                System.out.println("Nama : " + tempData.get("Nama"));
                System.out.println("Tunjangan Pulsa : " + tempData.get("Tunjangan Pulsa"));
                System.out.println("Gaji Pokok : " + tempData.get("Gaji Pokok"));
                System.out.println("Absensi Hari : " + tempData.get("Absensi Hari"));
                if (file.equals("staff.txt")){
                    System.out.println("Tunjangan Makan : " + tempData.get("Tunjangan Makan"));
                    JSONArray email = (JSONArray) tempData.get("Email");
                    System.out.print("Email : ");
                    for (int k = 0; k < email.size(); k++) {
                        System.out.print(email.get(k) + ", ");
                    }
                    System.out.print("\n\n");
                }else{
                    System.out.println("Tunjangan Transport : " + tempData.get("Tunjangan Transport"));
                    System.out.println("Tunjangan Entertaint : " + tempData.get("Tunjangan Entertaint"));
                    JSONArray email = (JSONArray) tempData.get("No Telepon");
                    System.out.print("No Telepon : ");
                    for (int k = 0; k < email.size(); k++) {
                        System.out.print(email.get(k) + ", ");
                    }
                    System.out.print("\n\n");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public static void showMenu() throws IOException {
        try{
            String choose = "0";
            while (!choose.equals("4")) {
                System.out.flush();
                System.out.println("Menu");
                System.out.println("1. Create Worker");
                System.out.println("2. Create and Write JSON");
                System.out.println("3. Read");
                System.out.println("4. Exit");
                System.out.print("Choose your Menu : ");
                choose = br.readLine();
                switch (choose) {
                    case "1": addWorker();
                    break;
                    case "2": createJson();
                    break;
                    case "3": ReadJson();
                    break;
                    default:
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}