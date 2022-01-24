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
        String[] cars = new String[] {"Volvo", "BMW", "Ford", "Mazda"};
        String getDataList("testing",cars);
    }
    public String getDataList(String query, String[] dataList){
        int i =0;
        System.out.println(Arrays.toString(dataList));
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
//            while (rs.netx()) {
//                resultData[i][0].
//                i++;
//            }
            
                    
        } catch (Exception e) {
            
        }
        return resultData;
        
    }
}
