package com.app.siakad.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.app.siakad.database.KoneksiDB;

/**
 *
 * @author Si-Ketjeeh
 */
public class User extends KoneksiDB{
    
    public String id_user;
    public String nama_user;
    public String pass;
    public String lev_user;
    
    public void select(){
        tbluser.setColumnIdentifiers(new Object[]{"ID. User", "Nama User", "Lev. User"});
        try{
            conn = getConnection();
            query = "select * from tbuser";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                id_user = res.getString("id_user");
                nama_user = res.getString("nama_user");
                lev_user = res.getString("lev_user");
                
                list.add(new Object[]{id_user, nama_user, lev_user});
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
                query = " insert into tbuser values(?,?,?,?) ";
            }else{
                query = " update tbuser set id_user=?, nama_user=?, pass=?, lev_user=? "
                        + " where id_user='"+id_user+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, id_user);
            stat.setString(2, nama_user);
            stat.setString(3, pass);
            stat.setString(4, lev_user);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    public void delete(String id_user){
        try{
            conn = getConnection();
            query = "delete from tbuser where id_user='"+id_user+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
}
