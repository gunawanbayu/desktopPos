/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devpos.models;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import global.GlobalMysql;
//import static global.GlobalMysql.getDataList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;


/**
 *
 * @author Gunawan Bayu
 */
public class Transaksi extends GlobalMysql{
    private static  String DB_URL = "jdbc:mysql://localhost/pos_netbeans";
    private static  String USER = "root";
    private static  String PASS = "";
    
    public static void main(String[] args) 
    { String[][] listDataDetailPenjualan = listDataDetailPenjualan(" id_transaksi is null");
        
        String createData   = createDetailPenjualan("testing","5000","1","1");
    }
    public static String[][] listDataDetailPenjualan( String dataWhere)
    {
        String QUERY = dataWhere;
        if(dataWhere == "SELECT * FROM penjualan" || dataWhere == "SELECT * FROM penjualan ORDER BY ID DESC LIMIT 1"){
            String[] listData = new String[] {"id", "tanggal_penjualan","kembalian","total_harga","uang"};
            String[][] returnGlobal = getDataList(QUERY,listData);
            return returnGlobal;
        }else{
            String[] listData = new String[] {"id_barang","name","harga","qty","id"};
            String[][] returnGlobal = getDataList(QUERY,listData);
            String[][]   listDataTransaksi = new String[returnGlobal.length][6];
            System.out.println(returnGlobal.length);
            int dataKepo=0;
            for (int i = 0; i < returnGlobal.length; i++) {
                
                dataKepo = Integer.parseInt(returnGlobal[i][3]) * Integer.parseInt(returnGlobal[i][2]);
                listDataTransaksi[i][0] = returnGlobal[i][0];
                listDataTransaksi[i][1] = returnGlobal[i][1];
                listDataTransaksi[i][2] = returnGlobal[i][2];
                listDataTransaksi[i][3] = returnGlobal[i][3];
                listDataTransaksi[i][4] = Integer.toString(dataKepo);
                listDataTransaksi[i][5] = returnGlobal[i][4];
                
                
            }
            
            return listDataTransaksi;
        }
    }
    public static String createDetailPenjualan (String name, String harga,String qty,String idBarang) {
        try {
            String[][]  dataList = listDataDetailPenjualan("SELECT id_barang, name,harga,qty,id FROM detail_penjualan where id_transaksi is null and id_barang = "+idBarang+"");
            if("[]".equals(Arrays.deepToString(dataList))){
//                String sql = "INSERT INTO detail_penjualan (name,harga,qty,id_barang) VALUES ('"+name+"','"+harga+"','"+qty+"','"+idBarang+"');";
//                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//                pst.execute();
                String sql = "INSERT INTO detail_penjualan (name,harga,qty,id_barang) VALUES ('"+name+"','"+harga+"','"+qty+"','"+idBarang+"');";
                String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
//                return result;
            }else{
//                System.out.println(Arrays.deepToString(dataList[0][3]));
                int  dataSumQty = Integer.parseInt(dataList[0][3]) + 1;
                String sql = "UPDATE detail_penjualan SET name='"+name+"',harga='"+harga+"',qty='"+(dataSumQty)+"' where id_barang = '"+idBarang+"' ;";
                String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
//                return result;
//                String sql = "UPDATE detail_penjualan SET name='"+name+"',harga='"+harga+"',qty='"+(dataSumQty)+"' where id_barang = '"+idBarang+"' ;";
//                java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//                pst.execute();
                
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
            String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
            }else{
                String sql = "UPDATE detail_penjualan SET name='"+name+"',harga='"+harga+"',qty='"+(dataSumQty)+"' where id_barang = '"+idBarang+"' ;";
                String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
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
                mysqlCUD(sql,"Penyimpanan Data Berhasil");
                String[][]  dataList = listDataDetailPenjualan("SELECT * FROM penjualan ORDER BY ID DESC LIMIT 1");
                
                String sql2 = "UPDATE detail_penjualan SET id_transaksi='"+dataList[0][0]+"' where id_transaksi is null ;";
                mysqlCUD(sql2,"Penyimpanan Data Berhasil");

            String result = "Penyimpanan Data Berhasil";
            return result;
        } catch (Exception e) {
            String result = e.getMessage();
            return result;
        }
    }
    public static String[][] listDataPendapat(String statusMonth)
    {
        if(statusMonth == "Y"){
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            String QUERY;
            if(month<10){
                 QUERY = "select MONTHNAME(tanggal_penjualan) as m,MONTH(tanggal_penjualan) as m2, sum(total_harga) as p\n" +
            "from penjualan where MONTH(tanggal_penjualan) ='0"+month+"'  \n" +
            "group by  MONTHNAME(tanggal_penjualan),MONTH(tanggal_penjualan)";
                 System.out.println(QUERY);
            }else{
                 QUERY = "select MONTHNAME(tanggal_penjualan) as m,MONTH(tanggal_penjualan) as m2, sum(total_harga) as p\n" +
                "from penjualan where MONTH(tanggal_penjualan) ='"+month+"'  \n" +
                "group by  MONTHNAME(tanggal_penjualan),MONTH(tanggal_penjualan)";
            }
            System.out.println(month);
            
            String[] listData = new String[] {"m", "p"};
            String[][] returnGlobal = getDataList(QUERY,listData);
            return returnGlobal;
        }else{
            String QUERY = "select MONTHNAME(tanggal_penjualan) as m, sum(total_harga) as p\n" +
            "from penjualan\n" +
            "group by  MONTHNAME(tanggal_penjualan)";
            String[] listData = new String[] {"m", "p"};
            String[][] returnGlobal = getDataList(QUERY,listData);
            return returnGlobal;
        }
        
    }
    public static String[][] listDataPendapatProduct(String statusMonth)
    {
        String QUERY;
        if(statusMonth == "Y"){
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            if(month<10){
                QUERY = "select MONTHNAME(tanggal) as m,MONTH(tanggal) as m2, sum(qty) as p, sum(harga) as p2,`name`\n" +
                "from detail_penjualan where id_transaksi is not null and MONTH(tanggal)= '0"+month+"'\n" +
                "group by  MONTHNAME(tanggal),name,MONTH(tanggal)";
            }else{
                QUERY = "select MONTHNAME(tanggal) as m,MONTH(tanggal) as m2, sum(qty) as p, sum(harga) as p2,`name`\n" +
                "from detail_penjualan where id_transaksi is not null and MONTH(tanggal)= '"+month+"'\n" +
                "group by  MONTHNAME(tanggal),name,MONTH(tanggal)";
            }
            
        }else{
            QUERY = "select MONTHNAME(tanggal) as m, sum(qty) as p, sum(harga) as p2,`name`\n" +
            "from detail_penjualan where id_transaksi is not null\n" +
            "group by  MONTHNAME(tanggal),name";
        }
        
        String[] listData = new String[] {"name","m", "p","p2"};
        String[][] returnGlobal = getDataList(QUERY,listData);
        String[][]   listDataTransaksi = new String[returnGlobal.length][6];
            System.out.println(returnGlobal.length);
            int dataKepo=0;
            for (int i = 0; i < returnGlobal.length; i++) {
                
                dataKepo = Integer.parseInt(returnGlobal[i][2]) * Integer.parseInt(returnGlobal[i][3]);
                listDataTransaksi[i][0] = returnGlobal[i][0];
                listDataTransaksi[i][1] = returnGlobal[i][1];
                listDataTransaksi[i][2] = returnGlobal[i][2];
                listDataTransaksi[i][3] = Integer.toString(dataKepo);
                
                
            }
        return listDataTransaksi;
    }
 
    
}
