/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author oratakashi
 */
public class KoneksiDB {
    public Connection getConnection() throws SQLException{
        Connection cnn;
        try{
            String server = "jdbc:mysql://localhost/dbsiakad_171530001";
            String drever = "com.mysql.jdbc.Driver";
            Class.forName(drever);
            cnn = DriverManager.getConnection(server, "root", "");
            return cnn;
        }catch(SQLException | ClassNotFoundException se){
            System.out.println(se);
            JOptionPane.showMessageDialog(null, "Gagal Terhubung ke database, cek koneksi database anda!!");
            return null;
        }
    }
}
