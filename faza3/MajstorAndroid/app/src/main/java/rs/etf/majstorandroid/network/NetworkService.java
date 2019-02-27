package rs.etf.majstorandroid.network;

import android.app.Activity;

import java.util.List;

import rs.etf.majstorandroid.network.dto.RequestInfoDTO;
import rs.etf.majstorandroid.network.dto.SearchRequestsParametersDTO;
import rs.etf.majstorandroid.network.dto.UserDTO;

public interface NetworkService {
    List<String> getJobs();

    List<String> getPaymentMethods();

    UserDTO login(String username, String password) throws Exception;

    UserDTO register(UserDTO userDTO) throws Exception;

    void editUserData(UserDTO userDTO) throws Exception;

    void editPassword(UserDTO userDTO, String newPassword) throws Exception;

    List<RequestInfoDTO> search(Activity activity, SearchRequestsParametersDTO searchParameters, UserDTO user) throws Exception;

    List<RequestInfoDTO> getActiveRequests(UserDTO user) throws Exception;

    void realizeRequest(RequestInfoDTO request) throws Exception;

    void invalidateRequest(RequestInfoDTO request) throws Exception;
}
