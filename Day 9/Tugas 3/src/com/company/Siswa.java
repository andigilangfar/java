package com.company;
import java.sql.*;
public class Siswa {
    String fullname,name,address,status;
    int physics,calculus,biologi;
    public Siswa(String fullname, String name, String address, String status, int physics, int calculus, int biologi){
        this.fullname = fullname;
        this.name = name;
        this.address = address;
        this.status = status;
        this.physics = physics;
        this.calculus = calculus;
        this.biologi = biologi;
    }
    public Siswa(){

    }
    public void showData(Conn jdbc){
        try{
            Statement stmt=jdbc.getConn().createStatement();
            ResultSet rs=stmt.executeQuery(String.format("select * from siswa"));
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getInt(6)+"  "+rs.getInt(7)+"  "+rs.getInt(8));
            }
        }catch(Exception e){ System.out.println(e);}
    }

    public void editData(Conn jdbc,int id,String fullname, String name, String address, String status, int physics, int calculus, int biologi){
        try{
            Statement stmt=jdbc.getConn().createStatement();
            int result=stmt.executeUpdate("update siswa set name='"+
                    name+"',fullname='"+fullname+"',address='"+address+"',status='"+status+"',physics="+physics+",calculus="+calculus+",biologi="+biologi+" where id="+id);
            if(result!=0){
                System.out.println(result+" records affected");
            } else {
                System.out.println("data not valid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void inputData(Conn jdbc){
        try{
            Statement stmt=jdbc.getConn().createStatement();
            int result=stmt.executeUpdate("insert into siswa values(null,'"+
                    name+"','"+fullname+"','"+address+"','"+status+"',"+physics+","+calculus+","+biologi+")");
            if(result!=0){
                System.out.println(result+" records affected");
            } else {
                System.out.println("id not valid or data not valid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteData(Conn jdbc, int id){
        try{
            Statement stmt=jdbc.getConn().createStatement();
            int result=stmt.executeUpdate("delete from siswa where id="+id);
            if(result!=0){
                System.out.println(result+" records affected");
            } else {
                System.out.println("id not valid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
