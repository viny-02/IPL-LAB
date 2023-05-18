/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenitta;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.System.*;

/**
 *
 * @author Jenitta
 */
@WebService(serviceName = "WS_server")
public class WS_server  extends HttpServlet {

    /**
     * This is a sample web service operation
     * 
     */
    @WebMethod(operationName = "fetch")
     public String[][] fetch() {                 //@WebParam(name = "name") String txt
         
         String[][] str = new String[4][4];
        HttpServletResponse response = null;
       String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3307/coffee";

        // Database credentials
        String USER = "root";
        String PASS = "";

        String title = "Database Result";

        System.out.println(title);
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM coffee;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(" <style> th, td { ");
            System.out.println("padding-top: 10px;");
            System.out.println(" padding-bottom: 20px;");
            System.out.println("padding-left: 30px;");
            System.out.println("padding-right: 40px;");
            System.out.println("} </style><center><div><table border = 1 >");
            System.out.println("<tr><td> Coffee Name </td>'");
            System.out.println("<td> Supplier </td>");
            System.out.println("<td> Price </td>");
            System.out.println("<td> Comment </td></tr>");
 
            
            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String Cofname = rs.getString("Cofname");
                String Sup_ID = rs.getString("Sup_ID");
                String price = rs.getString("price");
                String comment = rs.getString("comment");

                // Display values
                System.out.println("<tr> <td>" + Cofname + " </td>");
                System.out.println("<td>" + Sup_ID + "</td>");
                System.out.println("<td>" + price + "</td>");
                System.out.println("<td >" + comment + "</td>");
                System.out.println(
                        "<td > ");
                System.out.println("</td></tr> ");
                int j = 0;
                
                str[i][j] = Sup_ID;
                str[i][j+1] = Sup_ID;
                str[i][j+2] = price;
                str[i][j+3] = comment;
                i++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
        return str;
    }

       
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}