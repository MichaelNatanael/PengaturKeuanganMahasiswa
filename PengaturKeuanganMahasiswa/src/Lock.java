/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */
public class Lock {
    ViewLock viewLock = new ViewLock();
    ViewHalamanUtama viewUtama;
    HalamanUtama halamanUtama = new HalamanUtama();
    
    public void Lock(){
        this.viewUtama = viewUtama;
        Object[] data = Model.selectSetting();
        viewLock.setVisible(true);
        viewLock.setIsi((String)data[0], (String)data[1], (String)data[2]);
        
    }
    
    public void submit(){
        halamanUtama.setVisible();
    }
}
