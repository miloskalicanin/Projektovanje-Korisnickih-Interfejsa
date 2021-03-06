/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop.ui;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.text.PlainDocument;
import kupacdesktop.Navigator;
import kupacdesktop.Strings;
import kupacdesktop.TextfieldIntFilter;
import kupacdesktop.Utils;
import kupacdesktop.network.MockDatabaseService;
import kupacdesktop.network.dto.UserDTO;

/**
 *
 * @author milos
 */
public class RegistrationScreen extends javax.swing.JFrame {

    private static final String TAG = RegistrationScreen.class.getSimpleName();

    /**
     * Creates new form RegistrationScreen
     */
    public RegistrationScreen() {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));

        PlainDocument doc = (PlainDocument) inputPhone.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(true));
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
        jLabel2 = new javax.swing.JLabel();
        inputFirstName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputLastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inputPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        registration = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        backItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

        jLabel2.setText("Ime:");

        jLabel3.setText("Prezieme:");

        jLabel4.setText("Adresa:");

        jLabel5.setText("Telefon:");

        jLabel6.setText("Korisnicko ime:");

        jLabel7.setText("Lozinka:");

        registration.setBackground(new java.awt.Color(0, 0, 0));
        registration.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        registration.setForeground(new java.awt.Color(255, 255, 255));
        registration.setText("REGISTRACIJA");
        registration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationActionPerformed(evt);
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
                        .addGap(189, 189, 189)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(98, 98, 98)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inputUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                        .addComponent(inputPassword))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(registration, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(registration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationActionPerformed
        // registration
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        String address = inputAddress.getText();
        String phoneNumber = inputPhone.getText();
        String username = inputUsername.getText();
        String password = String.valueOf(inputPassword.getPassword());

        Utils.logDebugg(TAG, "First name: " + firstName);
        Utils.logDebugg(TAG, "Last name: " + lastName);
        Utils.logDebugg(TAG, "Address: " + address);
        Utils.logDebugg(TAG, "Phone number: " + phoneNumber);
        Utils.logDebugg(TAG, "Username: " + username);
        Utils.logDebugg(TAG, "Password: " + password);

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Utils.displayWarningDialog(RegistrationScreen.this, "Registracija", "Morate da popunite sva polja!");
            return;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setAddress(address);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        new RegistrationTask(userDTO).execute();
    }//GEN-LAST:event_registrationActionPerformed

    private void backItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backItemActionPerformed
        // navigation-back
        Navigator.openWelcomeScreen(this);
    }//GEN-LAST:event_backItemActionPerformed

    private class RegistrationTask extends SwingWorker<Void, String> {

        private UserDTO user;
        private boolean registered = false;

        public RegistrationTask(UserDTO user) {
            this.user = user;
        }

        @Override
        protected Void doInBackground() {
            MockDatabaseService mds = MockDatabaseService.getInstance();
            try {
                //Thread.sleep(200);
                mds.register(user);
                registered = true;
            } catch (Exception e) {
                Utils.logError(TAG, "Registration error", e);
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();

            if (!registered) {
                Utils.displayErrorDialog(RegistrationScreen.this, "Registracija", "Registracija nije uspesna.");
            } else {
                Utils.displaySuccessDialog(RegistrationScreen.this, "Registracija", "Registracija je uspesna!");
                Navigator.openWelcomeScreen(RegistrationScreen.this);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem backItem;
    private javax.swing.JTextField inputAddress;
    private javax.swing.JTextField inputFirstName;
    private javax.swing.JTextField inputLastName;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputPhone;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton registration;
    // End of variables declaration//GEN-END:variables
}
