/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package global;
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
public class GlobalMysql {
    static final String BD_URLGlobal  =   "jdbc:mysql://localhost/pos_netbeans";
    static final String USER    =   "root";
    static final String PASS    =   "";
    
    public static void main(String[] args) {
        String[] cars = new String[] {"id", "name"};
        String[][] data = getDataList("SELECT id, name FROM kategori",cars);
        System.out.println(Arrays.toString(data));
    }
    public static String[][] getDataList(String query, String[] dataList){
        int i =0;
        try {
            Connection conn =   DriverManager.getConnection(BD_URLGlobal,USER,PASS);
            Statement stmt  =   conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs    =   stmt.executeQuery(query);
            rs.last();
            int numberOfRows    =   rs.getRow();
            rs.beforeFirst();
            ResultSetMetaData rsmd  =   (ResultSetMetaData) rs.getMetaData();
            int columns     =   rsmd.getColumnCount();
            String[][] resultData     =   new String[numberOfRows][columns];
            while (rs.next()) {
                for (int j = 0; j < dataList.length; j++) {
                    resultData[i][j] = rs.getString(dataList[j]);
                }
                
                i++;
            }
            return resultData;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    public static String mysqlCUD (String query, String message) {
        try {
            String sql = query;
            java.sql.Connection conn = DriverManager.getConnection(BD_URLGlobal, USER, PASS);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           String result = message;
            return result;
        } catch (Exception e) {
            System.out.println("devpos.models.Kategori.create():"+e.getMessage());
            String result = e.getMessage();
            return result;
        }
    }
    public static String byteArrToString(byte[] b) {
         
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int j = b[i] & 0xff;
            if (j < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(j));
        }
        String res = sb.toString();
        return res.toUpperCase();
    }
}
