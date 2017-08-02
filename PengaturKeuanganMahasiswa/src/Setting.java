/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author micha
 */
public class Setting {
    ViewSetting viewSetting = new ViewSetting();
    ViewHalamanUtama viewUtama;
    public void setting(ViewHalamanUtama viewUtama){
        this.viewUtama = viewUtama;
        Object[] data = Model.selectSetting();
        viewSetting.setVisible(true);
        viewSetting.setIsi((String)data[0], (String)data[1], (String)data[2]);
        
    }
    
    public int submit(String set_password, String password, String perkiraan){
        Model.updateSetting(set_password, password, perkiraan);      
        return 0;
    }
}
