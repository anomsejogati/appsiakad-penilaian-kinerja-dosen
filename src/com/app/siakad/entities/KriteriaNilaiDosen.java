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

/**
 *
 * @author hp
 */
public class KriteriaNilaiDosen extends KoneksiDB{
    
    public String id_th;
    public String kd_prodi;
    public String kategori_penilaian;
    public String kd_knd;
    public String kd_katn;
    public String kriteria_penilaian;
    
    public void select(){
        tblknd.setColumnIdentifiers(new Object[]{"Tahun Ajaran", "Prodi", "Kategori Penilaian", "Kode Kriteria", "Kode Kategori", "kriteria Penilaian"});
        try{
            conn = getConnection();
            query = "select * from pg_kriterianilaidosen a, pg_kategorikriterianilai b where a.kd_katn=b.kd_katn, a.kd_prodi=b.kd_prodi, "
                    + "a.kategori_penilaian=b.kategori_penilaian and a.id_th=b.id_th";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_knd = res.getString(kd_knd);
                kd_katn = res.getString(kd_katn);
                kriteria_penilaian = res.getString(kriteria_penilaian);
                id_th = res.getString(id_th);
                kd_prodi = res.getString(kd_prodi);
                kategori_penilaian = res.getString(kategori_penilaian);
                
                list.add(new Object[]{kd_knd, kd_katn, kriteria_penilaian});
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
                query = " insert into pg_kriterianilaidosen values(?,?,?,?,?,?) ";
            }else{
                query = " update pg_kriterianilaidosen set kd_knd=?, kd_katn=?, kriteria_penilaian=?, kd_prodi=?, kategori_penilaian=?,id_th=? "
                        + " where kd_knd='"+kd_knd+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, kd_knd);
            stat.setString(2, kd_katn);
            stat.setString(3, kriteria_penilaian);
            stat.setString(4, kd_prodi);
            stat.setString(5, id_th);
            stat.setString(6, kategori_penilaian);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
     
    public void delete(String kd_prodi){
        try{
            conn = getConnection();
            query = "delete from pg_kriterianilaidosen where kd_knd='"+kd_knd+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
    public String[] KeyKriteria, Kriteria;
    public void listKriteria(){
        try{
            conn = getConnection();
            query = "select * from pg_kriterianilaidosen";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Kriteria[i] = res.getString(2);
                i++;
            }
            res.first();
            KeyKriteria = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyKriteria[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listKriteria : "+ex);
        }
    }
    
    
}
