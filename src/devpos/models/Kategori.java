/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devpos.models;

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
public class Kategori extends GlobalMysql{
    
    public static String[][] listData()
    {
        String QUERY = "SELECT id, name FROM kategori";
        String[] listData = new String[] {"id", "name"};
        String[][] returnGlobal = getDataList(QUERY,listData);
        return returnGlobal;
    }
    public static String create (String data) {
        String sql = "INSERT INTO kategori (name) VALUES ('"+data+"');";
        String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
        return result;
    }
    public static String edit (String data,String id) {
        String sql = "UPDATE kategori SET name = '"+data+"' WHERE id = '"+id+"';";
        String result = mysqlCUD(sql," Data Berhasil Di Ubah");
        return result;
    }
    public static String delete (String id) {
        String sql = "DELETE FROM kategori WHERE id='"+id+"';";
        String result = mysqlCUD(sql," Data Berhasil Di Hapus");
        return result;
    }
 
    
}
