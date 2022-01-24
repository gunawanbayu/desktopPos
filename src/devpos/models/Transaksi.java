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
    private static  String DB_URL = "jdbc:mysql://localhost/pos_netbeans";
    private static  String USER = "root";
    private static  String PASS = "";
    
    public static void main(String[] args) 
    { String[][] listDataDetailPenjualan = listDataDetailPenjualan(" id_transaksi is null");
        
        String createData   = createDetailPenjualan("testing","5000","1","1");
    }
    public static String[][] listDataDetailPenjualan( String dataWhere)
    {
        
        int i=0;
        int j=0;
        int dataKepo=0;
        String QUERY = dataWhere;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            rs.last();                              //move the cursor to the last row
            int numberOfRows = rs.getRow();         //get the number of rows
            rs.beforeFirst();                       
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnS = rsmd.getColumnCount();
            String[][] returnData = new String[numberOfRows][6];
            
         while(rs.next()){
             
              
              if(dataWhere == "SELECT * FROM penjualan" || dataWhere == "SELECT * FROM penjualan ORDER BY ID DESC LIMIT 1"){
                returnData[i][0] = rs.getString("id");
                returnData[i][1] = rs.getString("tanggal_penjualan");
                returnData[i][2] = rs.getString("kembalian");
                returnData[i][3] = rs.getString("total_harga");
                returnData[i][4] = rs.getString("uang");
              }else{
                dataKepo = Integer.parseInt(rs.getString("qty")) * Integer.parseInt(rs.getString("harga"));
                returnData[i][0] = rs.getString("id_barang");
                returnData[i][1] = rs.getString("name");
                returnData[i][2] = rs.getString("harga");
                returnData[i][3] = rs.getString("qty");
                returnData[i][4] = Integer.toString(dataKepo);
                returnData[i][5] = rs.getString("id");
              }
            
            i++;
            
        }
         return returnData;
        } catch (SQLException e) {
         e.printStackTrace();
        }    
 
        // Return statement of the method.
        
        return null;
    }
    public static String createDetailPenjualan (String name, String harga,String qty,String idBarang) {
        try {
            String[][]  dataList = listDataDetailPenjualan("SELECT id_barang, name,harga,qty,id FROM detail_penjualan where id_transaksi is null and id_barang = "+idBarang+"");
            if("[]".equals(Arrays.deepToString(dataList))){
                System.out.println(Arrays.deepToString(dataList));
                String sql = "INSERT INTO detail_penjualan (name,harga,qty,id_barang) VALUES ('"+name+"','"+harga+"','"+qty+"','"+idBarang+"');";
                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
            }else{
//                System.out.println(Arrays.deepToString(dataList[0][3]));
                int  dataSumQty = Integer.parseInt(dataList[0][3]) + 1;
                String sql = "UPDATE detail_penjualan SET name='"+name+"',harga='"+harga+"',qty='"+(dataSumQty)+"' where id_barang = '"+idBarang+"' ;";
                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
            }
            String result = "Penyimpanan Data Berhasil";
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
    public static String editDetailPenjualan (String name, String harga,String qty,String idBarang) {
        try {
            String[][]  dataList = listDataDetailPenjualan("SELECT id_barang, name,harga,qty,id FROM detail_penjualan where id_transaksi is null and id_barang = "+idBarang+"");
            int  dataSumQty = Integer.parseInt(dataList[0][3]) - 1;
            if(Integer.parseInt(dataList[0][3]) <=1){
                String sql = "DELETE FROM detail_penjualan WHERE id_barang='"+idBarang+"';";
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            }else{
                String sql = "UPDATE detail_penjualan SET name='"+name+"',harga='"+harga+"',qty='"+(dataSumQty)+"' where id_barang = '"+idBarang+"' ;";
                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
            }
            
           String result = " Data Berhasil Di Ubah";
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
    public static String createPenjualan (String name, String harga,String qty,String idBarang) {
        try {
            
                String sql = "INSERT INTO penjualan (kembalian,total_harga,uang) VALUES ('"+name+"','"+harga+"','"+qty+"');";
                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                String[][]  dataList = listDataDetailPenjualan("SELECT * FROM penjualan ORDER BY ID DESC LIMIT 1");
//                System.out.println(Arrays.deepToString(dataList[0][3]));
         
                String sql2 = "UPDATE detail_penjualan SET id_transaksi='"+dataList[0][0]+"' where id_transaksi is null ;";
                java.sql.Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
                java.sql.PreparedStatement pst2=conn2.prepareStatement(sql2);
                pst2.execute();

            String result = "Penyimpanan Data Berhasil";
            return result;
        } catch (Exception e) {
            String result = e.getMessage();
            return result;
        }
    }
 
    
}
