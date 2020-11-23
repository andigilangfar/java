package com.company;
import java.sql.*;


public class Auth {
    String user, paswd;
    boolean isLoggedIn;
    Auth(String user, String paswd){
    this.user=user;
    this.paswd=paswd;
    }
    public boolean Login(String user, String paswd, Conn jdbc){
        try{
            Statement stmt=jdbc.getConn().createStatement();
            ResultSet rs=stmt.executeQuery("select * from employees");
            if(rs.next()){
                isLoggedIn=true;
            }
        }catch(Exception e){ System.out.println(e);}
        return isLoggedIn;
    }
}
