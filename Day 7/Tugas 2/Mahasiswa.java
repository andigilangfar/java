import java.util.*;
public class Mahasiswa{
    int id = 0;
    String name;
    ArrayList<Double> nilai = new ArrayList<>();

    public Mahasiswa(int id, String name, Double bing, Double fisika, Double alg){
        this.id = id;
        this.name = name;
        plusNilai(bing, fisika, alg);
    }

    public void plusNilai(Double Binggris, Double Fisika, Double Algoritma){
        this.nilai.add(Binggris);
        this.nilai.add(Fisika);
        this.nilai.add(Algoritma);
    }

    public Double getNilai(int index){
        return this.nilai.get(index);
    }
    
    public void setID(int newId){
        id = newId;
    }

    public int getID(){
        return id;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

}