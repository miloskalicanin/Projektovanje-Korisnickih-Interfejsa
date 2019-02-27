package kupacdesktop.network;

import java.util.List;
import kupacdesktop.network.dto.*;

public interface NetworkService {

    List<String> getJobs();

    List<String> getPaymentMethods();

    UserDTO login(String username, String password) throws Exception;

    UserDTO register(UserDTO userDTO) throws Exception;

    void editUserData(UserDTO userDTO) throws Exception;

    void editPassword(UserDTO userDTO, String newPassword) throws Exception;

    List<UserDTO> search(SearchParametersDTO searchParameters) throws Exception;

    List<ScoreDTO> getAllScores(UserDTO userDTO) throws Exception;

    List<CommentDTO> getAllComments(UserDTO userDTO) throws Exception;

    void createRequest(CreateRequestDTO createRequestDTO) throws Exception;

    List<RequestInfoDTO> getActiveRequests(UserDTO user) throws Exception;

    List<RequestInfoDTO> getFinishedRequests(UserDTO user) throws Exception;

    void pay(RequestInfoDTO request) throws Exception;

    void sendRate(RequestInfoDTO request, double value) throws Exception;

    void sendComment(RequestInfoDTO request, String comment) throws Exception;
}
