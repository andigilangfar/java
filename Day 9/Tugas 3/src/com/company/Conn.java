package com.company;

import java.sql.*;

public class Conn {
    public java.sql.Connection con;
    public void start(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java","root","T3p4tBTPNS");
//here sonoo is database name, root is username and password
        }catch(Exception e){ System.out.println(e);}
    }
    public java.sql.Connection getConn(){
        return this.con;
    }
}
