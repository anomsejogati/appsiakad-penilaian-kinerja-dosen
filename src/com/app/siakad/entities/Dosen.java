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
 * @author Tujuh
 */
public class Dosen extends KoneksiDB{
    public String nik;
    public String kd_dosen;
    public String nip;
    public String nama_pegawai;
    public String jk;
    public String tmp_lahir;
    public String tgl_lahir;
    public String agama;
    public String alamat;
    public int no_telepon1;
    public int no_telepon2;
    public String email;
    public String tmt;
    public String nama_ibu;
    public String sta_menikah;
    public int jml_tanggungan;
    public String sta_pegawai;
    public String sta_aktif;

public void select(){
    tbldosen.setColumnIdentifiers(new Object[]{"Nip", "Kode Dosen", "Nik", "Nama Pegawai","Jenis Kelamin","Tempat Lahir",
            "Tanggal Lahir","Agama","Alamat","Nomor Telepon 1", "Nomor Telepon 2", "Email", "Tempat","Nama Ibu","Status Menikah",
            "Jumlah Tanggungan","Status Pegawai","Status Aktif"});
    try {
        conn = getConnection();
        query = "select * from pg_dosen";
        stat = conn.prepareStatement(query);
        res = stat.executeQuery(query);
        list = new ArrayList<>();
        while (res.next()) {
            nip = res.getString("nip");
            kd_dosen = res.getString("kd_dosen");
            nik = res.getString("nik");
            nama_pegawai = res.getString("nama_pegawai");
            jk = res.getString("jk");
            tmp_lahir =res.getString("tmp_lahir");
            tgl_lahir=res.getString("tgl_lahir");
            agama=res.getString("agama");
            alamat=res.getString("alamat");
            no_telepon1=res.getInt(no_telepon1);
            no_telepon2=res.getInt(no_telepon2);
            email=res.getString("email");
            tmt=res.getString("tmt");
            nama_ibu=res.getString("nama_ibu");
            sta_menikah=res.getString("sta_menikah");
            jml_tanggungan=res.getInt(jml_tanggungan);
            sta_pegawai=res.getString("sta_pegawai");
            sta_aktif=res.getString("sta_aktif");
            
            list.add(new Object[]{"Nip", "Kode Dosen", "Nik", "Nama Pegawai","Jenis Kelamin","Tempat Lahir",
            "Tanggal Lahir","Agama","Alamat","Nomor Telepon 1", "Nomor Telepon 2", "Email", "Tempat","Nama Ibu","Status Menikah",
            "Jumlah Tanggungan","Status Pegawai","Status Aktif"});
        }
        stat.close();
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error Method Select() :"+ex);
    }
}

public void insert_update(){
    try {
        conn = getConnection();
        if(isUpdate==false){
            query =" insert into pd_dosen values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        }else{
            query = " update pg_dosen set nip=?, kd_dosen=?,nik=?, nama_pegawai=?,jk=?, tmp_lahir=?,tgl_lahir=?,agama=?,alamat=?,no_telepon1=?,no_telepon2=?,email=?,tmt=?,nama_ibu=?,sta_menikah=?,jml_tanggungan=?,sta_pegawai=?,sta_aktif=?"
                    + " where nik='"+nik+"' ";
            }
        stat = conn.prepareStatement(query);
            stat.setString(1, nip);
            stat.setString(2, kd_dosen);
            stat.setString(3, nik);
            stat.setString(4, nama_pegawai);
            stat.setString(5, jk);
            stat.setString(6, tmp_lahir);
            stat.setString(7, tgl_lahir);
            stat.setString(8, agama);
            stat.setString(9, alamat);
            stat.setInt(10, no_telepon1);
            stat.setInt(11, no_telepon2);
            stat.setString(12,email);
            stat.setString(13,tmt);
            stat.setString(14,nama_ibu);
            stat.setString(15,sta_menikah);
            stat.setInt(16,jml_tanggungan);
            stat.setString(17,sta_pegawai);
            stat.setString(18, sta_aktif);
            stat.executeUpdate();
            stat.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
    }
}
public void delete(String nik){
        try{
            conn = getConnection();
            query = "delete from pg_dosen where nik='"+nik+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method delete() : " + ex);
        }
    }
    
    public String[] KeyDosen, Dosen;
    public void listDosen(){
        try{
            conn = getConnection();
            query = "select * from pg_dosen";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            int i = 1;
            while(res.next()){
                Dosen[i] = res.getString(2);
                i++;
            }
            res.first();
            KeyDosen = new String[i+1];
            for(int x=1 ; x<i ; x++){
                KeyDosen[x] = res.getString(1);
                res.next();
            }
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error Method listDosen : "+ex);
        }
    }
}
