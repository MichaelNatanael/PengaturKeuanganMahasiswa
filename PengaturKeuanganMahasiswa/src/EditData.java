/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */
public class EditData {
    ViewEditTransaksi viewEdit = new ViewEditTransaksi();
    
    static String pilih;
    public int edit(String pilih){
        this.pilih = pilih;
        System.out.println(this.pilih);
        
        Object[] data = Model.selectOne(pilih);
        viewEdit.setVisible(true);
        viewEdit.setIsi((String)data[0], (String)data[1], (String)data[2], Integer.toString((int)data[3]));
        return 0;
    }
    
    public int submit(String judul, String deskripsi, String tipe, String nominal){
            if("".equals(judul) || "".equals(deskripsi) || "".equals(nominal)){
                return 1;
            }
            else{
                int nominalInt;
                try{
                    nominalInt=Integer.parseInt(nominal);
                } catch(Exception e){
                    return 2;
                }
                System.out.println(pilih);
                Model.updateTransaksi(judul, deskripsi, tipe, Integer.parseInt(nominal), pilih);
                
                return 0;
            }
        
    }
}
