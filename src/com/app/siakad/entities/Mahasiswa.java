package com.app.siakad.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.app.siakad.database.KoneksiDB;

/**
 *
 * @author Si-Ketjeeh
 */
public class Mahasiswa extends KoneksiDB{
    
    public String nim;
    public int id_ta;
    public String kd_prodi;
    public String nama_mhs;
    public String jk;
    public String tmp_lahir;
    public String tgl_lahir;
    public String agama;
    public String nama_ayah;
    public String nama_ibu;
    public String alamat;
    public String no_teleponhp;
    
    public void insert_update(){
        try{
            conn = getConnection();
            if(isUpdate == false){
                query = " insert into tbmahasiswa values(?,?,?,?,?,?,?,?,?,?,?,?) ";
            }else{
                query = " update tbmahasiswa set nim=?, id_ta=?, "
                        + " kd_prodi=?, nama_mhs=?, jk=?, "
                        + " tmp_lahir=?, tgl_lahir=?, agama=?, "
                        + " nama_ayah=?, nama_ibu=?, "
                        + " alamat=?, no_telepon=? "
                        + " where nim='"+id_ta+"' ";
            }
            stat = conn.prepareStatement(query);
            stat.setString(1, nim);
            stat.setInt(2, id_ta);
            stat.setString(3, kd_prodi);
            stat.setString(4, nama_mhs);
            stat.setString(5, jk);
            stat.setString(6, tmp_lahir);
            stat.setString(7, tgl_lahir);
            stat.setString(8, agama);
            stat.setString(9, nama_ayah);
            stat.setString(10, nama_ibu);
            stat.setString(11, alamat);
            stat.setString(12, no_teleponhp);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method insert_update() : " + ex);
        }
    }
    
    public void delete(String nim){
        try{
            conn = getConnection();
            query = "delete from tbmahasiswa where nim='"+id_ta+"' ";
            stat = conn.prepareStatement(query);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void select(){
        tblmahasiswa.setColumnIdentifiers(new Object[]{"NIM", "Nama Mahasiswa", 
            "L/P", "Tempat", "Tgl. Lahir", "Program Studi", "Alamat", "No. Telepon"});
        try{
            conn = getConnection();
            query = " select * from tbmahasiswa a, tbthangkatan b, tbprodi c "
                    + " where a.id_ta=b.id_ta and a.kd_prodi=c.kd_prodi order by a.kd_prodi";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            list = new ArrayList<>();
            while(res.next()){
                nim = res.getString("nim");
                nama_mhs = res.getString("nama_mhs");
                jk = res.getString("jk");
                tmp_lahir = res.getString("tmp_lahir");
                tgl_lahir = tglview.format(res.getDate("tgl_lahir"));
                kd_prodi = res.getString("prodi");
                alamat = res.getString("alamat");
                no_teleponhp = res.getString("no_teleponhp");
                
                list.add(new Object[]{nim, nama_mhs, jk, tmp_lahir, 
                    tgl_lahir, kd_prodi, alamat, no_teleponhp});
            }
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method select() : " + ex);
        }
    }
    
    // Membuat NIM otomatis
    public void createNIM(){
        try{
            conn = getConnection();
            query = "select max(right(nim,4)) from tbmahasiswa where Left(nim, 5)='"+nim+"' ";
            stat = conn.prepareStatement(query);
            res = stat.executeQuery(query);
            if(res.first()){
                int noID = res.getInt(1) + 1;
                String no = String.valueOf(noID);
                int noLong = no.length();
                for(int a=0;a<2-noLong;a++){
                    no = "" + no;
                }
                if(noID < 1){
                    nim = nim+"0000" + no;
                }else if(noID < 10){
                    nim = nim+"000" + no;
                }else if(noID < 100){
                    nim = nim+"00" + no;
                }else if(noID < 1000){
                    nim = nim+"0" + no;
                } else{
                    nim = nim+""+ no;
                }
            }else{
                nim = nim+"0001";
            }
            stat.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error method createNIM() : " 
                    + ex, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
