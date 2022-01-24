/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devpos.models;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @author Gunawan Bayu
 */
public class Kategori {
    private static String DB_URL = "jdbc:mysql://localhost/pos_netbeans";
    private static String USER = "root";
    private static String PASS = "";
    
    public static void main(String[] args) {
    }
    public static String[][] listData()
    {
        
        int i=0;
        int j=0;
        String QUERY = "SELECT id, name FROM kategori";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            rs.last();                              //move the cursor to the last row
            int numberOfRows = rs.getRow();         //get the number of rows
            rs.beforeFirst();                       
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnS = rsmd.getColumnCount();
            String[][] returnData = new String[numberOfRows][columnS];
 
            
         while(rs.next()){
            //Display values
            System.out.print(", name: " + columnS);
            returnData[i][0] = rs.getString("id");
            returnData[i][1] = rs.getString("name");
            i++;
            
        }
         return returnData;
        } catch (SQLException e) {
         e.printStackTrace();
        }    
 
        // Return statement of the method.
        
        return null;
    }
    public static String create (String data) {
        try {
            String sql = "INSERT INTO kategori (name) VALUES ('"+data+"');";
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           String result = "Penyimpanan Data Berhasil";
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
    public static String edit (String data,String id) {
        try {
            String sql = "UPDATE kategori SET name = '"+data+"' WHERE id = '"+id+"';";
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           String result = " Data Berhasil Di Ubah";
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
    public static String delete (String id) {
        try {
            String sql = "DELETE FROM kategori WHERE id='"+id+"';";
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           String result = " Data Berhasil Di Hapus";
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
 
    
}
