import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import javax.xml.crypto.dsig.SignedInfo;

public class MahasiswaDemo {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();
    public static void main(String args[])throws Exception{
        
        try {
            System.out.print("Username : ");
            String loginUser = br.readLine();
            if(Pattern.matches("[abc][^abc][^abc][^abc]", loginUser)){
                    System.out.print("Password : ");
                    String loginPass = br.readLine();
                if (Pattern.matches("[abc][^abc][^abc][^abc]", loginPass)) {
                    FileReader ruser=new FileReader(".\\username.txt");    
                    int i = 0 ; 
                    String username = "";   
                    while((i=ruser.read())!=-1)    
                        username += (char)i;
                    ruser.close();
                    FileReader rpass=new FileReader(".\\password.txt");    
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
    
    public static void showMenu(){
        String menu = "";
        try {
            while(!menu.equals("4")) {
                System.out.println("Menu");
                System.out.println("1. Add Mahasiswa");
                System.out.println("2. Edit Mahasiswa");
                System.out.println("3. Print Data Mahasiswa");
                System.out.println("4. Exit");
                System.out.print("Select Menu : ");
                menu = br.readLine();
                switch (menu) {
                    case "1":
                        System.out.println();
                        menuSatu();
                        break;
                    case "2":
                        System.out.println();
                        menuDua();
                        break;
                    case "3":
                        System.out.println();
                        menuTiga();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
       
        }
    }
    public static void menuSatu(){
        try {
            System.out.print("Enter Your ID : ");
            int id = Integer.parseInt(br.readLine());
            System.out.print("Enter Your Name: ");
            String name = br.readLine();
            System.out.print("Nilai Bahasa Inggris   : ");
            Double Binggris = Double.parseDouble(br.readLine());
            System.out.print("Nilai Fisika   : ");
            Double Fisika = Double.parseDouble(br.readLine());
            System.out.print("Nilai Algoritma : ");
            Double Algoritma = Double.parseDouble(br.readLine());
            Mahasiswa mahasiswa = new Mahasiswa(id, name, Binggris, Fisika, Algoritma); 
            dataMahasiswa.add(mahasiswa); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void menuDua(){
        try {
            String menu = "";
            while(!menu.equals("3")) {
                System.out.println("Menu");
                System.out.println("1. Edit Mahasiswa");
                System.out.println("2. Delete Mahasiswa");
                System.out.println("3. Exit");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (menu) {
                    case "1":
                        editMahasiswa();
                        break;
                    case "2":
                        deleteMahasiswa();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void editMahasiswa(){
        try {
            System.out.print("Enter Your ID : ");
            int idEdit = Integer.parseInt(br.readLine());
            Mahasiswa dataEdit = dataMahasiswa.get(BinarySearch(dataMahasiswa, idEdit));      
            System.out.println("ID : " + idEdit);
            System.out.println("Name : " + dataEdit.getName());
            System.out.println("UTS : " + dataEdit.getNilai(0));
            System.out.println("UAS : " + dataEdit.getNilai(1));
            System.out.println("Tugas : " + dataEdit.getNilai(2));
            System.out.println("");
            System.out.print("Name : ");
            String editName = br.readLine();
            System.out.print("Nilai Bahasa Inggris : ");
            Double editBing = Double.parseDouble(br.readLine());
            System.out.print("Nilai Fisika : ");
            Double editFisika = Double.parseDouble(br.readLine());
            System.out.print("Nilai Algoritma : ");
            Double editAlg = Double.parseDouble(br.readLine());
    

            dataEdit.setName(editName);
            dataEdit.nilai.set(0, editBing);
            dataEdit.nilai.set(1, editFisika);
            dataEdit.nilai.set(2, editAlg);
            
            System.out.print("Enter");
            br.readLine();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void deleteMahasiswa(){
        try {
            System.out.print("Enter Your ID : ");
            int idEdit = Integer.parseInt(br.readLine());  
            dataMahasiswa.remove(BinarySearch(dataMahasiswa, idEdit));
            System.out.print("Enter");
            br.readLine();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void menuTiga(){
        try {
            BubbleSort(dataMahasiswa);
            System.out.println("Print Out Data Mahasiswa");
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.format("| Name       | B.Inggris  | Fisika  | Algoritma |%n");
            System.out.format("+------------+------------+---------+-----------+%n");
            Iterator itr = dataMahasiswa.iterator();
            while(itr.hasNext()){  
                Mahasiswa data = (Mahasiswa)itr.next();
                System.out.format(formatTable, data.getName(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.print("Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void BubbleSort(ArrayList<Mahasiswa> List){
        for (int i = 0; i < List.size() - 1; i++) {
            for (int j = 0; j < List.size() - 1 - i; j++) {
                if (List.get(j + 1).getID() < List.get(j).getID()) {
                    Mahasiswa temp = List.get(j);
                    List.set(j, List.get(j+1));
                    List.set(j+1, temp);
                }
            }
        }
    }
    public static int BinarySearch(ArrayList<Mahasiswa> List, int id) {
        BubbleSort(List);
        int target = id; 
        int left = 0;
        int middle = 0;
        int right = List.size() - 1;
        while (left <= right) {
            middle = (left + right) / 2;
            if (List.get(middle).getID() == target) {
                break;
            } else if (List.get(middle).getID() < target) {
                left = middle + 1;
            } else if (List.get(middle).getID() > target) {
                right = middle - 1;
            }
        }
        return middle;
    }
}
