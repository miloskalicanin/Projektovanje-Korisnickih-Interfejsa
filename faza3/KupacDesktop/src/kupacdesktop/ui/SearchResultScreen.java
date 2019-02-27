/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import kupacdesktop.Navigator;
import kupacdesktop.Strings;
import kupacdesktop.Utils;
import kupacdesktop.network.dto.UserDTO;

/**
 *
 * @author milos
 */
public class SearchResultScreen extends javax.swing.JFrame {

    private static final String TAG = SearchResultScreen.class.getSimpleName();

    private static List<UserDTO> repairmans;

    /**
     * Creates new form EditInfoScreen
     */
    public SearchResultScreen(List<UserDTO> repairmans) {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));

        SearchResultScreen.repairmans = repairmans;

        setupMenu();
        displayResults();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        resultPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Navigacija = new javax.swing.JMenu();
        mainScreen = new javax.swing.JMenuItem();
        activeRequests = new javax.swing.JMenuItem();
        changeInfo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        register = new javax.swing.JMenuItem();
        login = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        finishedRequests = new javax.swing.JMenuItem();
        changePassword = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        scrollPane1.add(resultPanel);

        jLabel2.setText("Ime i prezime");

        jLabel3.setText("Prosecna ocena");

        Navigacija.setText("Navigacija");

        mainScreen.setText("Glavni ekran");
        mainScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainScreenActionPerformed(evt);
            }
        });
        Navigacija.add(mainScreen);

        activeRequests.setText("Aktivni zahtevi");
        activeRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeRequestsActionPerformed(evt);
            }
        });
        Navigacija.add(activeRequests);

        changeInfo.setText("Izmeni licne podatke");
        changeInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeInfoActionPerformed(evt);
            }
        });
        Navigacija.add(changeInfo);
        Navigacija.add(jSeparator1);

        register.setText("Registracija");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        Navigacija.add(register);

        login.setText("Prijava");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        Navigacija.add(login);
        Navigacija.add(jSeparator2);

        finishedRequests.setText("Zavrseni zahtevi");
        finishedRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishedRequestsActionPerformed(evt);
            }
        });
        Navigacija.add(finishedRequests);

        changePassword.setText("Promeni lozinku");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });
        Navigacija.add(changePassword);

        logout.setText("Odjavi se");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        Navigacija.add(logout);

        jMenuBar1.add(Navigacija);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel3))
                            .addComponent(jLabel1))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        if (Utils.displayYesNoScreen(this, "Odjava", "Da li ste sigurni da zelite da se odjavite?")) {
            Navigator.logout(this);
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        Navigator.openChangePasswordScreen(this);
    }//GEN-LAST:event_changePasswordActionPerformed

    private void changeInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeInfoActionPerformed
        Navigator.openChangeInfoScreen(this);
    }//GEN-LAST:event_changeInfoActionPerformed

    private void finishedRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishedRequestsActionPerformed
        Navigator.openFinishedRequestsScreen(this);
    }//GEN-LAST:event_finishedRequestsActionPerformed

    private void activeRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeRequestsActionPerformed
        Navigator.openActiveRequestsScreen(this);
    }//GEN-LAST:event_activeRequestsActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        Navigator.openRegistrationScreen(this);
    }//GEN-LAST:event_registerActionPerformed

    private void mainScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainScreenActionPerformed
        Navigator.openMainScreen(this);
    }//GEN-LAST:event_mainScreenActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Navigator.openLoginScreen(this);
    }//GEN-LAST:event_loginActionPerformed

    private void displayResults() {
        resultPanel.setLayout(new GridLayout(repairmans.size(), 3));

        for (UserDTO user : repairmans) {
            JLabel label = new JLabel(user.getFirstName() + " " + user.getLastName(), JLabel.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            resultPanel.add(label);

            String rating = String.format("%.2f", user.getRating());
            label = new JLabel(rating, JLabel.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            resultPanel.add(label);

            label = new JLabel("Detaljnije", JLabel.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            resultPanel.add(label);

            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    moreInfo(user);
                }
            });
        }
    }

    private void setupMenu() {
        if (Navigator.getCurrentUser() != null) {
            // logged in user
            register.setVisible(false);
            login.setVisible(false);
            jSeparator2.setVisible(false);
        } else {
            // guest
            jSeparator2.setVisible(false);
            changeInfo.setVisible(false);
            changePassword.setVisible(false);
            activeRequests.setVisible(false);
            finishedRequests.setVisible(false);
            logout.setVisible(false);
        }
    }

    private void moreInfo(UserDTO user) {
        Utils.logDebugg("Info for: " + user.getFirstName());
        Navigator.displayRepairmanInfo(this, user);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Navigacija;
    private javax.swing.JMenuItem activeRequests;
    private javax.swing.JMenuItem changeInfo;
    private javax.swing.JMenuItem changePassword;
    private javax.swing.JMenuItem finishedRequests;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem mainScreen;
    private javax.swing.JMenuItem register;
    private javax.swing.JPanel resultPanel;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
