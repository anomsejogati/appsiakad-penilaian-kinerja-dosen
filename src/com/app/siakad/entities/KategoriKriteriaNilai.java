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
 * @author dita
 */
public class KategoriKriteriaNilai extends KoneksiDB {
    public String kd_katn;
    public String id_th;
    public String kd_prodi;
    public String kategori_penilaian;
    public Float bobot;
   
    public void select(){
        tblkatn.setColumnIdentifiers(new Object[]{"Kode Kategori Nilai", "ID TA", "Program Studi", "Kategori Penilaian", "Bobot"});
        try{
            conn = getConnection();
            query = "select * from pg_kategorikriterianilai";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_katn = res.getString(1);
                id_th = res.getString(2);
                kd_prodi = res.getString(3);
                kategori_penilaian = res.getString(4);
                bobot = res.getFloat(4);
                
                 list.add(new Object[]{kd_katn, id_th, kd_prodi, kategori_penilaian, bobot});
            }
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method select(): "+ex);
        }
    }
    
    public void insert_update(){
        try{
            conn = getConnection();
            if(isUpdate == false){
                query = " insert into pg_kategorikriterianilai values(?,?,?,?,?) ";
            }else{
                query = " update pg_kategorikriterianilai set kd_katn=?, id_th=?, kd_prodi=?, kategori_penilaian=?, bobot=? "
                        + " where kd_katn='"+kd_katn+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, kd_katn);
            stat.setString(2, id_th);
            stat.setString(3, kd_prodi);
            stat.setString(4, kategori_penilaian);
            stat.setFloat(5, bobot);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error mothod insert_update(): "+ex);
        }
    }
    
    public void delete(){
        try{
           conn = getConnection();
           query = "delete pg_kategorikriterianilai where kd_katn='"+kd_katn+"' ";
           stat = conn.prepareStatement(query);
           stat.executeUpdate();
           stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete(): "+ex);
        }
    }
    
    public String[] KeyKategoriNilai, Kategori;
    public void listKategoriNilai(){
        try{
            conn = getConnection();
            query = "select * from pg_Kategorikriterianilai";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Kategori[i] = res.getString(2);
                i++;
            }
            res.first();
            KeyKategoriNilai = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyKategoriNilai[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listKategoriNilai() : "+ex);
        }
    }
    
    
}
