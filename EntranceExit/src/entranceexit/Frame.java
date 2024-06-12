package entranceexit;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import trial101.UIDCallback;


public class Frame extends javax.swing.JFrame implements UIDCallback{
    Statement st;
    Connection con; 
    PreparedStatement pst; 
    ResultSet rs;
    public Frame() {
        initComponents();
        showDate();
        showTime();
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_parking?zeroDateTimeBehavior=CONVERT_TO_NULL ","root","");
            st = con.createStatement();
        } catch(Exception e) {
            System.out.print("No Database Detected");
        }
    }
      void showDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date4.setText(sdf.format(d));
    }

    void showTime() {
        new Timer(0,(ActionEvent ae)->{
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
            time4.setText(s.format(d));
        }).start();
    }
 

/*@Override
public void onRFIDReceived(String uid) {
    
    System.out.println(uid);
    
}*/
@Override
public void onUIDReceived(String uid) {
      System.out.println(uid);
    String existingId = id.getText();
    String existingId1 = id1.getText();

    if (existingId.isEmpty()) {
        if (uid.startsWith("Entrance: ")) {
            String status = uid.substring(10); // Extract the status part (after the prefix "1- ")
            
            // Fetch name and balance from the database
            try {
                String rfid = status; // Assuming status contains RFID
                String query = "SELECT `Name`, `Balance` FROM `registration` WHERE `RFID` = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, rfid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String namee = resultSet.getString("Name");
                    String balancee = resultSet.getString("Balance");
                    
                    if (!status.equals(existingId1)) {
                        id.setText(status);
                        datee.setText(date4.getText());
                        entranceT.setText(time4.getText());
                        name.setText(namee);
                        balance.setText(balancee);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: UID already has a record.");
                    }
                } else {
                    // UID not found in the database, display an error message or take appropriate action
                    JOptionPane.showMessageDialog(null, "UID not found in the database.");
                    //id.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
        if (uid.startsWith("Entrance: ")) {
            String status = uid.substring(10); // Extract the status part (after the prefix "1- ")
            
            // Fetch name and balance from the database
            try {
                String rfid = status; // Assuming status contains RFID
                String query = "SELECT `Name`, `Balance` FROM `registration` WHERE `RFID` = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, rfid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String balance = resultSet.getString("Balance");
                    
                    if (!status.equals(existingId)) {
                        id1.setText(status);
                        datee1.setText(date4.getText());
                        entranceT1.setText(time4.getText());
                        name1.setText(name);
                        balance1.setText(balance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: UID already has a record.");
                    }
                } else {
                    // UID not found in the database, display an error message or take appropriate action
                    JOptionPane.showMessageDialog(null, "UID not found in the database.");
                    //id1.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Handling exit
    if (uid.startsWith("Exit: ")) {
        String status = uid.substring(6);
        
        // Check if the entrance and exit UIDs are the same
        if (status.equals(existingId)) {
            exitT.setText(time4.getText());
            // Calculate the duration between entrance and exit
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date entranceTime = format.parse(entranceT.getText());
                Date exitTime = format.parse(time4.getText());
                long durationMillis = exitTime.getTime() - entranceTime.getTime();
                long durationMinutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis);
                
                if (durationMinutes >= 1) {
                    // Display payment label
                    long payment = durationMinutes * 10; // 10 for every minute
                    paid.setText(String.valueOf(payment));
                    long balanceee = Long.parseLong(balance.getText());
                    cbalance.setText(String.valueOf(balanceee - payment));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (status.equals(existingId1)) {
            exitT1.setText(time4.getText());
            // Calculate the duration between entrance and exit
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date entranceTime = format.parse(entranceT1.getText());
                Date exitTime = format.parse(time4.getText());
                long durationMillis = exitTime.getTime() - entranceTime.getTime();
                long durationMinutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis);
                
                if (durationMinutes >= 1) {
                    // Display payment label
                    long payment = durationMinutes * 10; // 10 for every minute
                    paid1.setText(String.valueOf(payment));
                    long balanceee = Long.parseLong(balance1.getText());
                   cbalance1.setText(String.valueOf(balanceee - payment));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
  
}





    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        date4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        time4 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        check = new javax.swing.JPanel();
        id = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        datee = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        entranceT = new javax.swing.JLabel();
        exitT = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        paid = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbalance = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        check1 = new javax.swing.JPanel();
        id1 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        balance1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        datee1 = new javax.swing.JLabel();
        entranceT1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        exitT1 = new javax.swing.JLabel();
        paid1 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cbalance1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(969, 687));
        jPanel9.setMinimumSize(new java.awt.Dimension(969, 687));
        jPanel9.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(null);

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DATE:");
        jPanel10.add(jLabel14);
        jLabel14.setBounds(540, 10, 90, 40);

        date4.setBackground(new java.awt.Color(0, 0, 0));
        date4.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        date4.setForeground(new java.awt.Color(0, 0, 0));
        date4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date4.setText("DATE");
        jPanel10.add(date4);
        date4.setBounds(580, 10, 190, 40);

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TIME:");
        jPanel10.add(jLabel15);
        jLabel15.setBounds(750, 10, 90, 40);

        time4.setBackground(new java.awt.Color(0, 0, 0));
        time4.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        time4.setForeground(new java.awt.Color(0, 0, 0));
        time4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time4.setText("TIME");
        jPanel10.add(time4);
        time4.setBounds(820, 10, 110, 40);

        jLabel39.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("AUTOMATED PARKING SYSTEM");
        jPanel10.add(jLabel39);
        jLabel39.setBounds(0, 0, 500, 60);

        jPanel9.add(jPanel10);
        jPanel10.setBounds(20, 20, 920, 62);

        check.setBackground(new java.awt.Color(255, 255, 255));
        check.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        check.setMaximumSize(new java.awt.Dimension(892, 480));
        check.setMinimumSize(new java.awt.Dimension(892, 480));
        check.setLayout(null);

        id.setBackground(new java.awt.Color(255, 255, 255));
        id.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        id.setForeground(new java.awt.Color(0, 0, 0));
        id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });
        check.add(id);
        id.setBounds(180, 20, 200, 40);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID:");
        check.add(jLabel12);
        jLabel12.setBounds(30, 20, 130, 40);

        name.setBackground(new java.awt.Color(255, 255, 255));
        name.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });
        check.add(name);
        name.setBounds(180, 80, 200, 40);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Name:");
        check.add(jLabel16);
        jLabel16.setBounds(30, 80, 130, 40);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Balance:");
        check.add(jLabel17);
        jLabel17.setBounds(30, 140, 130, 40);

        balance.setBackground(new java.awt.Color(255, 255, 255));
        balance.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        balance.setForeground(new java.awt.Color(0, 0, 0));
        balance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                balanceKeyTyped(evt);
            }
        });
        check.add(balance);
        balance.setBounds(180, 140, 200, 40);

        datee.setBackground(new java.awt.Color(255, 255, 255));
        datee.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        datee.setForeground(new java.awt.Color(0, 0, 0));
        datee.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        datee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dateeKeyTyped(evt);
            }
        });
        check.add(datee);
        datee.setBounds(180, 200, 200, 40);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Date:");
        check.add(jLabel18);
        jLabel18.setBounds(30, 200, 130, 40);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("SimSun-ExtB", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Entrance Time:");
        check.add(jLabel19);
        jLabel19.setBounds(30, 260, 130, 40);

        entranceT.setBackground(new java.awt.Color(255, 255, 255));
        entranceT.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        entranceT.setForeground(new java.awt.Color(0, 0, 0));
        entranceT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        entranceT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entranceTKeyTyped(evt);
            }
        });
        check.add(entranceT);
        entranceT.setBounds(180, 260, 200, 40);

        exitT.setBackground(new java.awt.Color(255, 255, 255));
        exitT.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        exitT.setForeground(new java.awt.Color(0, 0, 0));
        exitT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        exitT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                exitTKeyTyped(evt);
            }
        });
        check.add(exitT);
        exitT.setBounds(180, 320, 200, 40);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Exit Time:");
        check.add(jLabel20);
        jLabel20.setBounds(30, 320, 130, 40);

        paid.setBackground(new java.awt.Color(255, 255, 255));
        paid.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        paid.setForeground(new java.awt.Color(0, 0, 0));
        paid.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paidKeyTyped(evt);
            }
        });
        check.add(paid);
        paid.setBounds(180, 380, 200, 40);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Amount:");
        check.add(jLabel21);
        jLabel21.setBounds(30, 380, 130, 40);

        cbalance.setBackground(new java.awt.Color(255, 255, 255));
        cbalance.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        cbalance.setForeground(new java.awt.Color(0, 0, 0));
        cbalance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cbalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbalanceKeyTyped(evt);
            }
        });
        check.add(cbalance);
        cbalance.setBounds(180, 440, 200, 40);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("SimSun-ExtB", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Current Balance:");
        check.add(jLabel22);
        jLabel22.setBounds(30, 440, 130, 40);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("PRINT");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        check.add(jButton9);
        jButton9.setBounds(120, 510, 220, 40);

        jPanel9.add(check);
        check.setBounds(50, 150, 420, 570);

        check1.setBackground(new java.awt.Color(255, 255, 255));
        check1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        check1.setMaximumSize(new java.awt.Dimension(892, 480));
        check1.setMinimumSize(new java.awt.Dimension(892, 480));
        check1.setLayout(null);

        id1.setBackground(new java.awt.Color(255, 255, 255));
        id1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        id1.setForeground(new java.awt.Color(0, 0, 0));
        id1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        id1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                id1KeyTyped(evt);
            }
        });
        check1.add(id1);
        id1.setBounds(180, 20, 200, 40);

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("ID:");
        check1.add(jLabel31);
        jLabel31.setBounds(30, 20, 130, 40);

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Name:");
        check1.add(jLabel32);
        jLabel32.setBounds(30, 80, 130, 40);

        name1.setBackground(new java.awt.Color(255, 255, 255));
        name1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        name1.setForeground(new java.awt.Color(0, 0, 0));
        name1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                name1KeyTyped(evt);
            }
        });
        check1.add(name1);
        name1.setBounds(180, 80, 200, 40);

        balance1.setBackground(new java.awt.Color(255, 255, 255));
        balance1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        balance1.setForeground(new java.awt.Color(0, 0, 0));
        balance1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        balance1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                balance1KeyTyped(evt);
            }
        });
        check1.add(balance1);
        balance1.setBounds(180, 140, 200, 40);

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Balance:");
        check1.add(jLabel33);
        jLabel33.setBounds(30, 140, 130, 40);

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Date:");
        check1.add(jLabel34);
        jLabel34.setBounds(30, 200, 130, 40);

        datee1.setBackground(new java.awt.Color(255, 255, 255));
        datee1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        datee1.setForeground(new java.awt.Color(0, 0, 0));
        datee1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        datee1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datee1KeyTyped(evt);
            }
        });
        check1.add(datee1);
        datee1.setBounds(180, 200, 200, 40);

        entranceT1.setBackground(new java.awt.Color(255, 255, 255));
        entranceT1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        entranceT1.setForeground(new java.awt.Color(0, 0, 0));
        entranceT1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        entranceT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entranceT1KeyTyped(evt);
            }
        });
        check1.add(entranceT1);
        entranceT1.setBounds(180, 260, 200, 40);

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("SimSun-ExtB", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Entrance Time:");
        check1.add(jLabel35);
        jLabel35.setBounds(30, 260, 130, 40);

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Exit Time:");
        check1.add(jLabel36);
        jLabel36.setBounds(30, 320, 130, 40);

        exitT1.setBackground(new java.awt.Color(255, 255, 255));
        exitT1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        exitT1.setForeground(new java.awt.Color(0, 0, 0));
        exitT1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        exitT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                exitT1KeyTyped(evt);
            }
        });
        check1.add(exitT1);
        exitT1.setBounds(180, 320, 200, 40);

        paid1.setBackground(new java.awt.Color(255, 255, 255));
        paid1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        paid1.setForeground(new java.awt.Color(0, 0, 0));
        paid1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        paid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paid1KeyTyped(evt);
            }
        });
        check1.add(paid1);
        paid1.setBounds(180, 380, 200, 40);

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Amount:");
        check1.add(jLabel37);
        jLabel37.setBounds(30, 380, 130, 40);

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("SimSun-ExtB", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Current Balance:");
        check1.add(jLabel38);
        jLabel38.setBounds(30, 440, 130, 40);

        cbalance1.setBackground(new java.awt.Color(255, 255, 255));
        cbalance1.setFont(new java.awt.Font("Simplex_IV50", 0, 18)); // NOI18N
        cbalance1.setForeground(new java.awt.Color(0, 0, 0));
        cbalance1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cbalance1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbalance1KeyTyped(evt);
            }
        });
        check1.add(cbalance1);
        cbalance1.setBounds(180, 440, 200, 40);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("PRINT");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        check1.add(jButton8);
        jButton8.setBounds(100, 510, 220, 40);

        jPanel9.add(check1);
        check1.setBounds(510, 150, 420, 570);

        jLabel13.setFont(new java.awt.Font("SimSun-ExtB", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("SPOT 2");
        jPanel9.add(jLabel13);
        jLabel13.setBounds(510, 90, 420, 60);

        jLabel40.setFont(new java.awt.Font("SimSun-ExtB", 1, 30)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("SPOT 1");
        jPanel9.add(jLabel40);
        jLabel40.setBounds(50, 90, 420, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKeyTyped

    private void balanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balanceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceKeyTyped

    private void dateeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dateeKeyTyped

    private void entranceTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entranceTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_entranceTKeyTyped

    private void exitTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exitTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_exitTKeyTyped

    private void paidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_paidKeyTyped

    private void cbalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbalanceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cbalanceKeyTyped

    private void id1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_id1KeyTyped

    private void name1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_name1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_name1KeyTyped

    private void balance1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balance1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_balance1KeyTyped

    private void datee1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datee1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_datee1KeyTyped

    private void entranceT1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entranceT1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_entranceT1KeyTyped

    private void exitT1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exitT1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_exitT1KeyTyped

    private void paid1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_paid1KeyTyped

    private void cbalance1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbalance1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cbalance1KeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
                 try {
            // Check if all fields are filled
            if (id1.getText().isEmpty() || name1.getText().isEmpty() || balance1.getText().isEmpty() || datee1.getText().isEmpty() || entranceT1.getText().isEmpty()
                    || exitT1.getText().isEmpty() || paid1.getText().isEmpty() || cbalance1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter All Data!!");
            } else {

                // Calculate the new balance
                double currentBalance = Double.parseDouble(balance1.getText());
                double saleAmount = Double.parseDouble(paid1.getText());
                double updatedBalance = currentBalance - saleAmount;

                // Update balance in the registration table
                String updateQuery = "UPDATE `registration` SET `Balance` = ? WHERE `RFID` = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setDouble(1, updatedBalance);
                updateStatement.setString(2, id1.getText());
                updateStatement.executeUpdate();

                // Insert sales information into the info_sales table
                String insertQuery = "INSERT INTO `info_sales`(`ID`, `Name`, `Balance`, `Date`, `Entrance_Time`, `Exit_Time`, `Amount`, `Current_Balance`) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setString(1, id1.getText());
                insertStatement.setString(2, name1.getText());
                insertStatement.setDouble(3, Double.parseDouble(balance1.getText()));
                insertStatement.setString(4, datee1.getText()); // Use the formatted date
                insertStatement.setString(5, entranceT1.getText());
                insertStatement.setString(6, exitT1.getText());
                insertStatement.setDouble(7, saleAmount);
                insertStatement.setDouble(8, updatedBalance);
                insertStatement.executeUpdate();
                
                // Create a new PDF document
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("receipt1.pdf"));
                document.open();

                // Add receipt information
                document.add(new Paragraph("********************************************************"));
                document.add(new Paragraph("                      Receipt Information                     "));
                document.add(new Paragraph("********************************************************"));
                document.add(new Paragraph("ID: " + id1.getText()));
                document.add(new Paragraph("Name: " + name1.getText()));
                document.add(new Paragraph("Date: " + datee1.getText()));
                document.add(new Paragraph("----------------------------------------------------------"));
                document.add(new Paragraph("Entrance Time: " + entranceT1.getText()));
                document.add(new Paragraph("Exit Time: " + exitT1.getText()));
                document.add(new Paragraph("----------------------------------------------------------"));
                document.add(new Paragraph("Paid Amount: " + paid1.getText()));
                document.add(new Paragraph("Current Balance: " + updatedBalance));
                document.add(new Paragraph("----------------------------------------------------------"));
                double vatRate = 0.12; 
                double vatAmount = saleAmount * vatRate;
                double VatableAmm = saleAmount - vatAmount;
                document.add(new Paragraph("VATable Amount: " + VatableAmm));
                document.add(new Paragraph("VAT (12%): " + vatAmount));
                document.add(new Paragraph("********************************************************"));
                document.add(new Paragraph("             THANK YOU! PLEASE COME AGAIN!                     "));
                document.add(new Paragraph("********************************************************"));
                // Add message if current balance is 20
                if (updatedBalance <= 20) {
                document.add(new Paragraph("                      Please TOP-UP ASAP!                         "));
                }

                // Close the document
                document.close();
                writer.close(); // Close the PdfWriter
                // Clear input fields
                id1.setText("");
                name1.setText("");
                balance1.setText("");
                datee1.setText("");
                entranceT1.setText("");
                exitT1.setText("");
                paid1.setText("");
                cbalance1.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         try {
            // Check if all fields are filled
            if (id.getText().isEmpty() || name.getText().isEmpty() || balance.getText().isEmpty() || datee.getText().isEmpty() || entranceT.getText().isEmpty()
                    || exitT.getText().isEmpty() || paid.getText().isEmpty() || cbalance.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter All Data!!");
            } else {

                // Calculate the new balance
                double currentBalance = Double.parseDouble(balance.getText());
                double saleAmount = Double.parseDouble(paid.getText());
                double updatedBalance = currentBalance - saleAmount;

                // Update balance in the registration table
                String updateQuery = "UPDATE `registration` SET `Balance` = ? WHERE `RFID` = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setDouble(1, updatedBalance);
                updateStatement.setString(2, id.getText());
                updateStatement.executeUpdate();

                // Insert sales information into the info_sales table
                String insertQuery = "INSERT INTO `info_sales`(`ID`, `Name`, `Balance`, `Date`, `Entrance_Time`, `Exit_Time`, `Amount`, `Current_Balance`) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setString(1, id.getText());
                insertStatement.setString(2, name.getText());
                insertStatement.setDouble(3, Double.parseDouble(balance.getText()));
                insertStatement.setString(4, datee.getText()); // Use the formatted date
                insertStatement.setString(5, entranceT.getText());
                insertStatement.setString(6, exitT.getText());
                insertStatement.setDouble(7, saleAmount);
                insertStatement.setDouble(8, updatedBalance);
                insertStatement.executeUpdate();
                
     // Create a new PDF document
              Document document = new Document();
              PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("receipt.pdf"));
              document.open();

              // Add receipt information
              document.add(new Paragraph("********************************************************"));
              document.add(new Paragraph("                      Receipt Information                     "));
              document.add(new Paragraph("********************************************************"));
              document.add(new Paragraph("ID: " + id.getText()));
              document.add(new Paragraph("Name: " + name.getText()));
              document.add(new Paragraph("Date: " + datee.getText()));
              document.add(new Paragraph("----------------------------------------------------------"));
              document.add(new Paragraph("Entrance Time: " + entranceT.getText()));
              document.add(new Paragraph("Exit Time: " + exitT.getText()));
              document.add(new Paragraph("----------------------------------------------------------"));
              document.add(new Paragraph("Paid Amount: " + paid.getText()));
              document.add(new Paragraph("Current Balance: " + updatedBalance));
               document.add(new Paragraph("----------------------------------------------------------"));
               double vatRate = 0.12; 
            double vatAmount = saleAmount * vatRate;
            double VatableAmm = saleAmount - vatAmount;
              document.add(new Paragraph("VATable Amount: " + VatableAmm));
            document.add(new Paragraph("VAT (12%): " + vatAmount));
               document.add(new Paragraph("********************************************************"));
               document.add(new Paragraph("             THANK YOU! PLEASE COME AGAIN!                     "));
                document.add(new Paragraph("********************************************************"));
              // Add message if current balance is 20
              if (updatedBalance <= 20) {
              document.add(new Paragraph("                      Please TOP-UP ASAP!                         "));
              }

              // Close the document
              document.close();
              writer.close(); // Close the PdfWriter
                  // Clear input fields
                id.setText("");
                name.setText("");
                balance.setText("");
                datee.setText("");
                entranceT.setText("");
                exitT.setText("");
                paid.setText("");
                cbalance.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balance;
    private javax.swing.JLabel balance1;
    private javax.swing.JLabel cbalance;
    private javax.swing.JLabel cbalance1;
    private javax.swing.JPanel check;
    private javax.swing.JPanel check1;
    private javax.swing.JLabel date4;
    private javax.swing.JLabel datee;
    private javax.swing.JLabel datee1;
    private javax.swing.JLabel entranceT;
    private javax.swing.JLabel entranceT1;
    private javax.swing.JLabel exitT;
    private javax.swing.JLabel exitT1;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id1;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel paid;
    private javax.swing.JLabel paid1;
    private javax.swing.JLabel time4;
    // End of variables declaration//GEN-END:variables
}
