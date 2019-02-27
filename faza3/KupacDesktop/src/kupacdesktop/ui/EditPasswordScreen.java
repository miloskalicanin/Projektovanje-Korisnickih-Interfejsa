/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop.ui;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import kupacdesktop.Navigator;
import kupacdesktop.Strings;
import kupacdesktop.Utils;
import kupacdesktop.network.MockDatabaseService;
import kupacdesktop.network.dto.UserDTO;

/**
 *
 * @author milos
 */
public class EditPasswordScreen extends javax.swing.JFrame {

    private static final String TAG = EditPasswordScreen.class.getSimpleName();

    /**
     * Creates new form EditInfoScreen
     */
    public EditPasswordScreen() {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));

        UserDTO currentUser = Navigator.getCurrentUser();
        inputUsername.setText(currentUser.getUsername());
        inputUsername.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        change = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        inputNewPassword2 = new javax.swing.JPasswordField();
        inputNewPassword1 = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Navigacija = new javax.swing.JMenu();
        mainScreen = new javax.swing.JMenuItem();
        activeRequests = new javax.swing.JMenuItem();
        finishedRequests = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        changeInfo = new javax.swing.JMenuItem();
        changePassword = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Nova lozinka:");

        jLabel4.setText("Nova lozinka:");

        jLabel3.setText("Lozinka:");

        jLabel2.setText("Korisnicko ime:");

        change.setBackground(new java.awt.Color(0, 0, 0));
        change.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        change.setForeground(new java.awt.Color(255, 255, 255));
        change.setText("IZMENI");
        change.setToolTipText("");
        change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

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
                        .addGap(239, 239, 239)
                        .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputNewPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputNewPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inputNewPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputNewPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeActionPerformed
        // registration
        String username = inputUsername.getText();
        String password = String.valueOf(inputPassword.getPassword());
        String newPassword1 = String.valueOf(inputNewPassword1.getPassword());
        String newPassword2 = String.valueOf(inputNewPassword2.getPassword());

        Utils.logDebugg(TAG, "Username: " + username);
        Utils.logDebugg(TAG, "Password: " + password);
        Utils.logDebugg(TAG, "New password 1: " + newPassword1);
        Utils.logDebugg(TAG, "New password 2: " + newPassword2);

        if (username.isEmpty() || password.isEmpty() || newPassword1.isEmpty() || newPassword2.isEmpty()) {
            Utils.displayWarningDialog(EditPasswordScreen.this, "Izmena lozinke", "Morate da popunite sva polja!");
            return;
        }

        if (!newPassword1.equals(newPassword2)) {
            Utils.displayWarningDialog(EditPasswordScreen.this, "Izmena lozinke", "Nova lozinka se ne poklapa!");
            return;
        }

        UserDTO currentUser = Navigator.getCurrentUser();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(currentUser.getFirstName());
        userDTO.setLastName(currentUser.getLastName());
        userDTO.setAddress(currentUser.getAddress());
        userDTO.setPhoneNumber(currentUser.getPhoneNumber());
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setType(currentUser.getType());

        new EditPasswordTask(userDTO, newPassword1).execute();
    }//GEN-LAST:event_changeActionPerformed

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

    private class EditPasswordTask extends SwingWorker<Void, String> {

        private UserDTO user;
        private String newPassword;
        private boolean edited = false;

        public EditPasswordTask(UserDTO user, String newPassword) {
            this.user = user;
        }

        @Override
        protected Void doInBackground() {
            MockDatabaseService mds = MockDatabaseService.getInstance();
            try {
                //Thread.sleep(200);
                mds.editPassword(user, newPassword);
                edited = true;
            } catch (Exception e) {
                Utils.logError(TAG, "Edit password error", e);
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();

            if (!edited) {
                Utils.displayErrorDialog(EditPasswordScreen.this, "Izmena lozinke", "Izmena lozinke nije uspesna.");
            } else {
                UserDTO currentUser = Navigator.getCurrentUser();
                currentUser.setPassword(newPassword);
                Utils.displaySuccessDialog(EditPasswordScreen.this, "Izmena lozinke", "Izmena lozinke je uspesna!");
                Navigator.openMainScreen(EditPasswordScreen.this);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Navigacija;
    private javax.swing.JMenuItem activeRequests;
    private javax.swing.JButton change;
    private javax.swing.JMenuItem changeInfo;
    private javax.swing.JMenuItem changePassword;
    private javax.swing.JMenuItem finishedRequests;
    private javax.swing.JPasswordField inputNewPassword1;
    private javax.swing.JPasswordField inputNewPassword2;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem mainScreen;
    // End of variables declaration//GEN-END:variables
}