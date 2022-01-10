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
public class Transaksi {
        static final String DB_URL = "jdbc:mysql://localhost/pos_netbeans";
   static final String USER = "root";
   static final String PASS = "";
   static final String QUERY = "SELECT id, name FROM kategori";
    public static void main(String[] args) 
    { String[][] listDataDetailPenjualan = listDataDetailPenjualan();
        System.out.println(Arrays.deepToString(listDataDetailPenjualan));
    }
    public static String[][] listDataDetailPenjualan()
    {
        
        int i=0;
        int j=0;
        String QUERY = "SELECT id, name,harga,qty FROM detail_penjualan where id_transaksi is null";
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
            returnData[i][0] = rs.getString("id");
            returnData[i][1] = rs.getString("name");
            returnData[i][2] = rs.getString("harga");
            returnData[i][3] = rs.getString("qty");
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
