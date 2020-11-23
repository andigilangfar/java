import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class PrintWrite extends Thread{
    ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();
    String output = "";
    public void run(){
        try{
            FileWriter fw=new FileWriter(".\\AllMahasiswa.txt");
            Iterator itr=dataMahasiswa.iterator();
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            output += output.format("+------------+------------+---------+-----------+%n");
            output += output.format("| Name       | B.Inggris  | Fisika  | Algoritma |%n");
            output += output.format("+------------+------------+---------+-----------+%n");
            while(itr.hasNext()){   
                Mahasiswa data = (Mahasiswa)itr.next();
                output += output.format(formatTable, data.getName(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
            output += output.format("+------------+------------+---------+-----------+%n");
            fw.write(output);    
            fw.close();  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    PrintWrite(ArrayList<Mahasiswa> newMahasiswa){
        this.dataMahasiswa = newMahasiswa;
    }
}
