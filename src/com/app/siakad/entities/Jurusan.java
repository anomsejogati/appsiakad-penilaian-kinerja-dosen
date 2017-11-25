package com.app.siakad.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.app.siakad.database.KoneksiDB;

/**
 *
 * @author Si-Ketjeeh
 */
public class Jurusan extends KoneksiDB{
    
    public String kd_jur;
    public String jurusan;
    
    public void select(){
        tbljurusan.setColumnIdentifiers(new Object[]{"Kode", "Nama Jurusan"});
        try{
            conn = getConnection();
            query = "select * from tbjurusan";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_jur = res.getString(1);
                jurusan = res.getString(2);
                
                list.add(new Object[]{kd_jur, jurusan});
            }
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method select() : " + ex);
        }
    }
    
    public void insert_update(){
        try{
            conn = getConnection();
            if(isUpdate == false){
                query = " insert into tbjurusan values(?,?) ";
            }else{
                query = " update tbjurusan set kd_jur=?, jurusan=? "
                        + " where kd_jur='"+kd_jur+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, kd_jur);
            stat.setString(2, jurusan);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    public void delete(String kd_jur){
        try{
            conn = getConnection();
            query = "delete from tbjurusan where kd_jur='"+kd_jur+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
    public String[] KeyJur, Jurusan;
    public void listJurusan(){
        try{
            conn = getConnection();
            query = "select * from tbjurusan";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Jurusan[i] = res.getString(2);
                i++;
            }
            res.first();
            KeyJur = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyJur[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listJurusan : "+ex);
        }
    }
    
}
