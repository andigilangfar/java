import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class Descending extends Thread{
    ArrayList<Integer> dataDescending = new ArrayList<>();
    String output = "";
    public void run(){
        try {
            Sort();
            FileWriter fw=new FileWriter(".\\DataDescending.txt");
            Iterator itr = dataDescending.iterator();
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
    Descending(ArrayList<Integer> List){
        this.dataDescending = List;
    }
    void Sort(){
        for (int i = 0; i < dataDescending.size() - 1; i++) {
            for (int j = 0; j < dataDescending.size() - 1 - i; j++) {
                if (dataDescending.get(j) < dataDescending.get(j + 1)) {
                    Integer temp = dataDescending.get(j);
                    dataDescending.set(j, dataDescending.get(j+1));
                    dataDescending.set(j+1, temp);
                }
            }
        }
    }
}
