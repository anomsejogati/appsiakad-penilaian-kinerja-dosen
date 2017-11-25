package com.app.siakad.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.app.siakad.database.KoneksiDB;

/**
 *
 * @author Si-Ketjeeh
 */
public class TahunAngkatan extends KoneksiDB{
    
    public int id_ta;
    public int tahun_angkatan;
    
    public void select(){
        tblthangkatan.setColumnIdentifiers(new Object[]{"ID. TA", "Tahun Angkatan"});
        try{
            conn = getConnection();
            query = "select * from tbthangkatan";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                id_ta = res.getInt(1);
                tahun_angkatan = res.getInt(2);
                
                list.add(new Object[]{id_ta, tahun_angkatan});
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
                query = " insert into tbthangkatan values(?,?) ";
            }else{
                query = " update tbthangkatan set id_ta=?, tahun_angkatan=? "
                        + " where id_ta='"+id_ta+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setInt(1, id_ta);
            stat.setInt(2, tahun_angkatan);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    public void delete(String id_ta){
        try{
            conn = getConnection();
            query = "delete from tbthangkatan where id_ta='"+id_ta+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
    public String[] KeyTA, TahunAngkatan;
    public void listJurusan(){
        try{
            conn = getConnection();
            query = "select * from tbthangkatan";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                TahunAngkatan[i] = res.getString(2);
                i++;
            }
            res.first();
            KeyTA = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyTA[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listJurusan : "+ex);
        }
    }
    
}
