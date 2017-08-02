/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */
public class TmbPnglrn {
    ViewPengeluaran viewPengeluaran = new ViewPengeluaran();
    ViewHalamanUtama viewUtama;
    public void tambah(ViewHalamanUtama viewUtama){
        this.viewUtama = viewUtama;
        viewPengeluaran.setVisible(true);
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
                Model.insertTransaksi("Pengeluaran", judul, deskripsi, tipe, Integer.parseInt(nominal));
                
                return 0;
            }
        
    }
}
