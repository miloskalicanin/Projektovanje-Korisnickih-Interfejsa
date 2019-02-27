/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupacdesktop;

import java.util.List;
import javax.swing.JFrame;
import kupacdesktop.network.MockDatabaseService;
import kupacdesktop.network.dto.*;
import kupacdesktop.ui.*;

/**
 *
 * @author milos
 */
public class Navigator {

    private static UserDTO currentUser = null;

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        openWelcomeScreen(null);
    }

    public static void openWelcomeScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }
        new WelcomeScreen();
    }

    public static void openLoginScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }
        new LoginScreen();
    }

    public static void openRegistrationScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }
        new RegistrationScreen();
    }

    public static void logout(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }
        currentUser = null;

        openWelcomeScreen(null);
    }

    public static void finishLogin(JFrame currentFrame, UserDTO userDTO) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        currentUser = userDTO;

        openMainScreen(currentFrame);
    }

    public static void openMainScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new MainScreen();
    }

    public static void openChangeInfoScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new EditInfoScreen();
    }

    public static void openChangePasswordScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new EditPasswordScreen();
    }

    public static void displaySearchResult(JFrame currentFrame, List<UserDTO> repairmans) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new SearchResultScreen(repairmans);
    }

    public static void displayRepairmanInfo(JFrame currentFrame, UserDTO repairman) {
        /*if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }*/

        new RepairmanInfoScreen(repairman);
    }

    public static void displayCreateRequestScreen(JFrame currentFrame, UserDTO repairman) {
        /*if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }*/

        new CreateRequestScreen(repairman);
    }

    public static void openActiveRequestsScreen(JFrame currentFrame) {
        List<RequestInfoDTO> requests = MockDatabaseService.getInstance().getActiveRequests(getCurrentUser());
        if (requests == null || requests.isEmpty()) {
            Utils.displayErrorDialog(currentFrame, "Aktivni zahtevi", "Ne postoje aktivni zahtevi.");
        }

        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new ActiveRequestsScreen(requests);
    }

    public static void openActiveRequest(JFrame currentFrame, RequestInfoDTO requestInfo) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new ActiveRequestScreen(requestInfo);
    }

    public static void openFinishedRequestsScreen(JFrame currentFrame) {
        List<RequestInfoDTO> requests = MockDatabaseService.getInstance().getFinishedRequests(getCurrentUser());
        if (requests == null || requests.isEmpty()) {
            Utils.displayErrorDialog(currentFrame, "Zavrseni zahtevi", "Ne postoje zavrseni zahtevi.");
        }

        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new FinishedRequestsScreen(requests);
    }

    public static void openFinishedRequest(JFrame currentFrame, RequestInfoDTO requestInfo) {
        if (currentFrame != null) {
            currentFrame.dispose();
            currentFrame = null;
        }

        new FinishedRequestScreen(requestInfo);
    }
}
