/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devpos.models;

import global.GlobalMysql;

/**
 *
 * @author Gunawan Bayu
 */
public class User extends GlobalMysql{
    private static String[][] DataUser;
    public static String[][] login(String username,String password)
    {
        String QUERY = "SELECT * FROM user where username='"+username+"' and password = '"+password+"'";
        String[] listData = new String[] {"id", "name","role"};
        String[][] returnGlobal = getDataList(QUERY,listData);
        DataUser = returnGlobal;
        return returnGlobal;
    }
    public static String[][] Session(){
        return DataUser;
    }
        public static String[][] listData()
    {
        String QUERY = "SELECT * FROM user";
        String[] listData = new String[] {"id", "name","username","role"};
        String[][] returnGlobal = getDataList(QUERY,listData);
        return returnGlobal;
    }
    public static String create (String username,String password,String name) {
        String sql = "INSERT INTO user (username,password,role,name) VALUES ('"+username+"','"+password+"',2,'"+name+"');";
        String result = mysqlCUD(sql,"Penyimpanan Data Berhasil");
        return result;
    }
    public static String edit (String username,String password,String name,String id) {
        String sql = "UPDATE user SET username = '"+username+"',password = '"+password+"',name = '"+name+"' WHERE id = '"+id+"';";
        String result = mysqlCUD(sql," Data Berhasil Di Ubah");
        return result;
    }
    public static String delete (String id) {
        String sql = "DELETE FROM user WHERE id='"+id+"';";
        String result = mysqlCUD(sql," Data Berhasil Di Hapus");
        return result;
    }
}
