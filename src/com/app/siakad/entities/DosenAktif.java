package com.app.siakad.entities;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.app.siakad.database.KoneksiDB;
import java.util.ArrayList;

public class DosenAktif extends KoneksiDB{
    
    public String kd_dosen;
    public String nama_dosen;
    public String nama_mk;
    public int sks_t;
    public int sks_p;
    public String sta_aktif;
    
    public void select(){
        tbljadwalmk.setColumnIdentifiers(new Object[]{"Kode Dosen","Nama Dosen","Nama Matakuliah","SKS Teori","SKS Praktik","Status"});
        try{
            conn = getConnection();
            query = "select kd_dosen, nama_pegawai, sta_aktif from pg_dosen"+"select nama_mk, sks_t, sks_p from mk_matakuliah";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                kd_dosen=res.getString("kd_dosen");
                nama_dosen=res.getString("nama_pegawai");
                nama_mk=res.getString("nama_mk");
                sks_t=res.getInt(sks_t);
                sks_p=res.getInt(sks_p);
                sta_aktif=res.getString("sta_aktif");
                
                list.add(new Object[]{kd_dosen,nama_dosen,nama_mk,sks_t,sks_p,sta_aktif});
                
                stat.close();
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method select() : " + ex);
        }
    }
    
}
