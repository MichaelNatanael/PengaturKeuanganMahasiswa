
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
public class Search {
        static ViewSearch viewSearch;
        static String[] searchVal;
    public Search(String value){
        viewSearch=new ViewSearch();
        viewSearch.setVisible(true);
        searchVal=new String[5];
        searchVal[0]="";
        searchVal[1]="";
        searchVal[2]=value;
        searchVal[3]=value;
        searchVal[4]="";
                
    }
    
    public Search(String value, String tipe, String jenis){
        viewSearch=new ViewSearch();
        viewSearch.setVisible(true);
        searchVal=new String[5];
        searchVal[0]="";
        if(tipe.equals("Semua")){
            searchVal[1]="";
        }
        else{
            searchVal[1]=tipe;
            System.out.println(searchVal[1]);
        }
        searchVal[2]=value;
        searchVal[3]=value;
        if(jenis.equals("Semua")){
            searchVal[4]="";
        }
        else{
            searchVal[4]=jenis;
        }        
    }
    public static void btn_editTransaksi(String pilih){
       EditData edit = new EditData();
       System.out.println(pilih);
       if(pilih == ""){
           showMessageDialog(null, "Mohon memilih baris transaksi yang akan di edit!");
       }
       else{
           edit.edit(pilih);
       }
    }
    
    public static void btn_hapus(String pilih){
        
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
   
    public static void reselectSearch(String value){
        searchVal[0]="";
        searchVal[1]="";
        searchVal[2]=value;
        searchVal[3]=value;
        searchVal[4]="";
        viewSearch.setVisible(false);
        viewSearch.setVisible(true);
    }
    
    public static void reselectSearch(String value, String tipe, String jenis){
        
        searchVal[0]="";
        if(tipe.equals("Semua")){
            searchVal[1]="";
        }
        else{
            searchVal[1]=tipe;
        }
        searchVal[2]=value;
        searchVal[3]=value;
        if(jenis.equals("Semua")){
            searchVal[4]="";
        }
        else{
            searchVal[4]=jenis;
        }
        viewSearch.setVisible(false);
        viewSearch.setVisible(true);
    }
    public static String getJenis(){
        return searchVal[1];
    }
    public static String getTipe(){
        return searchVal[4];
    }
    public static String getValue(){
        return searchVal[2];
    }
    public static void selectSearch(javax.swing.JTable jtab_transaksi){
       Model.selectSearch(jtab_transaksi, searchVal); 
    }
}
