package com.app.siakad.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Si-Ketjeeh
 */
public class Prodi extends Jurusan{
    
    public String kd_prodi;
    public String prodi;
    
    @Override
    public void select(){
        tblprodi.setColumnIdentifiers(new Object[]{"Kode", "Jurusan","Program Studi"});
        try{
            conn = getConnection();
            query = "select * from tbprodi a, tbjurusan b where a.kd_jur=b.kd_jur";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_prodi = res.getString("kd_prodi");
                jurusan = res.getString("jurusan");
                prodi = res.getString("program studi");
                
                list.add(new Object[]{kd_prodi, jurusan, prodi});
            }
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method select() : " + ex);
        }
    }
    
    @Override
    public void insert_update(){
        try{
            conn = getConnection();
            if(isUpdate == false){
                query = " insert into tbprodi values(?,?,?) ";
            }else{
                query = " update tbprodi set kd_prodi=?, kd_jur=?, prodi=? "
                        + " where kd_prodi='"+kd_prodi+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, kd_prodi);
            stat.setString(2, kd_jur);
            stat.setString(3, prodi);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    @Override
    public void delete(String kd_prodi){
        try{
            conn = getConnection();
            query = "delete from tbprodi where kd_prodi='"+kd_prodi+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
    public String[] KeyProdi, Prodi;
    public void listProdi(){
        try{
            conn = getConnection();
            query = "select * from tbprodi";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Prodi[i] = res.getString("prodi");
                i++;
            }
            res.first();
            KeyProdi = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyProdi[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listProdi : "+ex);
        }
    }
    
}
