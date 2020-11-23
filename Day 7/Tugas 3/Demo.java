import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;

import java.io.FileInputStream;
public class Demo{
    static ArrayList<Integer> dataList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            InputStreamReader r=new InputStreamReader(System.in);    
            BufferedReader br=new BufferedReader(r);  
            showMenu();
            Int menu = 0;
            while (menu!=4){
                menu = Integer.parseInt(br.readLine());
                readFile(args[0]);
                switch (menu) {
                    case 1:
                        menuSatu();
                        break;
                    case 2:
                        menuDua(Integer.parseInt(args[1]));
                        break;
                    case 3:
                        menuTiga(Integer.parseInt(args[1]));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void readFile(String fileName){
        try {
            FileInputStream fin=new FileInputStream(".\\" + fileName);
            String output = "";    
            int i=0;
            while((i=fin.read())!=-1){    
                output += (char)i;
            }    
            String[] Number = output.split("\\,", -1);
            for (String string : Number) {
                NumberList.add(Integer.parseInt(string));
            }
            fin.close();   
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void menuSatu(){
        ArrayList<SimpleThread> thread = new ArrayList<>();
        try{    
            for (Integer integer : NumberList) {
                SimpleThread tr = new SimpleThread(String.valueOf(integer));
                thread.add(tr);
            }
            Iterator itr = thread.iterator();
            while (itr.hasNext()) {
                SimpleThread next = (SimpleThread)itr.next();
                next.start();
            }
          }catch(Exception e){System.out.println(e);}
    }

    public static void menuDua(int Pool){
        ArrayList<Runnable> thread = new ArrayList<>();
        try{    
            ExecutorService executor = Executors.newFixedThreadPool(Pool); 
            for (Integer integer : NumberList) {
                Runnable tr = new ThreadPool(integer);
                thread.add(tr);
            }
            Iterator itr = thread.iterator();
            while(itr.hasNext()){
                Runnable tr = (Runnable)itr.next();  
                executor.execute(tr);//calling execute method of ExecutorService 
            }
            executor.shutdown();  
            while (!executor.isTerminated()) {   }  
          }catch(Exception e){System.out.println(e);}
    }

    public static void menuTiga(int Pool){
        try{    
            String Printout = "";
            Iterator itr = NumberList.iterator();
            while (itr.hasNext()) {
                Integer number = (Integer)itr.next();
                Printout += number + "\n"; 
            }
            ArrayList<Integer> Ascending = new ArrayList<>(NumberList);
            ArrayList<Integer> Descending = new ArrayList<>(NumberList);
            SimpleThread t1 = new SimpleThread(Printout);
            Ascending t2 = new Ascending(Ascending);
            Descending t3 = new Descending(Descending);
            t1.start();
            t2.start(); 
            t3.start();
        }catch(Exception e){System.out.println(e);}
    }

    public static void showMenu(){
        System.out.println("Menu");
                System.out.println("");
                System.out.println("1. Simple Thread");
                System.out.println("2. Thread Pool");
                System.out.println("3. Print and Sort");
                System.out.println("4. Exit");
                System.out.print("Input Menu : ");
    }

}