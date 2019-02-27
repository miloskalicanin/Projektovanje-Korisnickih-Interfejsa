package rs.etf.kupacandroid.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rs.etf.kupacandroid.network.dto.CommentDTO;
import rs.etf.kupacandroid.network.dto.CreateRequestDTO;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.ScoreDTO;
import rs.etf.kupacandroid.network.dto.SearchParametersDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class MockDatabaseService implements NetworkService {
    private static final String TAG = MockDatabaseService.class.getSimpleName();

    private static MockDatabaseService instance;

    public static MockDatabaseService getInstance() {
        if (instance == null) {
            instance = new MockDatabaseService();
        }

        return instance;
    }

    Map<String, UserDTO> users;
    Map<String, List<ScoreDTO>> scores;
    Map<String, List<CommentDTO>> comments;
    List<RequestInfoDTO> activeRequests;
    List<RequestInfoDTO> finishedRequests;
    List<String> jobs;
    List<String> paymentMethods;

    public MockDatabaseService() {
        users = new HashMap<>();
        scores = new HashMap<>();
        comments = new HashMap<>();
        UserDTO user;
        List<String> specialTechniques;
        ScoreDTO score;
        CommentDTO comment;
        RequestInfoDTO request;
        Calendar c = Calendar.getInstance();

        jobs = new ArrayList<>(5);
        jobs.add("moler");
        jobs.add("stolar");
        jobs.add("elektricar");
        jobs.add("vodoinstalater");
        jobs.add("klima serviser");

        paymentMethods = new ArrayList<>(2);
        paymentMethods.add("gotovina");
        paymentMethods.add("kartica");

        user = new UserDTO();
        user.setFirstName("Milos");
        user.setLastName("Kalicanin");
        user.setAddress("Nicifora Ninkovica 60");
        user.setPhoneNumber("0641234567");
        user.setUsername("milos");
        user.setPassword("milos");
        user.setType(0);
        users.put("milos", user);

        user = new UserDTO();
        user.setFirstName("Petar");
        user.setLastName("Petrovic");
        user.setAddress("Takovska 1");
        user.setPhoneNumber("0641234567");
        user.setUsername("petar");
        user.setPassword("petar");
        user.setType(1);
        user.setJob("moler");
        user.setPrice(1200);
        user.setRating(4.56);
        user.setExperience(7);
        specialTechniques = new ArrayList<>(3);
        specialTechniques.add("gletovanje");
        specialTechniques.add("krecenje");
        specialTechniques.add("farbanje");
        user.setSpecialTechniques(specialTechniques);
        users.put("petar", user);

        user = new UserDTO();
        user.setFirstName("Miloje");
        user.setLastName("Milivojevic");
        user.setAddress("Takovska 2");
        user.setPhoneNumber("0641234567");
        user.setUsername("miloje");
        user.setPassword("miloje");
        user.setType(1);
        user.setJob("moler");
        user.setPrice(1199);
        user.setRating(4.3);
        user.setExperience(5);
        specialTechniques = new ArrayList<>(2);
        specialTechniques.add("krecenje");
        specialTechniques.add("farbanje");
        user.setSpecialTechniques(specialTechniques);
        users.put("miloje", user);

        user = new UserDTO();
        user.setFirstName("Jovan");
        user.setLastName("Jovanovic");
        user.setAddress("Takovska 3");
        user.setPhoneNumber("0641234567");
        user.setUsername("jovan");
        user.setPassword("jovan");
        user.setType(1);
        user.setJob("elektricar");
        user.setPrice(800);
        user.setRating(3.87);
        user.setExperience(1);
        specialTechniques = new ArrayList<>(2);
        specialTechniques.add("popravka frizidera");
        specialTechniques.add("popravka sporeta");
        user.setSpecialTechniques(specialTechniques);
        users.put("jovan", user);

        user = new UserDTO();
        user.setFirstName("Stevan");
        user.setLastName("Stevanovic");
        user.setAddress("Takovska 4");
        user.setPhoneNumber("0641234567");
        user.setUsername("stevan");
        user.setPassword("stevan");
        user.setType(1);
        user.setJob("elektricar");
        user.setPrice(1100);
        user.setRating(4.04);
        user.setExperience(3);
        specialTechniques = new ArrayList<>(4);
        specialTechniques.add("popravka frizidera");
        specialTechniques.add("popravka sporeta");
        specialTechniques.add("popravka ves masina");
        specialTechniques.add("popravka televizora");
        user.setSpecialTechniques(specialTechniques);
        users.put("stevan", user);

        user = new UserDTO();
        user.setFirstName("Nenad");
        user.setLastName("Nenadovic");
        user.setAddress("Takovska 5");
        user.setPhoneNumber("0641234567");
        user.setUsername("nenad");
        user.setPassword("nenad");
        user.setType(1);
        user.setJob("stolar");
        user.setPrice(2000);
        user.setRating(3.83);
        user.setExperience(5);
        users.put("nenad", user);

        scores.put("petar", new LinkedList<ScoreDTO>());
        scores.put("miloje", new LinkedList<ScoreDTO>());

        score = new ScoreDTO();
        c.set(2018, 11, 21, 0, 0);
        score.setDate(c.getTime());
        score.setScore(5.0);
        scores.get("petar").add(score);

        score = new ScoreDTO();
        c.set(2018, 11, 17, 0, 0);
        score.setDate(c.getTime());
        score.setScore(5.0);
        scores.get("petar").add(score);

        score = new ScoreDTO();
        c.set(2018, 11, 6, 0, 0);
        score.setDate(c.getTime());
        score.setScore(5.0);
        scores.get("petar").add(score);

        score = new ScoreDTO();
        c.set(2018, 10, 24, 0, 0);
        score.setDate(c.getTime());
        score.setScore(4.0);
        scores.get("petar").add(score);

        score = new ScoreDTO();
        c.set(2018, 10, 12, 0, 0);
        score.setDate(c.getTime());
        score.setScore(5.0);
        scores.get("petar").add(score);

        score = new ScoreDTO();
        c.set(2018, 10, 29, 0, 0);
        score.setDate(c.getTime());
        score.setScore(4.0);
        scores.get("miloje").add(score);

        score = new ScoreDTO();
        c.set(2018, 10, 14, 0, 0);
        score.setDate(c.getTime());
        score.setScore(5.0);
        scores.get("miloje").add(score);

        score = new ScoreDTO();
        c.set(2018, 10, 5, 0, 0);
        score.setDate(c.getTime());
        score.setScore(4.0);
        scores.get("miloje").add(score);

        comments.put("petar", new LinkedList<CommentDTO>());

        comment = new CommentDTO();
        c.set(2018, 11, 21, 0, 0);
        comment.setDate(c.getTime());
        comment.setComment("Preporuka.");
        comments.get("petar").add(comment);

        comment = new CommentDTO();
        c.set(2018, 11, 17, 0, 0);
        comment.setDate(c.getTime());
        comment.setComment("Veoma kvalitetno zavrseni radovi. Odlicna komunikacija, ugovorena dalja saradnja.");
        comments.get("petar").add(comment);

        comment = new CommentDTO();
        c.set(2018, 10, 24, 0, 0);
        comment.setDate(c.getTime());
        comment.setComment("Zadovoljan uslugom.");
        comments.get("petar").add(comment);


        activeRequests = new LinkedList<>();

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 0, 11, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 0, 13, 0, 0);
        request.setDateEnd(c.getTime());
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 1, 12, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 1, 16, 0, 0);
        request.setDateEnd(c.getTime());
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("miloje"));
        request.setPaymentMethod("kartica");
        c.set(2019, 1, 25, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 1, 26, 0, 0);
        request.setDateEnd(c.getTime());
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("nenad"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 2, 5, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 2, 15, 0, 0);
        request.setDateEnd(c.getTime());
        activeRequests.add(request);


        finishedRequests = new LinkedList<>();

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 0, 17, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 0, 21, 0, 0);
        request.setDateEnd(c.getTime());
        finishedRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 0, 4, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 0, 5, 0, 0);
        request.setDateEnd(c.getTime());
        finishedRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("jovan"));
        request.setPaymentMethod("kartica");
        c.set(2018, 11, 18, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2018, 11, 23, 0, 0);
        request.setDateEnd(c.getTime());
        finishedRequests.add(request);

        calculateUserRating(users.get("petar"));
        calculateUserRating(users.get("miloje"));
        calculateUserRating(users.get("jovan"));
        calculateUserRating(users.get("stevan"));
        calculateUserRating(users.get("nenad"));
    }

    @Override
    public List<String> getJobs() {
        return new ArrayList<>(jobs);
    }

    @Override
    public List<String> getPaymentMethods() {
        return new ArrayList<>(paymentMethods);
    }

    @Override
    public UserDTO login(String username, String password) throws Exception {
        UserDTO user = users.get(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Invalid user data");
        }

        if (user.getType() != 0) {
            throw new Exception("User type is not for this application");
        }

        return user;
    }

    @Override
    public UserDTO register(UserDTO userDTO) throws Exception {
        if (userDTO.getFirstName().isEmpty() || userDTO.getLastName().isEmpty() ||
                userDTO.getAddress().isEmpty() || userDTO.getPhoneNumber().isEmpty() ||
                userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            throw new Exception("Invalid user data");
        }

        if (users.get(userDTO.getUsername()) != null) {
            throw new Exception("User already exists");
        }

        users.put(userDTO.getUsername(), userDTO);

        return userDTO;
    }

    @Override
    public void editUserData(UserDTO userDTO) throws Exception {
        if (userDTO.getFirstName().isEmpty() || userDTO.getLastName().isEmpty() ||
                userDTO.getAddress().isEmpty() || userDTO.getPhoneNumber().isEmpty() ||
                userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            throw new Exception("Invalid user data");
        }

        if (users.get(userDTO.getUsername()) == null) {
            throw new Exception("User doesn't exists");
        }

        users.put(userDTO.getUsername(), userDTO);
    }

    @Override
    public void editPassword(UserDTO userDTO, String newPassword) throws Exception {
        if (users.get(userDTO.getUsername()) == null) {
            throw new Exception("User doesn't exists");
        }

        userDTO.setPassword(newPassword);

        users.put(userDTO.getUsername(), userDTO);
    }

    @Override
    public List<UserDTO> search(SearchParametersDTO searchParameters) throws Exception {
        List<UserDTO> result = new LinkedList<>();

        Log.d(TAG, "Search parameters: " + searchParameters);
        for (Map.Entry<String, UserDTO> entry : users.entrySet()) {
            UserDTO user = entry.getValue();

            if (user.getType() != 1) {
                continue;
            }

            if (searchParameters.getJob() != null && !searchParameters.getJob().equals("svi poslovi") && !searchParameters.getJob().equals(user.getJob())) {
                continue;
            }

            if (searchParameters.getPriceFrom() != null) {
                int requestedPriceFrom = Integer.parseInt(searchParameters.getPriceFrom());
                if (user.getPrice() < requestedPriceFrom) {
                    continue;
                }
            }

            if (searchParameters.getPriceTo() != null) {
                int requestedPriceTo = Integer.parseInt(searchParameters.getPriceTo());
                if (user.getPrice() > requestedPriceTo) {
                    continue;
                }
            }

            if (searchParameters.getExperienceFrom() != null) {
                int requestedExperienceFrom = Integer.parseInt(searchParameters.getExperienceFrom());
                if (user.getExperience() < requestedExperienceFrom) {
                    continue;
                }
            }

            if (searchParameters.getExperienceTo() != null) {
                int requestedExperienceTo = Integer.parseInt(searchParameters.getExperienceTo());
                if (user.getExperience() > requestedExperienceTo) {
                    continue;
                }
            }

            if (searchParameters.getRatingFrom() != null) {
                double requestedRatingFrom = Double.parseDouble(searchParameters.getRatingFrom());
                if (user.getRating() < requestedRatingFrom) {
                    continue;
                }
            }

            if (searchParameters.getRatingTo() != null) {
                double requestedRatingTo = Double.parseDouble(searchParameters.getRatingTo());
                if (user.getRating() > requestedRatingTo) {
                    continue;
                }
            }

            if (searchParameters.isSpecialTechniques() && (user.getSpecialTechniques() == null || user.getSpecialTechniques().isEmpty())) {
                continue;
            }

            result.add(user);
        }

        Collections.sort(result, new Comparator<UserDTO>() {
            @Override
            public int compare(UserDTO user1, UserDTO user2) {
                double rating1 = -1, rating2 = -1;
                try {
                    rating1 = user1.getRating();
                } catch (Exception e) {
                    // ignore
                }
                try {
                    rating2 = user2.getRating();
                } catch (Exception e) {
                    // ignore
                }
                return Double.compare(rating1, rating2);
            }
        });
        Collections.reverse(result);

        Log.d(TAG, "Search result: " + result);

        return result;
    }

    @Override
    public List<ScoreDTO> getAllScores(UserDTO userDTO) throws Exception {
        List<ScoreDTO> result = scores.get(userDTO.getUsername());
        if (result == null) {
            result = new ArrayList<>();
        }

        Collections.sort(result, new Comparator<ScoreDTO>() {
            @Override
            public int compare(ScoreDTO score1, ScoreDTO score2) {
                if (score1 == null) {
                    return -1;
                } else if (score2 == null) {
                    return 1;
                }
                Date d1 = score1.getDate();
                Date d2 = score2.getDate();
                return d1.compareTo(d2);
            }
        });
        Collections.reverse(result);

        return result;
    }

    @Override
    public List<CommentDTO> getAllComments(UserDTO userDTO) throws Exception {
        List<CommentDTO> result = comments.get(userDTO.getUsername());
        if (result == null) {
            result = new ArrayList<>();
        }

        Collections.sort(result, new Comparator<CommentDTO>() {
            @Override
            public int compare(CommentDTO comment1, CommentDTO comment2) {
                if (comment1 == null) {
                    return -1;
                } else if (comment2 == null) {
                    return 1;
                }
                Date d1 = comment1.getDate();
                Date d2 = comment2.getDate();
                return d1.compareTo(d2);
            }
        });
        Collections.reverse(result);

        return result;
    }

    @Override
    public void createRequest(CreateRequestDTO createRequestDTO) throws Exception {
        if (createRequestDTO == null || activeRequests == null) {
            return;
        }

        UserDTO client = users.get(createRequestDTO.getClientUsername());
        UserDTO repairman = users.get(createRequestDTO.getRepairmanUsername());

        if (client == null || repairman == null) {
            throw new Exception("User not found!");
        }

        RequestInfoDTO request = new RequestInfoDTO();
        request.setClient(client);
        request.setRepairman(repairman);
        request.setPaymentMethod(createRequestDTO.getPaymentMethod());
        request.setDateBegin(createRequestDTO.getDateBegin());
        request.setDateEnd(createRequestDTO.getDateEnd());
        activeRequests.add(request);
    }

    @Override
    public List<RequestInfoDTO> getActiveRequests(UserDTO user) {
        List<RequestInfoDTO> list = new LinkedList<>();

        for (RequestInfoDTO request : activeRequests) {
            if (request.getClient().getUsername().equals(user.getUsername())) {
                list.add(request);
            }
        }

        Collections.sort(list, new Comparator<RequestInfoDTO>() {
            @Override
            public int compare(RequestInfoDTO request1, RequestInfoDTO request2) {
                if (request1 == null) {
                    return -1;
                } else if (request2 == null) {
                    return 1;
                }
                Date d1 = request1.getDateBegin();
                Date d2 = request2.getDateBegin();
                return d1.compareTo(d2);
            }
        });

        return list;
    }

    @Override
    public List<RequestInfoDTO> getFinishedRequests(UserDTO user) {
        List<RequestInfoDTO> list = new LinkedList<>();

        for (RequestInfoDTO request : finishedRequests) {
            if (request.getClient().getUsername().equals(user.getUsername())) {
                list.add(request);
            }
        }

        Collections.sort(list, new Comparator<RequestInfoDTO>() {
            @Override
            public int compare(RequestInfoDTO request1, RequestInfoDTO request2) {
                if (request1 == null) {
                    return -1;
                } else if (request2 == null) {
                    return 1;
                }
                Date d1 = request1.getDateBegin();
                Date d2 = request2.getDateBegin();
                return d1.compareTo(d2);
            }
        });
        Collections.reverse(list);

        return list;
    }

    @Override
    public void pay(RequestInfoDTO request) throws Exception {
        activeRequests.remove(request);
        finishedRequests.add(request);
    }

    @Override
    public void sendRate(RequestInfoDTO request, double value) throws Exception {
        ScoreDTO score = new ScoreDTO();
        score.setDate(new Date(System.currentTimeMillis()));
        score.setScore(value);

        List<ScoreDTO> list = scores.get(request.getRepairman().getUsername());
        if (list == null) {
            list = new LinkedList<>();
            scores.put(request.getRepairman().getUsername(), list);
        }
        list.add(score);

        calculateUserRating(request.getRepairman());
    }

    @Override
    public void sendComment(RequestInfoDTO request, String comment) throws Exception {
        CommentDTO comm = new CommentDTO();
        comm.setDate(new Date(System.currentTimeMillis()));
        comm.setComment(comment);

        List<CommentDTO> list = comments.get(request.getRepairman().getUsername());
        if (list == null) {
            list = new LinkedList<>();
            comments.put(request.getRepairman().getUsername(), list);
        }
        list.add(comm);
    }

    private void calculateUserRating(UserDTO user) {
        UserDTO repairman = users.get(user.getUsername());

        try {
            double sum = 0;
            int num = 0;
            for (ScoreDTO score : scores.get(repairman.getUsername())) {
                sum += score.getScore();
                num++;
            }
            if (num > 0) {
                repairman.setRating(sum / num);
            } else {
                repairman.setRating(0.0);
            }
        } catch (Exception e) {
            repairman.setRating(0.0);
        }
    }
}
