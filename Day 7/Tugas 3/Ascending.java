import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class Ascending extends Thread{
    ArrayList<Integer> dataAscending = new ArrayList<>();
    String output = "";
    public void run(){
        try {
            Sort();
            FileWriter fw=new FileWriter(".\\DataAscending.txt");
            Iterator itr = dataAscending.iterator();
            while (itr.hasNext()) {
                Integer Number = (Integer)itr.next();
                output += Number + "\n";
            }  
            fw.write(output);    
            fw.close(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    Ascending(ArrayList<Integer> List){
        this.dataAscending = List;
    }
    void Sort(){
        for (int i = 0; i < dataAscending.size() - 1; i++) {
            for (int j = 0; j < dataAscending.size() - 1 - i; j++) {
                if (dataAscending.get(j + 1) < dataAscending.get(j)) {
                    int temp = Ascending.get(j);
                    dataAscending.set(j, dataAscending.get(j+1));
                    dataAscending.set(j+1, temp);
                }
            }
        }
    }
}
