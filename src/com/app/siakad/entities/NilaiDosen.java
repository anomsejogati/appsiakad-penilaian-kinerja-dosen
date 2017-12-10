/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.siakad.entities;

import com.app.siakad.database.KoneksiDB;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class NilaiDosen extends KoneksiDB{
    
    public String kd_nilai;
    public String kd_knd;
    public String kd_dosen;
    public int nilai;
    public String nim;
    public String masukan;
    public String time;
    public String kd_katn;
    
    public void select(){
        tblnilai.setColumnIdentifiers(new Object[]{"No", "Kategori Kriteria Penilaian", "Kriteria Penilaian","Nilai"});
        try{
            conn = getConnection();
            query = "select pg_nilaidosen a, pg_kriterianilaidosen b, pg_dosen c, mhs_mahasiswa d, pg_kategorikriterianilai e"
                    + "from pg_nilaidosen, pg_kriterianilaidosen, pg_dosen, mhs_mahasiswa, pg_kategorikriterianilai" + " where a.kd_knd=b.kd_knd, a.kd_dosen=c.kd_dosen and a.nim=d.nim, and a.kd_katn=e.kd_katn fr";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_nilai = res.getString("kd_nilai");
                kd_katn = res.getString("kd_katn");
                kd_knd = res.getString("kriteria_penilaian");
                nilai = res.getInt(nilai);
                
                list.add(new Object[]{"kd_nilai","kd_katn","kd_knd","nilai"});
            }
            stat.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error Method Select() : " + ex);
        }
        
    }
    
    public void insert_update(){
        try{
            conn = getConnection();
            if(isUpdate == false){
                query = " insert into pg_nilaidosen values(?,?,?,?,?,?) ";
            }else{
                query = " update pg_nilaidosen set kd_nilai=?, kd_knd=?, kd_dosen=?, nilai=?, nim=?, kd_katn=? "
                        + " where kd_nilai='"+kd_nilai+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, kd_nilai);
            stat.setString(2, kd_knd);
            stat.setString(3, kd_dosen);
            stat.setInt(4, nilai);
            stat.setString(5, nim);
            stat.setString(6, kd_katn);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    public void delete(){
        try{
            conn=getConnection();
            query="delete * from pg_nilaidosen where kd_nilai='" +kd_nilai+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    //membuat combobox nilai otomatis
    public String[] KeyNilai, Nilai;
    public void listNilai(){
        try{
            conn = getConnection();
            query = "select * from pg_nilaidosen";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Nilai[i] = res.getString(nilai);
                i++;
            }
            res.first();
            KeyNilai = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyNilai[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listNilai : "+ex);
        }
    }
    
    
    
}
