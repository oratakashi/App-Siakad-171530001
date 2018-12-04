/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.HierarchyEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;
import Tool.KoneksiDB;
import com.jtattoo.plaf.DecorationHelper;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author nixia
 */
public class FrMenu extends javax.swing.JFrame {
    KoneksiDB getCnn = new KoneksiDB();
    Connection _Cnn;
    String vid_user, vnama_user, vpass, vlev_user;
    String sqlselect;
    
    
    public FrMenu() {
        initComponents();
        
        form_utama(false);
        hidden_all_menu(false);
        pNotif.setVisible(false);
        this.setExtendedState(this.getExtendedState()| FrMenubak.MAXIMIZED_BOTH);
        Image image = new ImageIcon(getClass().getResource("/Image/Admin-Schoolar.png")).getImage();
        setIconImage(image);
    }
    
    public void showNotif(String Judul,String Keterangan,String Detail){
        lbJudulNotif.setText(Judul);
        lbKetNotif.setText(Keterangan);
        lbDetailNotif.setText(Detail);
        this.pNotif.setVisible(true);
        
    }
    
    public void form_utama(boolean xyz){
        pMenu.setVisible(xyz);
        pNav.setVisible(xyz);
        lbKeterangan.setVisible(xyz);
        lbCopyright.setVisible(xyz);
    }
    
    public void hidden_all_menu(boolean xyz){
        mnDSistem.setVisible(xyz);
        mnDMaster.setVisible(xyz);
        mnDMahasiswa.setVisible(xyz);
        mnDAkademik.setVisible(xyz);
        mnDPegawai.setVisible(xyz);
        mnDUangKuliah.setVisible(xyz);
    }
    
    void set_color2(JPanel panel){
        panel.setBackground(new java.awt.Color(80,80,80));
    }
    void set_color1(JPanel panel){
        panel.setBackground(new java.awt.Color(255,51,0));
    }
    
    private void aksiLogout(){
        hidden_all_menu(false);
        form_utama(false);
        pLogin.setVisible(true);
        lbPengguna.setText("Sistem Akademik Politeknik Negeri Bandung v.1.0");
        
        
        
    }
    //=========================================================================//
    private void aksiLogin(){
        vid_user = txtIdUser.getText().replaceAll("'", "");
        vpass = txtPass.getText().replaceAll("'", "");
        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection(); //Mengisi Var _Cnn dengan object getCnn method getConnection
            sqlselect = "select * from tbuser where id_user = '"+vid_user+"' "
                    + " and pass='"+vpass+"' ";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect);
            if(res.first()){
                vlev_user = res.getString("lev_user");
                vnama_user = res.getString("nama_user");
                lbKeterangan.setText("ID. User : "+vid_user+" - "+vnama_user+" | Lev.User : "+vlev_user);
                if(vlev_user.equals("Administrator")){
                    userAdmin();
                }else if(vlev_user.equals("Staf Akademik")){
                    userAkademik();
                }else if(vlev_user.equals("Staf Kepegawaian")){
                    userKepegawaian();
                }else if(vlev_user.equals("Staf Kemahasiswaan")){
                    userKemahasiswaan();
                }else if(vlev_user.equals("Staf Bag. Pembayaran UKT")){
                    userPembayaranUKT();
                }
                form_utama(true);
                pLogin.setVisible(false);
                lbPengguna.setText("Selamat Datang : "+res.getString(2).toString());
            }else{
                JOptionPane.showMessageDialog(this, "ID. User dan Password salah",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error Method aksiLogin() : "+ex);
        }
    }
    
    void menuSistem(boolean sta){
        mnSistem.setVisible(sta);
    }
    
    void menuMaster(boolean sta){
        mnMaster.setVisible(sta);
    }
    
    void menuAkademik(boolean sta){
        mnAkademik.setVisible(sta);
    }
    
    void menuMahasiswa(boolean sta){
        mnMahasiswa.setVisible(sta);
    }
    
    void menuPegawai(boolean sta){
        mnPegawai.setVisible(sta);
    }
    
    void menuUKT(boolean sta){
        mnUangKuliah.setVisible(sta);
    }
    
    void userAdmin(){
        menuSistem(true);
        lbUser.setText("User");
        menuMahasiswa(true);
        menuMaster(true);
        menuPegawai(true);
        menuUKT(true);
        menuAkademik(true);
    }
    
    void userAkademik(){
        menuSistem(true);
        lbUser.setText("Ganti Password");
        menuMahasiswa(false);
        menuMaster(true);
        menuPegawai(false);
        menuUKT(false);
        menuAkademik(true);
    }
    
    void userKemahasiswaan(){
        menuSistem(true);
        lbUser.setText("Ganti Password");
        menuMahasiswa(true);
        menuMaster(false);
        menuPegawai(false);
        menuUKT(false);
        menuAkademik(false);
    }
    
    void userKepegawaian(){
        menuSistem(true);
        lbUser.setText("Ganti Password");
        menuMahasiswa(false);
        menuMaster(false);
        menuPegawai(true);
        menuUKT(false);
        menuAkademik(false);
    }
    
    void userPembayaranUKT(){
        menuSistem(true);
        lbUser.setText("Ganti Password");
        menuMahasiswa(false);
        menuMaster(false);
        menuPegawai(false);
        menuUKT(false);
        menuAkademik(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mnSistem = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        mnDSistem = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        mnUser = new javax.swing.JPanel();
        lbUser = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        mnKeluar = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        mnMaster = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        mnDMaster = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        mnTA = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        mnJurusan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        mnProdi = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        mnJabatanPegawai = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        mnMahasiswa = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        mnDMahasiswa = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        mnDataMahasiswa = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        mnDataMahasiswaCuti = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        mnDataMahasiswaDO = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        mnDataMahasiswaMD = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        mnDataAlumni = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        mnLapMahasiswa = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        mnLapMahasiswa1 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        mnAkademik = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        mnDAkademik = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        mnSetThAkademik = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        mnDataKelompokMK = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        mnMasterMK = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        mnMKSemesterAktif = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        mnJadwalMK = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        mnPegawai = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        mnDPegawai = new javax.swing.JPanel();
        jSeparator14 = new javax.swing.JSeparator();
        mnDataPegawai = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        mnPegawaiMutasi = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        mnPegawaiKeluar = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        mnTransJabatan = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        mnTransJabatan1 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        mnUangKuliah = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        mnDUangKuliah = new javax.swing.JPanel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        mnMasterUKT = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        mnDataUKTMahasiswa = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        mnTransUKT = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        mnLapTransUKT = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        mnLogout = new javax.swing.JPanel();
        lbLogout = new javax.swing.JLabel();
        pLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        pNav = new javax.swing.JPanel();
        lbMenu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbClose = new javax.swing.JLabel();
        lbPengguna = new javax.swing.JLabel();
        lbAbout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbKeterangan = new javax.swing.JLabel();
        lbCopyright = new javax.swing.JLabel();
        jpMenu = new javax.swing.JDesktopPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        pNotif = new javax.swing.JPanel();
        lbJudulNotif = new javax.swing.JLabel();
        lbKetNotif = new javax.swing.JLabel();
        lbDetailNotif = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Siakad v.1.0.1");
        setUndecorated(true);

        pMenu.setBackground(java.awt.Color.darkGray);
        pMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMenuMouseClicked(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sistem Akademik");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Politeknik Negeri Bandung");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("Navigasi :");

        mnSistem.setBackground(new java.awt.Color(80, 80, 80));
        mnSistem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnSistem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnSistemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnSistemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnSistemMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sistem.png"))); // NOI18N
        jLabel10.setText("Sistem");

        javax.swing.GroupLayout mnSistemLayout = new javax.swing.GroupLayout(mnSistem);
        mnSistem.setLayout(mnSistemLayout);
        mnSistemLayout.setHorizontalGroup(
            mnSistemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnSistemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnSistemLayout.setVerticalGroup(
            mnSistemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnSistemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDSistem.setBackground(new java.awt.Color(80, 80, 80));

        mnUser.setBackground(new java.awt.Color(80, 80, 80));
        mnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnUserMouseExited(evt);
            }
        });

        lbUser.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbUser.setForeground(java.awt.Color.white);
        lbUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        lbUser.setText("User");
        lbUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUserMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mnUserLayout = new javax.swing.GroupLayout(mnUser);
        mnUser.setLayout(mnUserLayout);
        mnUserLayout.setHorizontalGroup(
            mnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnUserLayout.setVerticalGroup(
            mnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        mnKeluar.setBackground(new java.awt.Color(80, 80, 80));
        mnKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnKeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnKeluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnKeluarMouseExited(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel17.setForeground(java.awt.Color.white);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel17.setText("Keluar");

        javax.swing.GroupLayout mnKeluarLayout = new javax.swing.GroupLayout(mnKeluar);
        mnKeluar.setLayout(mnKeluarLayout);
        mnKeluarLayout.setHorizontalGroup(
            mnKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        mnKeluarLayout.setVerticalGroup(
            mnKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnKeluarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mnDSistemLayout = new javax.swing.GroupLayout(mnDSistem);
        mnDSistem.setLayout(mnDSistemLayout);
        mnDSistemLayout.setHorizontalGroup(
            mnDSistemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(mnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator7)
            .addComponent(mnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mnDSistemLayout.setVerticalGroup(
            mnDSistemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDSistemLayout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        mnMaster.setBackground(new java.awt.Color(80, 80, 80));
        mnMaster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnMasterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnMasterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnMasterMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/master.png"))); // NOI18N
        jLabel11.setText("Master");

        javax.swing.GroupLayout mnMasterLayout = new javax.swing.GroupLayout(mnMaster);
        mnMaster.setLayout(mnMasterLayout);
        mnMasterLayout.setHorizontalGroup(
            mnMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        mnMasterLayout.setVerticalGroup(
            mnMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDMaster.setBackground(new java.awt.Color(80, 80, 80));

        mnTA.setBackground(new java.awt.Color(80, 80, 80));
        mnTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnTAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnTAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnTAMouseExited(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel18.setForeground(java.awt.Color.white);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel18.setText("Tahun Angkatan");

        javax.swing.GroupLayout mnTALayout = new javax.swing.GroupLayout(mnTA);
        mnTA.setLayout(mnTALayout);
        mnTALayout.setHorizontalGroup(
            mnTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        mnTALayout.setVerticalGroup(
            mnTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnJurusan.setBackground(new java.awt.Color(80, 80, 80));
        mnJurusan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnJurusanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnJurusanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnJurusanMouseExited(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel19.setForeground(java.awt.Color.white);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel19.setText("Jurusan");

        javax.swing.GroupLayout mnJurusanLayout = new javax.swing.GroupLayout(mnJurusan);
        mnJurusan.setLayout(mnJurusanLayout);
        mnJurusanLayout.setHorizontalGroup(
            mnJurusanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJurusanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnJurusanLayout.setVerticalGroup(
            mnJurusanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJurusanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnProdi.setBackground(new java.awt.Color(80, 80, 80));
        mnProdi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnProdiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnProdiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnProdiMouseExited(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel20.setForeground(java.awt.Color.white);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel20.setText("Program Studi");

        javax.swing.GroupLayout mnProdiLayout = new javax.swing.GroupLayout(mnProdi);
        mnProdi.setLayout(mnProdiLayout);
        mnProdiLayout.setHorizontalGroup(
            mnProdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnProdiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnProdiLayout.setVerticalGroup(
            mnProdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnProdiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnJabatanPegawai.setBackground(new java.awt.Color(80, 80, 80));
        mnJabatanPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnJabatanPegawaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnJabatanPegawaiMouseExited(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel21.setForeground(java.awt.Color.white);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel21.setText("Jabatan Pegawai");

        javax.swing.GroupLayout mnJabatanPegawaiLayout = new javax.swing.GroupLayout(mnJabatanPegawai);
        mnJabatanPegawai.setLayout(mnJabatanPegawaiLayout);
        mnJabatanPegawaiLayout.setHorizontalGroup(
            mnJabatanPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJabatanPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnJabatanPegawaiLayout.setVerticalGroup(
            mnJabatanPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJabatanPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mnDMasterLayout = new javax.swing.GroupLayout(mnDMaster);
        mnDMaster.setLayout(mnDMasterLayout);
        mnDMasterLayout.setHorizontalGroup(
            mnDMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addComponent(jSeparator8)
            .addComponent(mnTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnJurusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnProdi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnJabatanPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mnDMasterLayout.setVerticalGroup(
            mnDMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDMasterLayout.createSequentialGroup()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnJabatanPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnMahasiswa.setBackground(new java.awt.Color(80, 80, 80));
        mnMahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnMahasiswaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnMahasiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnMahasiswaMouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel16.setForeground(java.awt.Color.white);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/mahasiswa.png"))); // NOI18N
        jLabel16.setText("Mahasiswa");

        javax.swing.GroupLayout mnMahasiswaLayout = new javax.swing.GroupLayout(mnMahasiswa);
        mnMahasiswa.setLayout(mnMahasiswaLayout);
        mnMahasiswaLayout.setHorizontalGroup(
            mnMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        mnMahasiswaLayout.setVerticalGroup(
            mnMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDMahasiswa.setBackground(new java.awt.Color(80, 80, 80));

        mnDataMahasiswa.setBackground(new java.awt.Color(80, 80, 80));
        mnDataMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnDataMahasiswaMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel23.setForeground(java.awt.Color.white);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel23.setText("Data Mahasiswa");

        javax.swing.GroupLayout mnDataMahasiswaLayout = new javax.swing.GroupLayout(mnDataMahasiswa);
        mnDataMahasiswa.setLayout(mnDataMahasiswaLayout);
        mnDataMahasiswaLayout.setHorizontalGroup(
            mnDataMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataMahasiswaLayout.setVerticalGroup(
            mnDataMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataMahasiswaCuti.setBackground(new java.awt.Color(80, 80, 80));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel24.setForeground(java.awt.Color.white);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel24.setText("Data Mahasiswa Cuti");

        javax.swing.GroupLayout mnDataMahasiswaCutiLayout = new javax.swing.GroupLayout(mnDataMahasiswaCuti);
        mnDataMahasiswaCuti.setLayout(mnDataMahasiswaCutiLayout);
        mnDataMahasiswaCutiLayout.setHorizontalGroup(
            mnDataMahasiswaCutiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaCutiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataMahasiswaCutiLayout.setVerticalGroup(
            mnDataMahasiswaCutiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaCutiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataMahasiswaDO.setBackground(new java.awt.Color(80, 80, 80));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel25.setForeground(java.awt.Color.white);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel25.setText("Data Mahasiswa DO");

        javax.swing.GroupLayout mnDataMahasiswaDOLayout = new javax.swing.GroupLayout(mnDataMahasiswaDO);
        mnDataMahasiswaDO.setLayout(mnDataMahasiswaDOLayout);
        mnDataMahasiswaDOLayout.setHorizontalGroup(
            mnDataMahasiswaDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaDOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataMahasiswaDOLayout.setVerticalGroup(
            mnDataMahasiswaDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaDOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataMahasiswaMD.setBackground(new java.awt.Color(80, 80, 80));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel26.setForeground(java.awt.Color.white);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel26.setText("Data Mahasiswa Keluar");

        javax.swing.GroupLayout mnDataMahasiswaMDLayout = new javax.swing.GroupLayout(mnDataMahasiswaMD);
        mnDataMahasiswaMD.setLayout(mnDataMahasiswaMDLayout);
        mnDataMahasiswaMDLayout.setHorizontalGroup(
            mnDataMahasiswaMDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaMDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataMahasiswaMDLayout.setVerticalGroup(
            mnDataMahasiswaMDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataMahasiswaMDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataAlumni.setBackground(new java.awt.Color(80, 80, 80));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel29.setForeground(java.awt.Color.white);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel29.setText("Data Mahasiswa Alumni");

        javax.swing.GroupLayout mnDataAlumniLayout = new javax.swing.GroupLayout(mnDataAlumni);
        mnDataAlumni.setLayout(mnDataAlumniLayout);
        mnDataAlumniLayout.setHorizontalGroup(
            mnDataAlumniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataAlumniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataAlumniLayout.setVerticalGroup(
            mnDataAlumniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataAlumniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnLapMahasiswa.setBackground(new java.awt.Color(80, 80, 80));
        mnLapMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnLapMahasiswaMouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel30.setForeground(java.awt.Color.white);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel30.setText("Laporan Data Mahasiswa");

        javax.swing.GroupLayout mnLapMahasiswaLayout = new javax.swing.GroupLayout(mnLapMahasiswa);
        mnLapMahasiswa.setLayout(mnLapMahasiswaLayout);
        mnLapMahasiswaLayout.setHorizontalGroup(
            mnLapMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        mnLapMahasiswaLayout.setVerticalGroup(
            mnLapMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        mnLapMahasiswa1.setBackground(new java.awt.Color(80, 80, 80));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel37.setForeground(java.awt.Color.white);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel37.setText("Data Nilai Mahasiswa");

        javax.swing.GroupLayout mnLapMahasiswa1Layout = new javax.swing.GroupLayout(mnLapMahasiswa1);
        mnLapMahasiswa1.setLayout(mnLapMahasiswa1Layout);
        mnLapMahasiswa1Layout.setHorizontalGroup(
            mnLapMahasiswa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapMahasiswa1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnLapMahasiswa1Layout.setVerticalGroup(
            mnLapMahasiswa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapMahasiswa1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mnDMahasiswaLayout = new javax.swing.GroupLayout(mnDMahasiswa);
        mnDMahasiswa.setLayout(mnDMahasiswaLayout);
        mnDMahasiswaLayout.setHorizontalGroup(
            mnDMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addComponent(jSeparator11)
            .addComponent(mnDataMahasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataMahasiswaCuti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataMahasiswaDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataMahasiswaMD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataAlumni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnLapMahasiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnLapMahasiswa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mnDMahasiswaLayout.setVerticalGroup(
            mnDMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDMahasiswaLayout.createSequentialGroup()
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataMahasiswaCuti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataMahasiswaDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataMahasiswaMD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataAlumni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnLapMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnLapMahasiswa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        mnAkademik.setBackground(new java.awt.Color(80, 80, 80));
        mnAkademik.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnAkademik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnAkademikMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnAkademikMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnAkademikMouseExited(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel31.setForeground(java.awt.Color.white);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/akademik.png"))); // NOI18N
        jLabel31.setText("Akademik");

        javax.swing.GroupLayout mnAkademikLayout = new javax.swing.GroupLayout(mnAkademik);
        mnAkademik.setLayout(mnAkademikLayout);
        mnAkademikLayout.setHorizontalGroup(
            mnAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnAkademikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        mnAkademikLayout.setVerticalGroup(
            mnAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnAkademikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDAkademik.setBackground(new java.awt.Color(80, 80, 80));

        mnSetThAkademik.setBackground(new java.awt.Color(80, 80, 80));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel32.setForeground(java.awt.Color.white);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel32.setText("Set Tahun Akademik");

        javax.swing.GroupLayout mnSetThAkademikLayout = new javax.swing.GroupLayout(mnSetThAkademik);
        mnSetThAkademik.setLayout(mnSetThAkademikLayout);
        mnSetThAkademikLayout.setHorizontalGroup(
            mnSetThAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnSetThAkademikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnSetThAkademikLayout.setVerticalGroup(
            mnSetThAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnSetThAkademikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataKelompokMK.setBackground(new java.awt.Color(80, 80, 80));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel33.setForeground(java.awt.Color.white);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel33.setText("Data Kelompok Matakuliah");

        javax.swing.GroupLayout mnDataKelompokMKLayout = new javax.swing.GroupLayout(mnDataKelompokMK);
        mnDataKelompokMK.setLayout(mnDataKelompokMKLayout);
        mnDataKelompokMKLayout.setHorizontalGroup(
            mnDataKelompokMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataKelompokMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataKelompokMKLayout.setVerticalGroup(
            mnDataKelompokMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataKelompokMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnMasterMK.setBackground(new java.awt.Color(80, 80, 80));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel34.setForeground(java.awt.Color.white);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel34.setText("Data Master Mahasiswa");

        javax.swing.GroupLayout mnMasterMKLayout = new javax.swing.GroupLayout(mnMasterMK);
        mnMasterMK.setLayout(mnMasterMKLayout);
        mnMasterMKLayout.setHorizontalGroup(
            mnMasterMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnMasterMKLayout.setVerticalGroup(
            mnMasterMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnMKSemesterAktif.setBackground(new java.awt.Color(80, 80, 80));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel35.setForeground(java.awt.Color.white);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel35.setText("Data Matakuliah Semester Aktif");

        javax.swing.GroupLayout mnMKSemesterAktifLayout = new javax.swing.GroupLayout(mnMKSemesterAktif);
        mnMKSemesterAktif.setLayout(mnMKSemesterAktifLayout);
        mnMKSemesterAktifLayout.setHorizontalGroup(
            mnMKSemesterAktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMKSemesterAktifLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        mnMKSemesterAktifLayout.setVerticalGroup(
            mnMKSemesterAktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMKSemesterAktifLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnJadwalMK.setBackground(new java.awt.Color(80, 80, 80));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel36.setForeground(java.awt.Color.white);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel36.setText("Data Jadwal Matakuliah");

        javax.swing.GroupLayout mnJadwalMKLayout = new javax.swing.GroupLayout(mnJadwalMK);
        mnJadwalMK.setLayout(mnJadwalMKLayout);
        mnJadwalMKLayout.setHorizontalGroup(
            mnJadwalMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJadwalMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnJadwalMKLayout.setVerticalGroup(
            mnJadwalMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnJadwalMKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mnDAkademikLayout = new javax.swing.GroupLayout(mnDAkademik);
        mnDAkademik.setLayout(mnDAkademikLayout);
        mnDAkademikLayout.setHorizontalGroup(
            mnDAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator12)
            .addComponent(mnSetThAkademik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataKelompokMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnMasterMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnMKSemesterAktif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnJadwalMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator13)
        );
        mnDAkademikLayout.setVerticalGroup(
            mnDAkademikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDAkademikLayout.createSequentialGroup()
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnSetThAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataKelompokMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnMasterMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnMKSemesterAktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnJadwalMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mnPegawai.setBackground(new java.awt.Color(80, 80, 80));
        mnPegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnPegawaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnPegawaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnPegawaiMouseExited(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel40.setForeground(java.awt.Color.white);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/group.png"))); // NOI18N
        jLabel40.setText("Pegawai");

        javax.swing.GroupLayout mnPegawaiLayout = new javax.swing.GroupLayout(mnPegawai);
        mnPegawai.setLayout(mnPegawaiLayout);
        mnPegawaiLayout.setHorizontalGroup(
            mnPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        mnPegawaiLayout.setVerticalGroup(
            mnPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDPegawai.setBackground(new java.awt.Color(80, 80, 80));

        mnDataPegawai.setBackground(new java.awt.Color(80, 80, 80));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel41.setForeground(java.awt.Color.white);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel41.setText("Data Pegawai");

        javax.swing.GroupLayout mnDataPegawaiLayout = new javax.swing.GroupLayout(mnDataPegawai);
        mnDataPegawai.setLayout(mnDataPegawaiLayout);
        mnDataPegawaiLayout.setHorizontalGroup(
            mnDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataPegawaiLayout.setVerticalGroup(
            mnDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnPegawaiMutasi.setBackground(new java.awt.Color(80, 80, 80));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel42.setForeground(java.awt.Color.white);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel42.setText("Data Pegawai Mutasi");

        javax.swing.GroupLayout mnPegawaiMutasiLayout = new javax.swing.GroupLayout(mnPegawaiMutasi);
        mnPegawaiMutasi.setLayout(mnPegawaiMutasiLayout);
        mnPegawaiMutasiLayout.setHorizontalGroup(
            mnPegawaiMutasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiMutasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnPegawaiMutasiLayout.setVerticalGroup(
            mnPegawaiMutasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiMutasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnPegawaiKeluar.setBackground(new java.awt.Color(80, 80, 80));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel43.setForeground(java.awt.Color.white);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel43.setText("Data Pegawai Keluar");

        javax.swing.GroupLayout mnPegawaiKeluarLayout = new javax.swing.GroupLayout(mnPegawaiKeluar);
        mnPegawaiKeluar.setLayout(mnPegawaiKeluarLayout);
        mnPegawaiKeluarLayout.setHorizontalGroup(
            mnPegawaiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnPegawaiKeluarLayout.setVerticalGroup(
            mnPegawaiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnPegawaiKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnTransJabatan.setBackground(new java.awt.Color(80, 80, 80));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel44.setForeground(java.awt.Color.white);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel44.setText("Data Transaksi Jabatan");

        javax.swing.GroupLayout mnTransJabatanLayout = new javax.swing.GroupLayout(mnTransJabatan);
        mnTransJabatan.setLayout(mnTransJabatanLayout);
        mnTransJabatanLayout.setHorizontalGroup(
            mnTransJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransJabatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        mnTransJabatanLayout.setVerticalGroup(
            mnTransJabatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransJabatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnTransJabatan1.setBackground(new java.awt.Color(80, 80, 80));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel47.setForeground(java.awt.Color.white);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel47.setText("Laporan Data Pegawai");

        javax.swing.GroupLayout mnTransJabatan1Layout = new javax.swing.GroupLayout(mnTransJabatan1);
        mnTransJabatan1.setLayout(mnTransJabatan1Layout);
        mnTransJabatan1Layout.setHorizontalGroup(
            mnTransJabatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransJabatan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnTransJabatan1Layout.setVerticalGroup(
            mnTransJabatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransJabatan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mnDPegawaiLayout = new javax.swing.GroupLayout(mnDPegawai);
        mnDPegawai.setLayout(mnDPegawaiLayout);
        mnDPegawaiLayout.setHorizontalGroup(
            mnDPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator14)
            .addComponent(mnDataPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnPegawaiMutasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnPegawaiKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnTransJabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator15)
            .addComponent(mnTransJabatan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mnDPegawaiLayout.setVerticalGroup(
            mnDPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDPegawaiLayout.createSequentialGroup()
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnPegawaiMutasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnPegawaiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTransJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTransJabatan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnUangKuliah.setBackground(new java.awt.Color(80, 80, 80));
        mnUangKuliah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnUangKuliah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnUangKuliahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnUangKuliahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnUangKuliahMouseExited(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel45.setForeground(java.awt.Color.white);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/uang.png"))); // NOI18N
        jLabel45.setText("Uang Kuliah");

        javax.swing.GroupLayout mnUangKuliahLayout = new javax.swing.GroupLayout(mnUangKuliah);
        mnUangKuliah.setLayout(mnUangKuliahLayout);
        mnUangKuliahLayout.setHorizontalGroup(
            mnUangKuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnUangKuliahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        mnUangKuliahLayout.setVerticalGroup(
            mnUangKuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnUangKuliahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnDUangKuliah.setBackground(new java.awt.Color(80, 80, 80));

        mnMasterUKT.setBackground(new java.awt.Color(80, 80, 80));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel46.setForeground(java.awt.Color.white);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel46.setText("Master Uang Kuliah");

        javax.swing.GroupLayout mnMasterUKTLayout = new javax.swing.GroupLayout(mnMasterUKT);
        mnMasterUKT.setLayout(mnMasterUKTLayout);
        mnMasterUKTLayout.setHorizontalGroup(
            mnMasterUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnMasterUKTLayout.setVerticalGroup(
            mnMasterUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnMasterUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnDataUKTMahasiswa.setBackground(new java.awt.Color(80, 80, 80));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel48.setForeground(java.awt.Color.white);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel48.setText("Data UKT Mahasiswa");

        javax.swing.GroupLayout mnDataUKTMahasiswaLayout = new javax.swing.GroupLayout(mnDataUKTMahasiswa);
        mnDataUKTMahasiswa.setLayout(mnDataUKTMahasiswaLayout);
        mnDataUKTMahasiswaLayout.setHorizontalGroup(
            mnDataUKTMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataUKTMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnDataUKTMahasiswaLayout.setVerticalGroup(
            mnDataUKTMahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDataUKTMahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnTransUKT.setBackground(new java.awt.Color(80, 80, 80));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel49.setForeground(java.awt.Color.white);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel49.setText("Data Transaksi UKT");

        javax.swing.GroupLayout mnTransUKTLayout = new javax.swing.GroupLayout(mnTransUKT);
        mnTransUKT.setLayout(mnTransUKTLayout);
        mnTransUKTLayout.setHorizontalGroup(
            mnTransUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mnTransUKTLayout.setVerticalGroup(
            mnTransUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnTransUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnLapTransUKT.setBackground(new java.awt.Color(80, 80, 80));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel50.setForeground(java.awt.Color.white);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submenu.png"))); // NOI18N
        jLabel50.setText("Laporan Transaksi UKT");

        javax.swing.GroupLayout mnLapTransUKTLayout = new javax.swing.GroupLayout(mnLapTransUKT);
        mnLapTransUKT.setLayout(mnLapTransUKTLayout);
        mnLapTransUKTLayout.setHorizontalGroup(
            mnLapTransUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapTransUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        mnLapTransUKTLayout.setVerticalGroup(
            mnLapTransUKTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLapTransUKTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mnDUangKuliahLayout = new javax.swing.GroupLayout(mnDUangKuliah);
        mnDUangKuliah.setLayout(mnDUangKuliahLayout);
        mnDUangKuliahLayout.setHorizontalGroup(
            mnDUangKuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator16)
            .addComponent(jSeparator17)
            .addComponent(mnMasterUKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnDataUKTMahasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnTransUKT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnLapTransUKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mnDUangKuliahLayout.setVerticalGroup(
            mnDUangKuliahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnDUangKuliahLayout.createSequentialGroup()
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnMasterUKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDataUKTMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTransUKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnLapTransUKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnLogout.setBackground(new java.awt.Color(80, 80, 80));
        mnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnLogoutMouseExited(evt);
            }
        });

        lbLogout.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbLogout.setForeground(java.awt.Color.white);
        lbLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/log-in.png"))); // NOI18N
        lbLogout.setText("Log Out");

        javax.swing.GroupLayout mnLogoutLayout = new javax.swing.GroupLayout(mnLogout);
        mnLogout.setLayout(mnLogoutLayout);
        mnLogoutLayout.setHorizontalGroup(
            mnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogout)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        mnLogoutLayout.setVerticalGroup(
            mnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mnLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pMenuLayout = new javax.swing.GroupLayout(pMenu);
        pMenu.setLayout(pMenuLayout);
        pMenuLayout.setHorizontalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mnPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(mnSistem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addGroup(pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mnDSistem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnDMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnDMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnDAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnUangKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnDUangKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pMenuLayout.setVerticalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mnSistem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDSistem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDMahasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnUangKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnDUangKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pLogin.setBackground(java.awt.Color.darkGray);
        pLogin.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_login.png"))); // NOI18N
        jLabel1.setText("Login Panel");

        txtIdUser.setBackground(java.awt.Color.darkGray);
        txtIdUser.setForeground(new java.awt.Color(255, 255, 255));
        txtIdUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdUser.setText("ID001");
        txtIdUser.setBorder(null);

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ID. User");

        txtPass.setBackground(java.awt.Color.darkGray);
        txtPass.setForeground(java.awt.Color.white);
        txtPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPass.setText("admin");
        txtPass.setBorder(null);

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Password");

        btnLogin.setBackground(java.awt.Color.blue);
        btnLogin.setForeground(java.awt.Color.white);
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/log-in.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnBatal.setBackground(java.awt.Color.blue);
        btnBatal.setForeground(java.awt.Color.white);
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cancel.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sistem Akademik");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Politeknik Negeri Bandung");

        jPanel3.setBackground(new java.awt.Color(80, 80, 80));

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        jLabel27.setForeground(java.awt.Color.white);
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Copyright @ 2018 SimbahGanteng.com");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        javax.swing.GroupLayout pLoginLayout = new javax.swing.GroupLayout(pLogin);
        pLogin.setLayout(pLoginLayout);
        pLoginLayout.setHorizontalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addGroup(pLoginLayout.createSequentialGroup()
                        .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdUser)
                    .addComponent(jSeparator1)
                    .addComponent(txtPass)
                    .addComponent(jSeparator2)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pLoginLayout.setVerticalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pNav.setBackground(new java.awt.Color(80, 80, 80));
        pNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pNavMouseClicked(evt);
            }
        });

        lbMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/menu.png"))); // NOI18N
        lbMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pNavLayout = new javax.swing.GroupLayout(pNav);
        pNav.setLayout(pNavLayout);
        pNavLayout.setHorizontalGroup(
            pNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNavLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(lbMenu)
                .addGap(5, 5, 5))
        );
        pNavLayout.setVerticalGroup(
            pNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/close.png"))); // NOI18N
        lbClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCloseMouseExited(evt);
            }
        });

        lbPengguna.setFont(new java.awt.Font("Segoe UI Black", 2, 13)); // NOI18N
        lbPengguna.setForeground(java.awt.Color.orange);
        lbPengguna.setText("Sistem Akademik Politeknik Negeri Bandung v.1.0");

        lbAbout.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbAbout.setForeground(java.awt.Color.white);
        lbAbout.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAbout.setText("Tentang Aplikasi");
        lbAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbAboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbAboutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPengguna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbClose)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lbAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPengguna))
                        .addGap(5, 5, 5))))
        );

        jPanel2.setBackground(new java.awt.Color(80, 80, 80));

        lbKeterangan.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbKeterangan.setForeground(java.awt.Color.white);
        lbKeterangan.setText("ID. User : ..... | Lev.User : .......");

        lbCopyright.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbCopyright.setForeground(java.awt.Color.white);
        lbCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCopyright.setText("Copyright@2018 SimbahGanteng.com All Right Reserved");
        lbCopyright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCopyrightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCopyrightMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbKeterangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbKeterangan, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addComponent(lbCopyright))
        );

        jpMenu.setBackground(java.awt.Color.white);
        jpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpMenuMouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon-login.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel14.setText("Politeknik Negeri Bandung");

        jSeparator4.setBackground(java.awt.Color.darkGray);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Sistem Akademik");

        pNotif.setBackground(java.awt.Color.darkGray);

        lbJudulNotif.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbJudulNotif.setForeground(java.awt.Color.white);
        lbJudulNotif.setText("Sistem Informasi Akademik");

        lbKetNotif.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbKetNotif.setForeground(java.awt.Color.white);
        lbKetNotif.setText("Politeknik Negeri Bandung");

        lbDetailNotif.setForeground(java.awt.Color.white);
        lbDetailNotif.setText("Product Version : 1.0");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setForeground(java.awt.Color.white);
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("x");
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pNotifLayout = new javax.swing.GroupLayout(pNotif);
        pNotif.setLayout(pNotifLayout);
        pNotifLayout.setHorizontalGroup(
            pNotifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNotifLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pNotifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbKetNotif, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addGroup(pNotifLayout.createSequentialGroup()
                        .addComponent(lbDetailNotif)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pNotifLayout.createSequentialGroup()
                        .addComponent(lbJudulNotif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pNotifLayout.setVerticalGroup(
            pNotifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNotifLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pNotifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbJudulNotif)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNotifLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(4, 4, 4)))
                .addGap(0, 0, 0)
                .addComponent(lbKetNotif, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbDetailNotif)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMenu.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpMenu.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpMenu.setLayer(jSeparator4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpMenu.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpMenu.setLayer(pNotif, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
        jpMenu.setLayout(jpMenuLayout);
        jpMenuLayout.setHorizontalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pNotif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMenuLayout.createSequentialGroup()
                        .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator4))
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );
        jpMenuLayout.setVerticalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(pNotif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1469, Short.MAX_VALUE)
                .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14)
                        .addGap(37, 37, 37))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpMenu, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        txtIdUser.setText("");
        txtPass.setText("");
    }//GEN-LAST:event_btnBatalActionPerformed

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        
        int close = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar ?", "", JOptionPane.YES_NO_OPTION);
        if(close==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMouseClicked

    private void mnSistemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSistemMouseClicked
        mnDSistem.setVisible(true);
        mnDMaster.setVisible(false);
        mnDAkademik.setVisible(false);
        mnDMahasiswa.setVisible(false);
        mnDPegawai.setVisible(false);
        mnDUangKuliah.setVisible(false);
    }//GEN-LAST:event_mnSistemMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        
    }//GEN-LAST:event_jPanel4MouseClicked

    private void mnMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMasterMouseClicked
        mnDSistem.setVisible(false);
        mnDMaster.setVisible(true);
        mnDAkademik.setVisible(false);
        mnDMahasiswa.setVisible(false);
        mnDPegawai.setVisible(false);
        mnDUangKuliah.setVisible(false);
    }//GEN-LAST:event_mnMasterMouseClicked

    private void mnMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMahasiswaMouseClicked
        mnDSistem.setVisible(false);
        mnDMaster.setVisible(false);
        mnDAkademik.setVisible(false);
        mnDMahasiswa.setVisible(true);
        mnDPegawai.setVisible(false);
        mnDUangKuliah.setVisible(false);
    }//GEN-LAST:event_mnMahasiswaMouseClicked

    private void mnAkademikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnAkademikMouseClicked
        mnDSistem.setVisible(false);
        mnDMaster.setVisible(false);
        mnDAkademik.setVisible(true);
        mnDMahasiswa.setVisible(false);
        mnDPegawai.setVisible(false);
        mnDUangKuliah.setVisible(false);
    }//GEN-LAST:event_mnAkademikMouseClicked

    private void mnPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnPegawaiMouseClicked
        mnDSistem.setVisible(false);
        mnDMaster.setVisible(false);
        mnDAkademik.setVisible(false);
        mnDMahasiswa.setVisible(false);
        mnDPegawai.setVisible(true);
        mnDUangKuliah.setVisible(false);
    }//GEN-LAST:event_mnPegawaiMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(txtIdUser.getText().equals("") || txtIdUser.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Silahkan lengkapi data!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            aksiLogin();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void jpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMenuMouseClicked
        pMenu.setVisible(false);
        pNotif.setVisible(false);
        lbAbout.setForeground(Color.white);
    }//GEN-LAST:event_jpMenuMouseClicked

    private void lbMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMenuMouseClicked
        pMenu.setVisible(true);
        lbAbout.setForeground(Color.ORANGE);
    }//GEN-LAST:event_lbMenuMouseClicked

    private void lbUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbUserMouseClicked

    private void mnUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUserMouseClicked
        if(lbUser.getText().equals("Ganti Password")){
            jdUbahPassword fr = new jdUbahPassword(this, true, vid_user);
            fr.setVisible(true);
        }else{
            IfrUser fr = new IfrUser();
            jpMenu.add(fr);
            fr.setVisible(true);
        }
    }//GEN-LAST:event_mnUserMouseClicked

    private void mnSistemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSistemMouseEntered
        set_color1(mnSistem);
    }//GEN-LAST:event_mnSistemMouseEntered

    private void mnSistemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSistemMouseExited
        set_color2(mnSistem);
    }//GEN-LAST:event_mnSistemMouseExited

    private void mnUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUserMouseEntered
        set_color1(mnUser);
    }//GEN-LAST:event_mnUserMouseEntered

    private void mnUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUserMouseExited
        set_color2(mnUser);
    }//GEN-LAST:event_mnUserMouseExited

    private void mnKeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnKeluarMouseEntered
        set_color1(mnKeluar);
    }//GEN-LAST:event_mnKeluarMouseEntered

    private void mnKeluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnKeluarMouseExited
        set_color2(mnKeluar);
    }//GEN-LAST:event_mnKeluarMouseExited

    private void mnMasterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMasterMouseEntered
        set_color1(mnMaster);
    }//GEN-LAST:event_mnMasterMouseEntered

    private void mnUangKuliahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUangKuliahMouseClicked
        mnDSistem.setVisible(false);
        mnDMaster.setVisible(false);
        mnDAkademik.setVisible(false);
        mnDMahasiswa.setVisible(false);
        mnDPegawai.setVisible(false);
        mnDUangKuliah.setVisible(true);
    }//GEN-LAST:event_mnUangKuliahMouseClicked

    private void mnMasterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMasterMouseExited
        set_color2(mnMaster);
    }//GEN-LAST:event_mnMasterMouseExited

    private void pMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMenuMouseClicked
        hidden_all_menu(false);
    }//GEN-LAST:event_pMenuMouseClicked

    private void pNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pNavMouseClicked
        hidden_all_menu(false);
    }//GEN-LAST:event_pNavMouseClicked

    private void jLabel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseEntered
        jLabel27.setForeground(Color.ORANGE);
    }//GEN-LAST:event_jLabel27MouseEntered

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
        jLabel27.setForeground(Color.white);
    }//GEN-LAST:event_jLabel27MouseExited

    private void mnTAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTAMouseEntered
        set_color1(mnTA);
    }//GEN-LAST:event_mnTAMouseEntered

    private void mnTAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTAMouseExited
        set_color2(mnTA);
    }//GEN-LAST:event_mnTAMouseExited

    private void mnJurusanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnJurusanMouseEntered
        set_color1(mnJurusan);
    }//GEN-LAST:event_mnJurusanMouseEntered

    private void mnJurusanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnJurusanMouseExited
        set_color2(mnJurusan);
    }//GEN-LAST:event_mnJurusanMouseExited

    private void mnProdiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnProdiMouseEntered
        set_color1(mnProdi);
    }//GEN-LAST:event_mnProdiMouseEntered

    private void mnProdiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnProdiMouseExited
        set_color2(mnProdi);
    }//GEN-LAST:event_mnProdiMouseExited

    private void mnTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTAMouseClicked
        IfrTahunAngkatan fr = new IfrTahunAngkatan();
        jpMenu.add(fr);
        fr.setVisible(true);
    }//GEN-LAST:event_mnTAMouseClicked

    private void mnJurusanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnJurusanMouseClicked
        IfrJurusan fr = new IfrJurusan();
        jpMenu.add(fr);
        fr.setVisible(true);
    }//GEN-LAST:event_mnJurusanMouseClicked

    private void mnProdiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnProdiMouseClicked
        IfrProdi fr = new IfrProdi();
        jpMenu.add(fr);
        fr.setVisible(true);
    }//GEN-LAST:event_mnProdiMouseClicked

    private void mnJabatanPegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnJabatanPegawaiMouseEntered
        set_color1(mnJabatanPegawai);
    }//GEN-LAST:event_mnJabatanPegawaiMouseEntered

    private void mnJabatanPegawaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnJabatanPegawaiMouseExited
        set_color2(mnJabatanPegawai);
    }//GEN-LAST:event_mnJabatanPegawaiMouseExited

    private void mnDataMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnDataMahasiswaMouseClicked
        IfrMahasiswa fr = new IfrMahasiswa();
        jpMenu.add(fr);
        fr.setVisible(true);
    }//GEN-LAST:event_mnDataMahasiswaMouseClicked

    private void mnLapMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLapMahasiswaMouseClicked
        IfrLapMahasiswa fr = new IfrLapMahasiswa();
        jpMenu.add(fr);
        fr.setVisible(true);
    }//GEN-LAST:event_mnLapMahasiswaMouseClicked

    private void mnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLogoutMouseClicked
        int logout = JOptionPane.showConfirmDialog(this, "Apakah anda ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(logout==JOptionPane.YES_OPTION){
            aksiLogout();
        }
    }//GEN-LAST:event_mnLogoutMouseClicked

    private void lbCopyrightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCopyrightMouseEntered
        lbCopyright.setForeground(Color.ORANGE);
    }//GEN-LAST:event_lbCopyrightMouseEntered

    private void lbCopyrightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCopyrightMouseExited
        lbCopyright.setForeground(Color.white);
    }//GEN-LAST:event_lbCopyrightMouseExited

    private void lbAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAboutMouseEntered
        lbAbout.setForeground(Color.ORANGE);
    }//GEN-LAST:event_lbAboutMouseEntered

    private void lbAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAboutMouseExited
        lbAbout.setForeground(Color.white);
    }//GEN-LAST:event_lbAboutMouseExited

    private void lbAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAboutMouseClicked
        showNotif("Sistem Informasi Akademik", "Politeknik Negeri Bandung", "Product Version : 1.0");
    }//GEN-LAST:event_lbAboutMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        pNotif.setVisible(false);
        lbAbout.setForeground(Color.white);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        pNotif.setVisible(false);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/closehover.png")));
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/close.png")));
    }//GEN-LAST:event_lbCloseMouseExited

    private void mnMahasiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMahasiswaMouseEntered
        set_color1(mnMahasiswa);
    }//GEN-LAST:event_mnMahasiswaMouseEntered

    private void mnMahasiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnMahasiswaMouseExited
        set_color2(mnMahasiswa);
    }//GEN-LAST:event_mnMahasiswaMouseExited

    private void mnAkademikMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnAkademikMouseEntered
        set_color1(mnAkademik);
    }//GEN-LAST:event_mnAkademikMouseEntered

    private void mnAkademikMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnAkademikMouseExited
        set_color2(mnAkademik);
    }//GEN-LAST:event_mnAkademikMouseExited

    private void mnPegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnPegawaiMouseEntered
        set_color1(mnPegawai);
    }//GEN-LAST:event_mnPegawaiMouseEntered

    private void mnPegawaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnPegawaiMouseExited
        set_color2(mnPegawai);
    }//GEN-LAST:event_mnPegawaiMouseExited

    private void mnUangKuliahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUangKuliahMouseEntered
        set_color1(mnUangKuliah);
    }//GEN-LAST:event_mnUangKuliahMouseEntered

    private void mnUangKuliahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnUangKuliahMouseExited
        set_color2(mnUangKuliah);
    }//GEN-LAST:event_mnUangKuliahMouseExited

    private void mnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLogoutMouseEntered
        set_color1(mnLogout);
    }//GEN-LAST:event_mnLogoutMouseEntered

    private void mnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLogoutMouseExited
        set_color2(mnLogout);
    }//GEN-LAST:event_mnLogoutMouseExited

    private void mnKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnKeluarMouseClicked
        int close = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar ?", "", JOptionPane.YES_NO_OPTION);
        if(close==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_mnKeluarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
            //UIManager.setLookAndFeel(new MaterialLookAndFeel());
            com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme("Default", "Java Swing", "");
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            SwingUtilities.updateComponentTreeUI(new FrMenu());
            DecorationHelper.decorateWindows(false);
        }finally{
            new FrMenu().setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JDesktopPane jpMenu;
    private javax.swing.JLabel lbAbout;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbCopyright;
    private javax.swing.JLabel lbDetailNotif;
    private javax.swing.JLabel lbJudulNotif;
    private javax.swing.JLabel lbKetNotif;
    private javax.swing.JLabel lbKeterangan;
    private javax.swing.JLabel lbLogout;
    private javax.swing.JLabel lbMenu;
    private javax.swing.JLabel lbPengguna;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel mnAkademik;
    private javax.swing.JPanel mnDAkademik;
    private javax.swing.JPanel mnDMahasiswa;
    private javax.swing.JPanel mnDMaster;
    private javax.swing.JPanel mnDPegawai;
    private javax.swing.JPanel mnDSistem;
    private javax.swing.JPanel mnDUangKuliah;
    private javax.swing.JPanel mnDataAlumni;
    private javax.swing.JPanel mnDataKelompokMK;
    private javax.swing.JPanel mnDataMahasiswa;
    private javax.swing.JPanel mnDataMahasiswaCuti;
    private javax.swing.JPanel mnDataMahasiswaDO;
    private javax.swing.JPanel mnDataMahasiswaMD;
    private javax.swing.JPanel mnDataPegawai;
    private javax.swing.JPanel mnDataUKTMahasiswa;
    private javax.swing.JPanel mnJabatanPegawai;
    private javax.swing.JPanel mnJadwalMK;
    private javax.swing.JPanel mnJurusan;
    private javax.swing.JPanel mnKeluar;
    private javax.swing.JPanel mnLapMahasiswa;
    private javax.swing.JPanel mnLapMahasiswa1;
    private javax.swing.JPanel mnLapTransUKT;
    private javax.swing.JPanel mnLogout;
    private javax.swing.JPanel mnMKSemesterAktif;
    private javax.swing.JPanel mnMahasiswa;
    private javax.swing.JPanel mnMaster;
    private javax.swing.JPanel mnMasterMK;
    private javax.swing.JPanel mnMasterUKT;
    private javax.swing.JPanel mnPegawai;
    private javax.swing.JPanel mnPegawaiKeluar;
    private javax.swing.JPanel mnPegawaiMutasi;
    private javax.swing.JPanel mnProdi;
    private javax.swing.JPanel mnSetThAkademik;
    private javax.swing.JPanel mnSistem;
    private javax.swing.JPanel mnTA;
    private javax.swing.JPanel mnTransJabatan;
    private javax.swing.JPanel mnTransJabatan1;
    private javax.swing.JPanel mnTransUKT;
    private javax.swing.JPanel mnUangKuliah;
    private javax.swing.JPanel mnUser;
    private javax.swing.JPanel pLogin;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pNav;
    private javax.swing.JPanel pNotif;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
