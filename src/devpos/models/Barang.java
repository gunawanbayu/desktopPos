/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package devpos.models;

//library connection mysql
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
public class Barang {
    //connection
    public static final String DB_URL = "jdbc:mysql://localhost/pos_netbeans";
    public static final String USER ="root";
    public static final String PASSWORD="";
    
    public static void main(String[] args){
        String[][] showData = listData();
        String createData   = create("testing","5000","1");
        String editData     = edit("3","dimsum crabstik","3000","1");
        String deleteData   = delete("5");
        
    }
    // show data in database 
    public static String[][] listData(){
        String query = "SELECT barang.id,barang.name,barang.harga,\n"+
                "kategori.id as kodeKategori, kategori.name as kategoriName from barang\n"+
                "inner join kategori on barang.kategori_id = kategori.id";
        try {
            int i =0;
            Connection conn =   DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stmt  =   conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs    =   stmt.executeQuery(query);
            rs.last();
            int numberOfRows = rs.getRow();
            rs.beforeFirst();
            ResultSetMetaData rsmd =   (ResultSetMetaData) rs.getMetaData();
            int columns = rsmd.getColumnCount();
            String[][] resultData = new String[numberOfRows][columns];
            while(rs.next()){
                resultData[i][0]    =   rs.getString("id");
                resultData[i][1]    =   rs.getString("name");
                resultData[i][2]    =   rs.getString("harga");
                resultData[i][3]    =   rs.getString("kodeKategori");
                resultData[i][4]    =   rs.getString("kategoriName");
                i++;
            }
            return resultData;
        } catch (Exception e) {
        }
        
        return null;
    }
    
    // create data in dataabse 
    public static String create(String name,String harga,String kategori_id ){
    
        try {
            String query = "insert into barang (name,harga,kategori_id) "
                    + "value ('"+name+"','"+harga+"','"+kategori_id+"')";
            Connection conn                     = DriverManager.getConnection(
                    DB_URL,USER,PASSWORD);
            java.sql.PreparedStatement pst      = conn.prepareStatement(query);
            pst.execute();
            String result = "data Berhasil Disimpan";
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public static String edit(String id,String name, String harga, String kategori_id){
        
        String query    =   "update barang set name='"+name+"', harga='"+harga+"'"
                + ",kategori_id='"+kategori_id+"' where id='"+id+"'";
        try {
             Connection conn     = 
                DriverManager.getConnection(DB_URL,USER,PASSWORD);
        java.sql.PreparedStatement pst = conn.prepareStatement(query);
        pst.execute();
        String result ="data Berhasil Di ubah ";
        return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String delete(String id){
        
        String query    =   "delete from barang where id='"+id+"'";
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            java.sql.PreparedStatement pst = conn.prepareStatement(query);
            pst.execute();
            String result ="data Berhasil Di Hapus";
            return result;
            
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
