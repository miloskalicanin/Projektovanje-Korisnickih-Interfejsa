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
public class LoginScreen extends javax.swing.JFrame {

    private static final String TAG = LoginScreen.class.getSimpleName();

    /**
     * Creates new form LoginScreen
     */
    public LoginScreen() {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));
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
        jLabel6 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        backItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

        jLabel6.setText("Korisnicko ime:");

        jLabel7.setText("Lozinka:");

        login.setBackground(new java.awt.Color(0, 0, 0));
        login.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("PRIJAVA");
        login.setToolTipText("");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jMenu1.setText("Navigacija");

        backItem.setText("Nazad");
        backItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backItemActionPerformed(evt);
            }
        });
        jMenu1.add(backItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(42, 42, 42)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // registration
        String username = inputUsername.getText();
        String password = String.valueOf(inputPassword.getPassword());

        Utils.logDebugg(TAG, "Username: " + username);
        Utils.logDebugg(TAG, "Password: " + password);

        if (username.isEmpty() || password.isEmpty()) {
            Utils.displayWarningDialog(LoginScreen.this, "Prijava", "Morate da popunite sva polja!");
            return;
        }

        new LoginTask(username, password).execute();
    }//GEN-LAST:event_loginActionPerformed

    private void backItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backItemActionPerformed
        // navigation-back
        Navigator.openWelcomeScreen(this);
    }//GEN-LAST:event_backItemActionPerformed

    private class LoginTask extends SwingWorker<Void, String> {

        private String username;
        private String password;
        private UserDTO user = null;
        private boolean loggedIn = false;

        public LoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground() {
            MockDatabaseService mds = MockDatabaseService.getInstance();
            try {
                //Thread.sleep(200);
                user = mds.login(username, password);
                loggedIn = true;
            } catch (Exception e) {
                Utils.logError(TAG, "Login error", e);
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();

            if (!loggedIn) {
                Utils.displayErrorDialog(LoginScreen.this, "Prijava", "Prijava nije uspesna.");
            } else {
                //Utils.displaySuccessDialog(LoginScreen.this, "Prijava", "Prijava je uspesna!");
                Navigator.finishLogin(LoginScreen.this, user);
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem backItem;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton login;
    // End of variables declaration//GEN-END:variables
}
