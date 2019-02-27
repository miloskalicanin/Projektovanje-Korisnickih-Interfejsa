/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop.ui;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import kupacdesktop.Navigator;
import kupacdesktop.Strings;
import kupacdesktop.Utils;
import kupacdesktop.network.MockDatabaseService;
import kupacdesktop.network.dto.CommentDTO;
import kupacdesktop.network.dto.ScoreDTO;
import kupacdesktop.network.dto.UserDTO;

/**
 *
 * @author milos
 */
public class RepairmanInfoScreen extends javax.swing.JFrame {

    private static final String TAG = RepairmanInfoScreen.class.getSimpleName();

    private static UserDTO repairman;

    /**
     * Creates new form EditInfoScreen
     */
    public RepairmanInfoScreen(UserDTO repairman) {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));

        RepairmanInfoScreen.repairman = repairman;

        setupMenu();
        setupScreen();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        createRequest = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelFirstAndLastName = new javax.swing.JLabel();
        labelJob = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        labelAddress = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelPrice = new javax.swing.JLabel();
        labelRating = new javax.swing.JLabel();
        labelExperience = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        specTechPanel = new javax.swing.JPanel();
        share = new javax.swing.JButton();
        seeRating = new javax.swing.JButton();
        seeComments = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Navigacija = new javax.swing.JMenu();
        mainScreen = new javax.swing.JMenuItem();
        activeRequests = new javax.swing.JMenuItem();
        finishedRequests = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        register = new javax.swing.JMenuItem();
        login = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        changeInfo = new javax.swing.JMenuItem();
        changePassword = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Telefon:");

        jLabel4.setText("Adresa:");

        jLabel3.setText("Posao:");

        jLabel2.setText("Ime i prezime:");

        createRequest.setBackground(new java.awt.Color(0, 0, 0));
        createRequest.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        createRequest.setForeground(new java.awt.Color(255, 255, 255));
        createRequest.setText("KREIRAJ ZAHTEV");
        createRequest.setToolTipText("");
        createRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRequestActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

        jLabel6.setText("Cena:");

        labelFirstAndLastName.setText("Petar Petrovic");

        labelJob.setText("moler");

        labelPhone.setText("0641234567");

        labelAddress.setText("Takovska 1");

        jLabel11.setText("Prosecna ocena:");

        jLabel12.setText("Iskustvo:");

        jLabel13.setText("Specijalne tehnike:");

        labelPrice.setText("1200 din");

        labelRating.setText("4.56");

        labelExperience.setText("7 godina");

        javax.swing.GroupLayout specTechPanelLayout = new javax.swing.GroupLayout(specTechPanel);
        specTechPanel.setLayout(specTechPanelLayout);
        specTechPanelLayout.setHorizontalGroup(
            specTechPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        specTechPanelLayout.setVerticalGroup(
            specTechPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        scrollPane1.add(specTechPanel);

        share.setBackground(new java.awt.Color(153, 153, 153));
        share.setForeground(new java.awt.Color(255, 255, 255));
        share.setText("Podeli");
        share.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareActionPerformed(evt);
            }
        });

        seeRating.setBackground(new java.awt.Color(153, 153, 153));
        seeRating.setForeground(new java.awt.Color(255, 255, 255));
        seeRating.setText("Pogledaj ocene");
        seeRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeRatingActionPerformed(evt);
            }
        });

        seeComments.setBackground(new java.awt.Color(153, 153, 153));
        seeComments.setForeground(new java.awt.Color(255, 255, 255));
        seeComments.setText("Pogledaj komentare");
        seeComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeCommentsActionPerformed(evt);
            }
        });

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

        finishedRequests.setText("Zavrseni zahtevi");
        finishedRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishedRequestsActionPerformed(evt);
            }
        });
        Navigacija.add(finishedRequests);
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

        changeInfo.setText("Izmeni licne podatke");
        changeInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeInfoActionPerformed(evt);
            }
        });
        Navigacija.add(changeInfo);

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
                        .addGap(204, 204, 204)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(createRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(seeRating)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelJob)
                                .addComponent(labelFirstAndLastName)
                                .addComponent(labelAddress)
                                .addComponent(labelPhone)
                                .addComponent(labelPrice)
                                .addComponent(labelExperience))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelRating)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                            .addComponent(seeComments)
                            .addGap(43, 43, 43)))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(share)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(share, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelFirstAndLastName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelJob))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelAddress))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(labelPhone))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(labelPrice))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(seeComments, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelRating))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(labelExperience))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(createRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRequestActionPerformed
        if (Navigator.getCurrentUser() == null) {
            Utils.displayWarningDialog(this, "Kreiranje zahteva", "Morate biti prijavljeni da biste kreirali zahtev.");
            return;
        }

        Navigator.displayCreateRequestScreen(this, repairman);
    }//GEN-LAST:event_createRequestActionPerformed

    private void seeRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeRatingActionPerformed
        try {
            List<ScoreDTO> scores = MockDatabaseService.getInstance().getAllScores(repairman);
            if (scores == null || scores.isEmpty()) {
                throw new Exception("Empty");
            }
            List<String> items = new ArrayList<>(scores.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
            for (ScoreDTO score : scores) {
                String dateString = format.format(score.getDate());
                String item = dateString + " - " + String.format("%.2f", score.getScore());
                items.add(item);
            }
            JList list = new JList(items.toArray(new String[items.size()]));
            ListDialog dialog = new ListDialog("Ocene", "Ocene:", list);
            dialog.show();
        } catch (Exception ex) {
            Utils.displayErrorDialog(this, "Ocene", "Nema ocena za prikaz.");
        }
    }//GEN-LAST:event_seeRatingActionPerformed

    private void seeCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeCommentsActionPerformed
        try {
            List<CommentDTO> comments = MockDatabaseService.getInstance().getAllComments(repairman);
            if (comments == null || comments.isEmpty()) {
                throw new Exception("Empty");
            }
            List<String> items = new ArrayList<>(comments.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
            for (CommentDTO comment : comments) {
                String dateString = format.format(comment.getDate());
                String item = dateString + " " + comment.getComment();
                items.add(item);
            }
            JList list = new JList(items.toArray(new String[items.size()]));
            ListDialog dialog = new ListDialog("Komentari", "Komentari:", list);
            dialog.show();
        } catch (Exception ex) {
            Utils.displayErrorDialog(this, "Komentari", "Nema komentara za prikaz.");
        }
    }//GEN-LAST:event_seeCommentsActionPerformed

    private void shareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shareActionPerformed
        try {
            openWebpage(new URL("https://www.facebook.com/"));
        } catch (Exception ex) {
            Utils.logError(TAG, "Failed to open url", ex);
        }
    }//GEN-LAST:event_shareActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        Navigator.openRegistrationScreen(this);
    }//GEN-LAST:event_registerActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Navigator.openLoginScreen(this);
    }//GEN-LAST:event_loginActionPerformed

    private void mainScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainScreenActionPerformed
        Navigator.openMainScreen(this);
    }//GEN-LAST:event_mainScreenActionPerformed

    private void activeRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeRequestsActionPerformed
        Navigator.openActiveRequestsScreen(this);
    }//GEN-LAST:event_activeRequestsActionPerformed

    private void finishedRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishedRequestsActionPerformed
        Navigator.openFinishedRequestsScreen(this);
    }//GEN-LAST:event_finishedRequestsActionPerformed

    private void changeInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeInfoActionPerformed
        Navigator.openChangeInfoScreen(this);
    }//GEN-LAST:event_changeInfoActionPerformed

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        Navigator.openChangePasswordScreen(this);
    }//GEN-LAST:event_changePasswordActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        if (Utils.displayYesNoScreen(this, "Odjava", "Da li ste sigurni da zelite da se odjavite?")) {
            Navigator.logout(this);
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void setupScreen() {
        labelFirstAndLastName.setText(repairman.getFirstName() + " " + repairman.getLastName());
        labelAddress.setText(repairman.getAddress());
        labelPhone.setText(repairman.getPhoneNumber());
        labelExperience.setText(repairman.getExperience() + " godina");
        labelPrice.setText(repairman.getPrice() + " din");
        labelJob.setText(repairman.getJob());
        labelRating.setText(String.format("%.2f", repairman.getRating()));

        if (repairman.getSpecialTechniques() != null) {
            List<String> techniques = new LinkedList<>();
            for (String t : repairman.getSpecialTechniques()) {
                String[] arr = t.split(",");
                for (String tt : arr) {
                    techniques.add(tt.replaceAll(" ", ""));
                }
            }
            specTechPanel.setLayout(new GridLayout(techniques.size(), 1));
            for (String technique : techniques) {
                JLabel label = new JLabel(technique, JLabel.CENTER);
                //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                specTechPanel.add(label);
            }
        } else {
            specTechPanel.setLayout(new GridLayout());
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

    private boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                Utils.logError(TAG, "Network error", e);
            }
        }
        return false;
    }

    private boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (Exception e) {
            Utils.logError(TAG, "Network error", e);
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Navigacija;
    private javax.swing.JMenuItem activeRequests;
    private javax.swing.JMenuItem changeInfo;
    private javax.swing.JMenuItem changePassword;
    private javax.swing.JButton createRequest;
    private javax.swing.JMenuItem finishedRequests;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelExperience;
    private javax.swing.JLabel labelFirstAndLastName;
    private javax.swing.JLabel labelJob;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelRating;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem mainScreen;
    private javax.swing.JMenuItem register;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton seeComments;
    private javax.swing.JButton seeRating;
    private javax.swing.JButton share;
    private javax.swing.JPanel specTechPanel;
    // End of variables declaration//GEN-END:variables
}
