
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Model {
   
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite://D:/College/Semester 5/Rekayasa Perangkat Lunak/project/PKM/PengaturKeuanganMahasiswa/database.db";
        //D:/College/Semester 5/Rekayasa Perangkat Lunak/project/PKM/PengaturKeuanganMahasiswa/
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:/D:/College/Semester 5/Rekayasa Perangkat Lunak/project/PKM/PengaturKeuanganMahasiswa/database.db";
        //nama database sama lokasi blm di set
        
        // SQL statement for creating a new table tipe data blm, belum dimasukan ke contructor
        String sql = "CREATE TABLE IF NOT EXISTS transaksi (\n"
                + "	id_transaksi integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	tanggal text NOT NULL,\n"
                + "	jenis text NOT NULL,\n"
                + "	judul text NOT NULL,\n"
                + "	deskripsi text NOT NULL,\n"
                + "	tipe text NOT NULL,\n"
                + "	nominal integer NOT NULL\n"
                
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createTableSetting();
    }
    private static void createTableSetting(){
        String url = "jdbc:sqlite:/D:/College/Semester 5/Rekayasa Perangkat Lunak/project/PKM/PengaturKeuanganMahasiswa/database.db";
        //nama database sama lokasi blm di set
        
        // SQL statement for creating a new table tipe data blm, belum dimasukan ke contructor
        String sql = "CREATE TABLE IF NOT EXISTS setting (\n"
                + "	id_setting integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	set_password text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	perkiraan text NOT NULL\n"
                
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertSetting();
    }
    
    public static void delete(String id_transaksi) {
         String sql = "DELETE FROM transaksi WHERE tanggal = ?";
          try (Connection conn = Model.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, id_transaksi);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
    }
    
    public static Object[] selectOne(String id){
        String sql = "SELECT judul, deskripsi, tipe, nominal FROM transaksi WHERE tanggal = '"+id+"'";
        Object[] data= {};
        try{
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                //Count the number of column
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                data = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    data[i - 1] = rs.getObject(i);
                }
            }
            // Tutup koneksi
            rs.close();
            stmt.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
       return data;
    }
    
    public static void updateTransaksi(String judul, String deskripsi, String tipe, int nominal, String tgl){
        String sql = "UPDATE transaksi SET judul = ?, deskripsi = ?, tipe = ?, nominal = ? WHERE tanggal = ?";
 
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, judul);
            pstmt.setString(2, deskripsi);
            pstmt.setString(3, tipe);
            pstmt.setInt(4, nominal);
            pstmt.setString(5, tgl);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
    
    public static void selectAll(javax.swing.JTable jtab_transaksi){
        String sql = "SELECT tanggal, jenis, judul, deskripsi, tipe, nominal FROM transaksi";
        try{
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
              
             //To remove previously added rows
            while(jtab_transaksi.getRowCount() > 0) 
            {
                ((DefaultTableModel) jtab_transaksi.getModel()).removeRow(0);
            }

            while (rs.next()) {
                //Count the number of column
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) jtab_transaksi.getModel()).insertRow(rs.getRow()-1,row);
            }
            
            // Tutup koneksi
            rs.close();
            stmt.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
    
    public static void selectSearch(javax.swing.JTable jtab_transaksi, String[] value){
        String sql = "SELECT tanggal, jenis, judul, deskripsi, tipe, nominal FROM transaksi WHERE tanggal LIKE ? AND jenis LIKE ? AND (judul LIKE ? OR deskripsi LIKE ?) AND tipe LIKE ?";
        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, "%"+value[0]+"%");
            pstmt.setString(2, "%"+value[1]+"%");
            pstmt.setString(3, "%"+value[2]+"%");
            pstmt.setString(4, "%"+value[3]+"%");
            pstmt.setString(5, "%"+value[4]+"%");  
            ResultSet rs = pstmt.executeQuery();
            
             //To remove previously added rows
            while(jtab_transaksi.getRowCount() > 0) 
            {
                ((DefaultTableModel) jtab_transaksi.getModel()).removeRow(0);
            }

            while (rs.next()) {
                //Count the number of column
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) jtab_transaksi.getModel()).insertRow(rs.getRow()-1,row);
            }
            
            // Tutup koneksi
            rs.close();
            pstmt.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
    
    public static void insertTransaksi(String jenis, String judul, String deskripsi, String tipe, int nominal){
        String sql = "INSERT INTO transaksi(tanggal, jenis, judul, deskripsi, tipe, nominal)"
                + "VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            pstmt.setString(1, (String)dateFormat.format(date));
            pstmt.setString(2, jenis);
            pstmt.setString(3, judul);
            pstmt.setString(4, deskripsi);
            pstmt.setString(5, tipe);
            pstmt.setInt(6, nominal);
            
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertSetting(){
        String sql = "INSERT OR IGNORE INTO setting (id_setting,set_password,password,perkiraan)"
                + "VALUES (?,?,?,?);";
        
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setString(2, "NO");
            pstmt.setString(3, "");
            pstmt.setString(4, "NO");
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void updateSetting(String set_password, String password, String perkiraan){
        String sql = "UPDATE setting SET set_password = ?, password = ?, perkiraan = ? WHERE id_setting = 1";
 
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, set_password);
            pstmt.setString(2, password);
            pstmt.setString(3, perkiraan);

            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Object[] selectSetting(){
        String sql = "SELECT set_password, password, perkiraan FROM setting WHERE id_setting = 1";
        Object[] data= {};
        try{
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                //Count the number of column
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                data = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    data[i - 1] = rs.getObject(i);
                }
            }
            // Tutup koneksi
            rs.close();
            stmt.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
       return data;
    }
    
}
