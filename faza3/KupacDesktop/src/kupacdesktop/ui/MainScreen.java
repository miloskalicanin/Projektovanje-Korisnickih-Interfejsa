/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop.ui;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.text.PlainDocument;
import kupacdesktop.Navigator;
import kupacdesktop.Strings;
import kupacdesktop.TextfieldIntFilter;
import kupacdesktop.Utils;
import kupacdesktop.network.MockDatabaseService;
import kupacdesktop.network.dto.SearchParametersDTO;
import kupacdesktop.network.dto.UserDTO;

/**
 *
 * @author milos
 */
public class MainScreen extends javax.swing.JFrame {

    private static final String TAG = MainScreen.class.getSimpleName();

    private List<String> jobs;

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        setTitle(Strings.APP_NAME);
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(0xa9a9a9));

        setupMenu();
        initJobs();
        initDatePickers();

        PlainDocument doc = (PlainDocument) inputPriceFrom.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));

        doc = (PlainDocument) inputPriceTo.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));

        doc = (PlainDocument) inputExpFrom.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));

        doc = (PlainDocument) inputExpTo.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));

        doc = (PlainDocument) inputRateFrom.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));

        doc = (PlainDocument) inputRateTo.getDocument();
        doc.setDocumentFilter(new TextfieldIntFilter(false));
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
        inputPriceTo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputJob = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        inputPriceFrom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateFrom = new javax.swing.JLabel();
        dateFrom1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputExpFrom = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputExpTo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inputRateFrom = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        inputRateTo = new javax.swing.JTextField();
        inputSpecialTechniques = new javax.swing.JCheckBox();
        inputUrgency = new javax.swing.JCheckBox();
        search = new javax.swing.JButton();
        inputDateEnd = new com.github.lgooddatepicker.components.DatePicker();
        inputDateBegin = new com.github.lgooddatepicker.components.DatePicker();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo-black-white(1).png"))); // NOI18N

        jLabel2.setText("Cena:");

        jLabel3.setText("Posao:");

        inputJob.setMaximumRowCount(10);
        inputJob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("-");

        jLabel6.setText("din");

        dateFrom.setText("Datum pocetka:");

        dateFrom1.setText("Datum kraja:");

        jLabel5.setText("Iskustvo:");

        jLabel7.setText("-");

        jLabel8.setText("god");

        jLabel9.setText("Ocene:");
        jLabel9.setToolTipText("");

        jLabel10.setText("-");

        inputSpecialTechniques.setBackground(new java.awt.Color(169, 169, 169));
        inputSpecialTechniques.setText("Specijalne tehnike");

        inputUrgency.setBackground(new java.awt.Color(169, 169, 169));
        inputUrgency.setText("Hitnost");

        search.setBackground(new java.awt.Color(0, 0, 0));
        search.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("PRETRAGA");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jMenu1.setText("Navigacija");

        mainScreen.setText("Glavni ekran");
        mainScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainScreenActionPerformed(evt);
            }
        });
        jMenu1.add(mainScreen);

        activeRequests.setText("Aktivni zahtevi");
        activeRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeRequestsActionPerformed(evt);
            }
        });
        jMenu1.add(activeRequests);

        finishedRequests.setText("Zavrseni zahtevi");
        finishedRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishedRequestsActionPerformed(evt);
            }
        });
        jMenu1.add(finishedRequests);
        jMenu1.add(jSeparator1);

        register.setText("Registracija");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        jMenu1.add(register);

        login.setText("Prijava");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jMenu1.add(login);
        jMenu1.add(jSeparator2);

        changeInfo.setText("Izmeni licne podatke");
        changeInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeInfoActionPerformed(evt);
            }
        });
        jMenu1.add(changeInfo);

        changePassword.setText("Promeni lozinku");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });
        jMenu1.add(changePassword);

        logout.setText("Odjavi se");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jMenu1.add(logout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(jLabel5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dateFrom1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inputDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(156, 156, 156)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(inputExpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(inputExpTo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel8))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(inputRateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(inputRateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(inputSpecialTechniques)
                                        .addComponent(inputUrgency)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(83, 83, 83)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateFrom)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(inputJob, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(inputPriceFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(inputPriceTo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(inputDateBegin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(213, 213, 213))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPriceFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPriceTo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFrom)
                    .addComponent(inputDateBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFrom1)
                    .addComponent(inputDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputExpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(inputExpTo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputRateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputRateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(inputSpecialTechniques)
                .addGap(18, 18, 18)
                .addComponent(inputUrgency)
                .addGap(48, 48, 48)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainScreenActionPerformed
        Navigator.openMainScreen(this);
    }//GEN-LAST:event_mainScreenActionPerformed

    private void activeRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeRequestsActionPerformed
        Navigator.openActiveRequestsScreen(this);
    }//GEN-LAST:event_activeRequestsActionPerformed

    private void finishedRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishedRequestsActionPerformed
        Navigator.openFinishedRequestsScreen(this);
    }//GEN-LAST:event_finishedRequestsActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        Navigator.openRegistrationScreen(this);
    }//GEN-LAST:event_registerActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Navigator.openLoginScreen(this);
    }//GEN-LAST:event_loginActionPerformed

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

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        String job = jobs.get(inputJob.getSelectedIndex());
        String priceFrom = inputPriceFrom.getText();
        String priceTo = inputPriceTo.getText();
        LocalDate dateBegin = inputDateBegin.getDate();
        LocalDate dateEnd = inputDateEnd.getDate();
        String experienceFrom = inputExpFrom.getText();
        String experienceTo = inputExpTo.getText();
        String ratingFrom = inputRateFrom.getText();
        String ratingTo = inputRateTo.getText();
        boolean specialTechniques = inputSpecialTechniques.isSelected();
        boolean urgency = inputUrgency.isSelected();

        SearchParametersDTO searchParameters = new SearchParametersDTO();
        if (job != null && !job.isEmpty()) {
            searchParameters.setJob(job);
        }

        if (priceFrom != null && !priceFrom.isEmpty()) {
            searchParameters.setPriceFrom(priceFrom);
        }

        if (priceTo != null && !priceTo.isEmpty()) {
            searchParameters.setPriceTo(priceTo);
        }

        if (dateBegin != null) {
            searchParameters.setDateBegin(Date.from(dateBegin.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dateEnd != null) {
            searchParameters.setDateEnd(Date.from(dateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (experienceFrom != null && !experienceFrom.isEmpty()) {
            searchParameters.setExperienceFrom(experienceFrom);
        }

        if (experienceTo != null && !experienceTo.isEmpty()) {
            searchParameters.setExperienceTo(experienceTo);
        }

        if (ratingFrom != null && !ratingFrom.isEmpty()) {
            searchParameters.setRatingFrom(ratingFrom);
        }

        if (ratingTo != null && !ratingTo.isEmpty()) {
            searchParameters.setRatingTo(ratingTo);
        }

        searchParameters.setSpecialTechniques(specialTechniques);
        searchParameters.setUrgency(urgency);

        Utils.logDebugg(TAG, "Search parameters: " + searchParameters);

        new SearchTask(searchParameters).execute();
    }//GEN-LAST:event_searchActionPerformed

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

    private void initJobs() {
        jobs = MockDatabaseService.getInstance().getJobs();
        jobs.add(0, "svi poslovi");

        String[] jobsArray = jobs.toArray(new String[jobs.size()]);

        DefaultComboBoxModel model = new DefaultComboBoxModel(jobsArray);
        inputJob.setModel(model);
    }

    private void initDatePickers() {
        Locale locale = new Locale("sr", "RS");
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dateSettings.setLocale(locale);
        dateSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        dateSettings.setAllowEmptyDates(true);
        inputDateBegin.setSettings(dateSettings);
        //inputDateBegin.setDateToToday();
        //dateSettings.setVetoPolicy(new SampleDateVetoPolicy());

        dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dateSettings.setLocale(locale);
        dateSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        dateSettings.setAllowEmptyDates(true);
        inputDateEnd.setSettings(dateSettings);
        //inputDateEnd.setDateToToday();
        dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
    }

    private static class SampleDateVetoPolicy implements DateVetoPolicy {

        /**
         * isDateAllowed, Return true if a date should be allowed, or false if a
         * date should be vetoed.
         */
        @Override
        public boolean isDateAllowed(LocalDate date) {
            // Allow only time in future.
            if (date.isBefore(LocalDate.now())) {
                return false;
            }

            // Allow all other days.
            return true;
        }
    }

    private class SearchTask extends SwingWorker<Void, String> {

        private SearchParametersDTO searchParameters;
        private List<UserDTO> repairmans = null;

        public SearchTask(SearchParametersDTO searchParameters) {
            this.searchParameters = searchParameters;
        }

        @Override
        protected Void doInBackground() {
            MockDatabaseService mds = MockDatabaseService.getInstance();
            try {
                //Thread.sleep(200);
                repairmans = mds.search(searchParameters);
            } catch (Exception e) {
                Utils.logError(TAG, "Search error", e);
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();

            if (repairmans == null) {
                Utils.displayErrorDialog(MainScreen.this, "Pretraga", "Pretraga nije uspesna.");
            } else if (repairmans.isEmpty()) {
                Utils.displayErrorDialog(MainScreen.this, "Pretraga", "Ne postoje majstori koji zadovoljavaju kriterijum pretrage.");
            } else {
                Navigator.displaySearchResult(MainScreen.this, repairmans);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem activeRequests;
    private javax.swing.JMenuItem changeInfo;
    private javax.swing.JMenuItem changePassword;
    private javax.swing.JLabel dateFrom;
    private javax.swing.JLabel dateFrom1;
    private javax.swing.JMenuItem finishedRequests;
    private com.github.lgooddatepicker.components.DatePicker inputDateBegin;
    private com.github.lgooddatepicker.components.DatePicker inputDateEnd;
    private javax.swing.JTextField inputExpFrom;
    private javax.swing.JTextField inputExpTo;
    private javax.swing.JComboBox<String> inputJob;
    private javax.swing.JTextField inputPriceFrom;
    private javax.swing.JTextField inputPriceTo;
    private javax.swing.JTextField inputRateFrom;
    private javax.swing.JTextField inputRateTo;
    private javax.swing.JCheckBox inputSpecialTechniques;
    private javax.swing.JCheckBox inputUrgency;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem mainScreen;
    private javax.swing.JMenuItem register;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables
}