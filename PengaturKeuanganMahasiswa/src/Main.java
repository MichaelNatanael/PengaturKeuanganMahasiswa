/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
/**
 *
 * @author Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file=new File("D:\\College\\Semester 5\\Rekayasa Perangkat Lunak\\project\\PKM\\PengaturKeuanganMahasiswa");
        if(!file.exists()){
            file.mkdirs();
        }
        Model.createNewTable();
        Object[] data = Model.selectSetting();
        String pass = (String)data[0];
        if("NO".equals(pass)){
            HalamanUtama halamanUtama = new HalamanUtama();
            halamanUtama.setVisible();
        }
        else{
            Lock main= new Lock();
            main.Lock();
        }
        
    }
    
    
    
}
