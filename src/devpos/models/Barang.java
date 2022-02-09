/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package devpos.models;

//library connection mysql
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import global.GlobalMysql;
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
public class Barang extends GlobalMysql{
    //connection
//    
//    public static void main(String[] args){
//        String[][] showData = listData();
//        String createData   = create("testing","5000","1");
//        String editData     = edit("3","dimsum crabstik","3000","1");
//        String deleteData   = delete("5");
//    }
    // show data in database 
    public static String[][] listData(){
        String QUERY = "SELECT barang.id,barang.name,barang.harga,\n"+
                "kategori.id as kodeKategori, kategori.name as kategoriName from barang\n"+
                "inner join kategori on barang.kategori_id = kategori.id";
        String[] listData = new String[] {"id", "name","harga","kodeKategori","kategoriName"};
        String[][] returnGlobal = getDataList(QUERY,listData);
        return returnGlobal;
    }
    
    // create data in dataabse 
    public static String create(String name,String harga,String kategori_id ){
        String sql = "insert into barang (name,harga,kategori_id) "
                    + "value ('"+name+"','"+harga+"','"+kategori_id+"')";
        String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
        return result;
    }
    public static String edit(String id,String name, String harga, String kategori_id){
        String sql = "update barang set name='"+name+"', harga='"+harga+"'"
                + ",kategori_id='"+kategori_id+"' where id='"+id+"'";
        String result = mysqlCUD(sql," Data Berhasil Di Ubah");
        return result;
    }
    
    public static String delete(String id){
        String sql = "delete from barang where id='"+id+"'";
        String result = mysqlCUD(sql," Data Berhasil Di Hapus");
        return result;
    }
    
}
