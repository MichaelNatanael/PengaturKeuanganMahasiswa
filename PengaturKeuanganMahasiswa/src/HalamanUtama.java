
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class HalamanUtama {
    public void setVisible(){
        new ViewHalamanUtama().setVisible(true);
    }
    
    public static void refreshTable(javax.swing.JTable jtab_transaksi){
        Model.selectAll(jtab_transaksi);
    }
   public static void btn_tambahPemasukan(ViewHalamanUtama viewUtama){
       TmbPmskn tmbPmskn = new TmbPmskn();
       tmbPmskn.tambah(viewUtama);
   }
   public static void btn_tambahPengeluaran(ViewHalamanUtama viewUtama){
       TmbPnglrn tmbPnglrn = new TmbPnglrn();
       tmbPnglrn.tambah(viewUtama);
   }
   public static void btn_editTransaksi(ViewHalamanUtama viewUtama, String pilih){
       EditData edit = new EditData();
       System.out.println(pilih);
       if(pilih == ""){
           showMessageDialog(null, "Mohon memilih baris transaksi yang akan di edit!");
       }
       else{
           edit.edit(pilih);
       }
   }
   
   public static void btn_hapus(ViewHalamanUtama viewUtama, String pilih){
        
        String id_terpilih = pilih;
        System.out.println(id_terpilih);
        if(id_terpilih != "") { 
            
            int selectedOption = JOptionPane.showConfirmDialog(null, "Apakah anda ingin mendelete data ini ? ", "Hapus data", JOptionPane.YES_NO_OPTION); 
            if (selectedOption == JOptionPane.YES_OPTION) {
                Model.delete(id_terpilih);
                
                showMessageDialog(null, "Data berhasil dihapus !");
                
            } System.out.println("ntut");
        }
        else{
            showMessageDialog(null, "Pilih data yang akan dihapus !");
        }
       
    
   }
   
   public static void btn_setting(ViewHalamanUtama viewUtama){
       Setting setting = new Setting();
       setting.setting(viewUtama);
   }
   
}
