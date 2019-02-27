/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class Utils {

    public static void logDebugg(String message) {
        System.out.println(message);
    }

    public static void logDebugg(String tag, String message) {
        System.out.println("D/" + tag + ":\t" + message);
    }

    public static void logError(String message) {
        System.err.println(message);
    }

    public static void logError(String tag, String message) {
        System.err.println("E/" + tag + ":\t" + message);
    }

    public static void logError(String tag, String message, Throwable t) {
        System.err.println("E/" + tag + ":\t" + message);
        t.printStackTrace();
    }

    public static void displayErrorDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void displayWarningDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void displaySuccessDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean displayYesNoScreen(Component parentComponent, String title, String message) {
        Object[] options = {"DA", "NE"};
        int dialogResult = JOptionPane.showOptionDialog(parentComponent,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if (dialogResult == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public static String inputTextDialog(Component parentComponent, String title, String message) {
        try {
            return JOptionPane.showInputDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            return null;
        }
    }
}
