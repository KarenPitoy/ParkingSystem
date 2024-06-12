package parkingsystem;

import com.mysql.cj.jdbc.Blob;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;


// Import the UIDCallback interface from the trial101 package
import trial101.UIDCallback;

public class Admin extends javax.swing.JFrame implements UIDCallback {
    Statement st;
    Connection con; 
    PreparedStatement pst; 
    ResultSet rs;
    private static Set<Character> allowedSymbols = new HashSet<>();

    public Admin() {
        initComponents();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_parking?zeroDateTimeBehavior=CONVERT_TO_NULL ","root","");
            st = con.createStatement();
        } catch(Exception e) {
            System.out.print("No Database Detected");
        }
        register.setVisible(false);
        check.setVisible(false);
        top.setVisible(false);
        info.setVisible(false);
        showDate();
        showTime();
        Update_table();
        Update_table1();

    } 
    
    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MMM dd, yyyy");
        date.setText(s.format(d));
    }

    void showTime() {
        new Timer(0,(ActionEvent ae)->{
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
            time.setText(s.format(d));
        }).start();
    }

    // Implement the onUIDReceived method from the UIDCallback interface
    @Override
    public void onUIDReceived(String uid) {
      //  setUID(uid); // Update the UI with the received UID
      if (uid.startsWith("Entrance: ")) {
        String status = uid.substring(10); // Extract the status part (after the prefix "1- ")
        id.setText(status);
      }else if (uid.startsWith("Exit: ")) {
        String status = uid.substring(6); // Extract the status part (after the prefix "1- ")
        id.setText(status);
      }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        register = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        age = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        no = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        m = new javax.swing.JRadioButton();
        f = new javax.swing.JRadioButton();
        id = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        kwarta = new javax.swing.JTextField();
        add = new javax.swing.JTextField();
        check = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        photo1 = new javax.swing.JLabel();
        top = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        id1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        balance = new javax.swing.JTextField();
        age1 = new javax.swing.JTextField();
        add1 = new javax.swing.JTextField();
        no1 = new javax.swing.JTextField();
        email1 = new javax.swing.JTextField();
        sexx = new javax.swing.JTextField();
        info = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        search1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(969, 687));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(969, 687));
        jPanel2.setMinimumSize(new java.awt.Dimension(969, 687));
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AUTOMATED PARKING SYSTEM");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 500, 60);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATE:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(540, 10, 90, 40);

        date.setBackground(new java.awt.Color(0, 0, 0));
        date.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        date.setForeground(new java.awt.Color(0, 0, 0));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("DATE");
        jPanel1.add(date);
        date.setBounds(580, 10, 190, 40);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TIME:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(750, 10, 90, 40);

        time.setBackground(new java.awt.Color(0, 0, 0));
        time.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        time.setForeground(new java.awt.Color(0, 0, 0));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("TIME");
        jPanel1.add(time);
        time.setBounds(820, 10, 110, 40);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(20, 20, 920, 62);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("TOP-UP");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(450, 10, 210, 50);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("REGISTER");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);
        jButton4.setBounds(10, 10, 210, 50);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("VIEW ACCOUNTS");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(220, 10, 220, 50);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("PARKING INFO");
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8);
        jButton8.setBounds(660, 10, 230, 50);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(40, 100, 890, 60);

        register.setBackground(new java.awt.Color(255, 255, 255));
        register.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        register.setMaximumSize(new java.awt.Dimension(892, 480));
        register.setMinimumSize(new java.awt.Dimension(892, 480));
        register.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sex:");
        register.add(jLabel2);
        jLabel2.setBounds(50, 340, 130, 40);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Cellphone No.:");
        register.add(jLabel3);
        jLabel3.setBounds(450, 180, 170, 40);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Email:");
        register.add(jLabel7);
        jLabel7.setBounds(450, 240, 180, 40);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Age:");
        register.add(jLabel8);
        jLabel8.setBounds(450, 60, 160, 40);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Name:");
        register.add(jLabel10);
        jLabel10.setBounds(50, 290, 130, 40);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Address:");
        register.add(jLabel11);
        jLabel11.setBounds(450, 120, 160, 40);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID:");
        register.add(jLabel12);
        jLabel12.setBounds(50, 230, 130, 40);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Balance:");
        register.add(jLabel13);
        jLabel13.setBounds(450, 300, 170, 40);

        age.setBackground(new java.awt.Color(255, 255, 255));
        age.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        age.setForeground(new java.awt.Color(0, 0, 0));
        age.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ageKeyTyped(evt);
            }
        });
        register.add(age);
        age.setBounds(620, 60, 200, 40);

        name.setBackground(new java.awt.Color(255, 255, 255));
        name.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });
        register.add(name);
        name.setBounds(200, 290, 200, 40);

        no.setBackground(new java.awt.Color(255, 255, 255));
        no.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        no.setForeground(new java.awt.Color(0, 0, 0));
        no.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noKeyTyped(evt);
            }
        });
        register.add(no);
        no.setBounds(620, 180, 200, 40);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Simplex_IV50", 0, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("REGISTER");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        register.add(jButton5);
        jButton5.setBounds(370, 400, 150, 40);

        m.setBackground(new java.awt.Color(255, 255, 255));
        m.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        m.setForeground(new java.awt.Color(0, 0, 0));
        m.setText("Male");
        m.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mItemStateChanged(evt);
            }
        });
        register.add(m);
        m.setBounds(200, 340, 110, 40);

        f.setBackground(new java.awt.Color(255, 255, 255));
        f.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        f.setForeground(new java.awt.Color(0, 0, 0));
        f.setText("Female");
        f.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fItemStateChanged(evt);
            }
        });
        register.add(f);
        f.setBounds(310, 340, 100, 40);

        id.setBackground(new java.awt.Color(255, 255, 255));
        id.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        id.setForeground(new java.awt.Color(0, 0, 0));
        id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });
        register.add(id);
        id.setBounds(200, 230, 200, 40);

        photo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        photo.setPreferredSize(new java.awt.Dimension(200, 200));
        register.add(photo);
        photo.setBounds(50, 20, 200, 200);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Simplex_IV50", 0, 16)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("UPLOAD");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        register.add(jButton6);
        jButton6.setBounds(270, 70, 150, 40);

        email.setBackground(new java.awt.Color(255, 255, 255));
        email.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(0, 0, 0));
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });
        register.add(email);
        email.setBounds(620, 240, 200, 40);

        kwarta.setBackground(new java.awt.Color(255, 255, 255));
        kwarta.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        kwarta.setForeground(new java.awt.Color(0, 0, 0));
        kwarta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        kwarta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kwartaKeyTyped(evt);
            }
        });
        register.add(kwarta);
        kwarta.setBounds(620, 300, 200, 40);

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        add.setForeground(new java.awt.Color(0, 0, 0));
        add.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addKeyTyped(evt);
            }
        });
        register.add(add);
        add.setBounds(620, 120, 200, 40);

        jPanel2.add(register);
        register.setBounds(35, 170, 892, 460);

        check.setBackground(new java.awt.Color(255, 255, 255));
        check.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        check.setMaximumSize(new java.awt.Dimension(892, 480));
        check.setMinimumSize(new java.awt.Dimension(892, 480));
        check.setLayout(null);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Age", "Sex", "Address", "Cellphone No.", "Email", "Balance"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        check.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 680, 370);

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setForeground(new java.awt.Color(0, 0, 0));
        search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        check.add(search);
        search.setBounds(250, 20, 310, 30);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        check.add(jButton1);
        jButton1.setBounds(570, 20, 90, 30);

        photo1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        photo1.setPreferredSize(new java.awt.Dimension(134, 172));
        check.add(photo1);
        photo1.setBounds(710, 100, 134, 172);

        jPanel2.add(check);
        check.setBounds(35, 170, 892, 450);

        top.setBackground(new java.awt.Color(255, 255, 255));
        top.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        top.setMaximumSize(new java.awt.Dimension(892, 455));
        top.setMinimumSize(new java.awt.Dimension(892, 455));
        top.setLayout(null);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("ID");
        top.add(jLabel20);
        jLabel20.setBounds(40, 90, 130, 30);

        id1.setEditable(false);
        id1.setBackground(new java.awt.Color(255, 255, 255));
        id1.setFont(new java.awt.Font("Simplex_IV50", 0, 14)); // NOI18N
        id1.setForeground(new java.awt.Color(0, 0, 0));
        id1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        id1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                id1KeyTyped(evt);
            }
        });
        top.add(id1);
        id1.setBounds(210, 90, 200, 30);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Name:");
        top.add(jLabel22);
        jLabel22.setBounds(40, 130, 190, 30);

        name1.setBackground(new java.awt.Color(255, 255, 255));
        name1.setFont(new java.awt.Font("Simplex_IV50", 0, 14)); // NOI18N
        name1.setForeground(new java.awt.Color(0, 0, 0));
        name1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        top.add(name1);
        name1.setBounds(210, 130, 200, 30);

        amount.setBackground(new java.awt.Color(255, 255, 255));
        amount.setFont(new java.awt.Font("Simplex_IV50", 0, 14)); // NOI18N
        amount.setForeground(new java.awt.Color(0, 0, 0));
        amount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });
        top.add(amount);
        amount.setBounds(210, 210, 200, 30);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Current Balance: ");
        top.add(jLabel23);
        jLabel23.setBounds(40, 170, 170, 30);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Amount Added:");
        top.add(jLabel24);
        jLabel24.setBounds(40, 210, 190, 30);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Simplex_IV50", 0, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("Load");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        top.add(jButton7);
        jButton7.setBounds(140, 280, 150, 40);

        tbl1.setFont(new java.awt.Font("Simplex_IV50", 0, 12)); // NOI18N
        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Current Balance"
            }
        ));
        tbl1.setEditingRow(0);
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl1MouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tbl1);

        top.add(jScrollPane3);
        jScrollPane3.setBounds(430, 20, 430, 406);

        balance.setEditable(false);
        balance.setBackground(new java.awt.Color(255, 255, 255));
        balance.setFont(new java.awt.Font("Simplex_IV50", 0, 14)); // NOI18N
        balance.setForeground(new java.awt.Color(0, 0, 0));
        balance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                balanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                balanceKeyTyped(evt);
            }
        });
        top.add(balance);
        balance.setBounds(210, 170, 200, 30);

        age1.setEditable(false);
        age1.setBackground(new java.awt.Color(255, 255, 255));
        age1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        age1.setForeground(new java.awt.Color(255, 255, 255));
        age1.setBorder(null);
        age1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age1ActionPerformed(evt);
            }
        });
        age1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                age1KeyTyped(evt);
            }
        });
        top.add(age1);
        age1.setBounds(370, 250, 20, 20);

        add1.setEditable(false);
        add1.setBackground(new java.awt.Color(255, 255, 255));
        add1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        add1.setForeground(new java.awt.Color(255, 255, 255));
        add1.setBorder(null);
        add1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                add1KeyTyped(evt);
            }
        });
        top.add(add1);
        add1.setBounds(130, 360, 200, 40);

        no1.setEditable(false);
        no1.setBackground(new java.awt.Color(255, 255, 255));
        no1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        no1.setForeground(new java.awt.Color(255, 255, 255));
        no1.setBorder(null);
        no1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no1KeyTyped(evt);
            }
        });
        top.add(no1);
        no1.setBounds(370, 370, 20, 20);

        email1.setEditable(false);
        email1.setBackground(new java.awt.Color(255, 255, 255));
        email1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        email1.setForeground(new java.awt.Color(255, 255, 255));
        email1.setBorder(null);
        email1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                email1KeyTyped(evt);
            }
        });
        top.add(email1);
        email1.setBounds(370, 430, 20, 20);

        sexx.setEditable(false);
        sexx.setBackground(new java.awt.Color(255, 255, 255));
        sexx.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        sexx.setForeground(new java.awt.Color(255, 255, 255));
        sexx.setBorder(null);
        sexx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sexxKeyTyped(evt);
            }
        });
        top.add(sexx);
        sexx.setBounds(370, 310, 20, 20);

        jPanel2.add(top);
        top.setBounds(35, 170, 892, 455);

        info.setBackground(new java.awt.Color(255, 255, 255));
        info.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        info.setMaximumSize(new java.awt.Dimension(892, 480));
        info.setMinimumSize(new java.awt.Dimension(892, 480));
        info.setPreferredSize(new java.awt.Dimension(892, 455));
        info.setLayout(null);

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Balance", "Date", "Entrance Time", "Exit Time", "Amount Paid", "Current Balance"
            }
        ));
        tbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl2);

        info.add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 860, 370);

        search1.setBackground(new java.awt.Color(255, 255, 255));
        search1.setForeground(new java.awt.Color(0, 0, 0));
        search1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search1MouseClicked(evt);
            }
        });
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search1KeyReleased(evt);
            }
        });
        info.add(search1);
        search1.setBounds(250, 20, 310, 30);

        jButton9.setBackground(new java.awt.Color(0, 0, 0));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("SEARCH");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        info.add(jButton9);
        jButton9.setBounds(570, 20, 90, 30);

        jPanel2.add(info);
        info.setBounds(35, 170, 892, 455);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 969, 687);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
        String sql = "SELECT `RFID`, `Name`,`Balance` FROM `registration` WHERE 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tbl1.setModel(DbUtils.resultSetToTableModel(rs));

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    
        
    register.setVisible(false);
    check.setVisible(false);
    top.setVisible(true);
    info.setVisible(false);
    id1.setText("");
    name1.setText("");
    balance.setText("");
    amount.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    register.setVisible(true);
    check.setVisible(false);
    top.setVisible(false);
    info.setVisible(false);
     id.setText("");
     name.setText("");
     email.setText("");
     m.setSelected(false);
     f.setSelected(false);
     no.setText("");
     kwarta.setText("");
     age.setText("");
     add.setText("");
     photo.setIcon(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    register.setVisible(false);
    check.setVisible(true);
    top.setVisible(false);
    info.setVisible(false);
    photo1.setIcon(null);
    tbl.clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void ageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageKeyTyped
     String currentText = age.getText();
     char a = evt.getKeyChar();

     if (!(Character.isDigit(a) && currentText.length() < 2)) {
         evt.consume();
     }
    }//GEN-LAST:event_ageKeyTyped

    private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed

    }//GEN-LAST:event_nameKeyPressed

    private void nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyTyped
       String currentText = name.getText();
       char a = evt.getKeyChar();
       if (!(Character.isLetter(a) || a == ' ' || (a == '.' && !currentText.contains(".")))) {
           evt.consume();
       } else if (Character.isLetter(a) && (currentText.isEmpty() || currentText.endsWith(" "))) {
           name.setText(currentText + Character.toUpperCase(a));
           evt.consume();
       }
    }//GEN-LAST:event_nameKeyTyped

    private void noKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noKeyTyped
        String currentText = no.getText();
     char a = evt.getKeyChar();

     if (!(Character.isDigit(a) && currentText.length() < 11)) {
         evt.consume();
     } else if (currentText.length() == 0 && a != '0') {
          JOptionPane.showMessageDialog(null,"Incorrect Format (09)");
         evt.consume();
     } else if (currentText.length() == 1 && currentText.charAt(0) != '0' && a != '9') {
          JOptionPane.showMessageDialog(null,"Incorrect Format (09)");
         evt.consume();
     } else if (currentText.length() == 2 && !(currentText.startsWith("09") && a != '0')) {
         JOptionPane.showMessageDialog(null,"Incorrect Format (09)");
         evt.consume();
     } else if (currentText.length() == 11) {
         if (!Character.isDigit(a)) {
              JOptionPane.showMessageDialog(null,"Incorrect Format");
             evt.consume();
         }
     }
    }//GEN-LAST:event_noKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
  try {
    // Check if all fields are filled
    if (id.getText().isEmpty() || name.getText().isEmpty() || age.getText().isEmpty() || add.getText().isEmpty() || no.getText().isEmpty()
            || email.getText().isEmpty() || kwarta.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter All Data!!");
    } else {
        // Check if the phone number is valid
        if (isValidNumber(no.getText())) {
            // Check if the email address is valid
            if (isValidEmail(email.getText())) {
                // Get the image from the JLabel
                Icon icon = photo.getIcon();
                
                // Check if an image is set on the JLabel
                if (icon != null) {
                    // Resize the image to desired dimensions
                    int desiredWidth = 200; // Adjust this as needed
                    int desiredHeight = 200; // Adjust this as needed

                    // Convert Icon to BufferedImage
                    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bufferedImage.createGraphics();
                    // Paint the Icon onto the BufferedImage
                    icon.paintIcon(null, g, 0, 0);
                    g.dispose();

                    // Resize the BufferedImage
                    Image scaledImage = bufferedImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

                    // Create a BufferedImage from the scaled image
                    BufferedImage bufferedImageResized = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = bufferedImageResized.createGraphics();
                    g2d.drawImage(scaledImage, 0, 0, null);
                    g2d.dispose();
                    
                    // Convert BufferedImage to byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageResized, "jpg", baos);
                    byte[] photoBytes = baos.toByteArray();

                    // Determine the sex
                    String sex;
                    if (m.isSelected()) {
                        sex = "Male";
                    } else if (f.isSelected()) {
                        sex = "Female";
                    } else {
                        sex = "";
                    }

                    // Prepare and execute the SQL statement to insert data
                    String b = "INSERT INTO `registration`(`RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance`, `Picture`) "
                            + "VALUES ('" + id.getText() + "','" + name.getText() + "','" + sex + "','" + age.getText() + "','" + add.getText() + "',"
                            + "'" + no.getText() + "','" + email.getText() + "','" + kwarta.getText() + "',?)";
                    pst = con.prepareStatement(b);
                    pst.setBytes(1, photoBytes);
                    pst.executeUpdate();

                    // Refresh the table data
                    String sql = "SELECT `RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance`, `Picture` FROM `registration` WHERE 1";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    tbl.setModel(DbUtils.resultSetToTableModel(rs));

                    // Clear input fields
                    id.setText("");
                    name.setText("");
                    email.setText("");
                    m.setSelected(false);
                    f.setSelected(false);
                    no.setText("");
                    kwarta.setText("");
                    age.setText("");
                    add.setText("");
                    photo.setIcon(null);
                } else {
                    JOptionPane.showMessageDialog(this, "No image selected.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email address. Email must end with '@gmail.com' or '@icloud.com'.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid number. Number must be exactly 11 digits.");
        }
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage());
}

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        File file = chooser.getSelectedFile();
        if (file != null) { 
            try {
                BufferedImage originalImage = ImageIO.read(file);
                int targetWidth = 200;
                int targetHeight = 200;
                Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                photo.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
    

    }//GEN-LAST:event_tblMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked

    }//GEN-LAST:event_searchMouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased

    }//GEN-LAST:event_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         DefaultTableModel model=(DefaultTableModel)tbl.getModel();
         TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
         tbl.setRowSorter(tr);
         tr.setRowFilter(RowFilter.regexFilter(search.getText()));
         tr.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(search.getText())));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void id1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id1KeyTyped

    }//GEN-LAST:event_id1KeyTyped

    private void amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyPressed

    }//GEN-LAST:event_amountKeyPressed

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped

    }//GEN-LAST:event_amountKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
  try {
    double update_amount = Double.parseDouble(balance.getText()) + Double.parseDouble(amount.getText());
    String q = "UPDATE `registration` SET `Name`='" + name1.getText() + "', `Sex`='" + sexx.getText() + "', `Age`='" + age1.getText() + "', "
             + "`Address`='" + add1.getText() + "', `Cellno`='" + no1.getText() + "', `Email`='" + email1.getText() + "', `Balance`='" + update_amount + "' "
             + "WHERE RFID='" + id1.getText() + "'";
    st = con.createStatement();
    st.executeUpdate(q);

    try {
        String q1 = "SELECT `RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance` FROM `registration` WHERE 1";
        rs = pst.executeQuery(q1);
        DefaultTableModel model = (DefaultTableModel)(tbl1.getModel());
        model.setRowCount(0);
        while(rs.next()){
            Object o [] = {
                rs.getString("RFID"), rs.getString("Name"), rs.getString("Sex"), rs.getInt("Age"),
                rs.getString("Address"), rs.getString("Cellno"), rs.getString("Email"), rs.getInt("Balance") // Changed getInt() to getDouble()
            };
            model.addRow(o);
            
        }
       
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    try {
                String selectQuery = "SELECT `RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance` FROM `registration` WHERE 1";
                rs = st.executeQuery(selectQuery);
                DefaultTableModel model = (DefaultTableModel) (tbl.getModel());
                model.setRowCount(0);
                
                while (rs.next()) {
                    Object o[] = {
                        rs.getString("RFID"), rs.getString("Name"), rs.getString("Sex"), rs.getInt("Age"),
                        rs.getString("Address"), rs.getString("Cellno"), rs.getString("Email"), rs.getInt("Balance")
                    };
                        model.addRow(o);
                    }

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
     try {
        String sql = "SELECT `RFID`, `Name`,`Balance` FROM `registration` WHERE 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        tbl1.setModel(DbUtils.resultSetToTableModel(rs));

     

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
      id1.setText("");
        name1.setText("");
        sexx.setText("");
        age1.setText("");
        add1.setText("");
        no1.setText("");
        email1.setText("");
        balance.setText("");
        amount.setText("");
} catch (SQLException ex) {
    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
}

    }//GEN-LAST:event_jButton7ActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
     try {
    int selectedRow = tbl1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) tbl1.getModel();

    id1.setText(model.getValueAt(selectedRow, 0).toString());
    name1.setText(model.getValueAt(selectedRow, 1).toString());
    balance.setText(model.getValueAt(selectedRow, 2).toString());

    String idFromDatabase = model.getValueAt(selectedRow, 0).toString();
    String sql = "SELECT `RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance` FROM `registration` WHERE RFID = ?";
    pst = con.prepareStatement(sql);
    pst.setString(1, idFromDatabase);
    rs = pst.executeQuery();

    if (rs.next()) {
        sexx.setText(rs.getString("Sex"));
        age1.setText(Integer.toString(rs.getInt("Age"))); // Convert int to String
        add1.setText(rs.getString("Address"));
        // Change the data type of Cellno to String or Long
        no1.setText(rs.getString("Cellno")); // Use getString() instead of getInt()
        email1.setText(rs.getString("Email"));
    }

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, ex);
}


    }//GEN-LAST:event_tbl1MouseClicked

    private void mItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mItemStateChanged
          if (m.isSelected()){
            f.setSelected(false);
          }
    }//GEN-LAST:event_mItemStateChanged

    private void fItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fItemStateChanged
        if (f.isSelected()){
            m.setSelected(false);
          }
    }//GEN-LAST:event_fItemStateChanged

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped
      String currentText = id.getText();
      char a = evt.getKeyChar();
      if (!(Character.isLetter(a) || Character.isDigit(a) || a == ' ' || a == ',' || (a == '.' && !currentText.contains(".")))) {
          evt.consume();
      } else if (Character.isLetter(a) && (currentText.isEmpty() || currentText.endsWith(" "))) {
          id.setText(currentText + Character.toUpperCase(a));
          evt.consume();
      } else if (Character.isDigit(a) && currentText.length() == 6) {
          evt.consume(); 
      }
    }//GEN-LAST:event_idKeyTyped

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
      allowedSymbols.add('.');
      allowedSymbols.add('@');
      allowedSymbols.add('_');     
      char a = evt.getKeyChar();
      if (allowedSymbols.contains(a) && email.getText().contains(String.valueOf(a))) {
           evt.consume(); 
          }
    }//GEN-LAST:event_emailKeyTyped

    private void kwartaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kwartaKeyTyped
          char a = evt.getKeyChar();

     if (!(Character.isDigit(a))) {
         evt.consume();
     }
    }//GEN-LAST:event_kwartaKeyTyped

    private void addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addKeyTyped
        String currentText = add.getText();
      char a = evt.getKeyChar();
      if (!(Character.isLetter(a) || Character.isDigit(a) || a == ' ' || a == ',' || (a == '.' && !currentText.contains(".")))) {
          evt.consume();
      } else if (Character.isLetter(a) && (currentText.isEmpty() || currentText.endsWith(" "))) {
          add.setText(currentText + Character.toUpperCase(a));
          evt.consume();
      } else if (Character.isDigit(a) && currentText.length() == 6) {
          evt.consume(); 
      }
    }//GEN-LAST:event_addKeyTyped

    private void balanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balanceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceKeyPressed

    private void balanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balanceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceKeyTyped

    private void age1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_age1ActionPerformed

    private void age1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_age1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_age1KeyTyped

    private void add1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_add1KeyTyped

    private void no1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_no1KeyTyped

    private void email1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_email1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_email1KeyTyped

    private void sexxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sexxKeyTyped

    private void tbl1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl1MouseEntered

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    register.setVisible(false);
    check.setVisible(false);
    top.setVisible(false);
    info.setVisible(true);
    tbl2.clearSelection();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl2MouseClicked

    }//GEN-LAST:event_tbl2MouseClicked

    private void search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search1MouseClicked

    }//GEN-LAST:event_search1MouseClicked

    private void search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyReleased

    }//GEN-LAST:event_search1KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultTableModel model=(DefaultTableModel)tbl.getModel();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
        tbl.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search.getText()));
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(search.getText())));
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add;
    private javax.swing.JTextField add1;
    private javax.swing.JTextField age;
    private javax.swing.JTextField age1;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField balance;
    private javax.swing.JPanel check;
    private javax.swing.JLabel date;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email1;
    private javax.swing.JRadioButton f;
    private javax.swing.JLabel id;
    private javax.swing.JTextField id1;
    private javax.swing.JPanel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kwarta;
    private javax.swing.JRadioButton m;
    private javax.swing.JTextField name;
    private javax.swing.JLabel name1;
    private javax.swing.JTextField no;
    private javax.swing.JTextField no1;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel photo1;
    private javax.swing.JPanel register;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search1;
    private javax.swing.JTextField sexx;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JLabel time;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables

private void Update_table() {
    try {
        String sql = "SELECT `RFID`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance` FROM `registration` WHERE 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tbl.setModel(DbUtils.resultSetToTableModel(rs));
        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    displaySelectedRowPhoto();
                }
            }
        });
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
private void Update_table1() {
    try {
        String sql = "SELECT * FROM `info_sales` WHERE 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tbl2.setModel(DbUtils.resultSetToTableModel(rs));
      
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
   private void displaySelectedRowPhoto() {
    int selectedRow = tbl.getSelectedRow();
    if (selectedRow != -1) {
        try {
            String selectedID = (String) tbl.getValueAt(selectedRow, 0);

            String photoQuery = "SELECT `Picture` FROM `registration` WHERE `RFID` = ?";
            pst = con.prepareStatement(photoQuery);
            pst.setString(1, selectedID);
            rs = pst.executeQuery();

            if (rs.next()) {
                Blob blob = (Blob) rs.getBlob("Picture");
                if (blob != null) {
                    byte[] photoBytes = blob.getBytes(1, (int) blob.length());
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(photoBytes));

                    if (bufferedImage != null) {
                        Image resizedImage = bufferedImage.getScaledInstance(134, 172, Image.SCALE_SMOOTH);

                        photo1.setIcon(new ImageIcon(resizedImage));
                        photo1.setText(null);
                        
                System.out.println("Image data length: " + photoBytes.length);
                    } else {
                        ImageIcon placeholderIcon = new ImageIcon("path/to/placeholder_image.png");
                        photo1.setIcon(placeholderIcon);
                        photo1.setText("No Photo Uploaded");
                        photo1.setHorizontalTextPosition(JLabel.CENTER);
                        photo1.setVerticalTextPosition(JLabel.CENTER);
                    }
                } else {
                    ImageIcon placeholderIcon = new ImageIcon("path/to/placeholder_image.png");
                    photo1.setIcon(placeholderIcon);
                    photo1.setText("No Photo Uploaded");
                    photo1.setHorizontalTextPosition(JLabel.CENTER);
                    photo1.setVerticalTextPosition(JLabel.CENTER);
                }
            } else {
                ImageIcon placeholderIcon = new ImageIcon("path/to/placeholder_image.png");
                photo1.setIcon(placeholderIcon);
                photo1.setText("No Photo Uploaded");
                photo1.setHorizontalTextPosition(JLabel.CENTER);
                photo1.setVerticalTextPosition(JLabel.CENTER);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}   
    private static boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") || email.endsWith("@icloud.com");
    }
    private boolean isValidNumber(String number) {
         return number.matches("\\d{11}");
     }
}
