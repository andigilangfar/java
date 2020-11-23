import java.util.ArrayList;
import java.util.Iterator;

public class PrintScreen extends Thread{
    ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();
    public void run(){
        try {
            Iterator itr=dataMahasiswa.iterator();
            System.out.println("Laporan");
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.format("| Name       | B. Inggris  | Fisika  | Algoritma |%n");
            System.out.format("+------------+------------+---------+-----------+%n");
            while(itr.hasNext()){  
                Mahasiswa data = (Mahasiswa)itr.next();
                System.out.format(formatTable, data.getName(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    PrintScreen(ArrayList<Mahasiswa> newMahasiswa){
        this.dataMahasiswa = newMahasiswa;
    }
}
