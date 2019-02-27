package rs.etf.majstorandroid.network;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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

import rs.etf.majstorandroid.network.dto.RequestInfoDTO;
import rs.etf.majstorandroid.network.dto.SearchRequestsParametersDTO;
import rs.etf.majstorandroid.network.dto.UserDTO;

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
    List<RequestInfoDTO> activeRequests;
    List<String> jobs;
    List<String> paymentMethods;

    public MockDatabaseService() {
        users = new HashMap<>();
        UserDTO user;
        List<String> specialTechniques;
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


        activeRequests = new LinkedList<>();

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 0, 20, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 0, 25, 0, 0);
        request.setDateEnd(c.getTime());
        request.setUrgent(true);
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 1, 22, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 1, 25, 0, 0);
        request.setDateEnd(c.getTime());
        request.setUrgent(false);
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("petar"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 2, 12, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 2, 16, 0, 0);
        request.setDateEnd(c.getTime());
        request.setUrgent(false);
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("miloje"));
        request.setPaymentMethod("kartica");
        c.set(2019, 2, 25, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 2, 26, 0, 0);
        request.setDateEnd(c.getTime());
        request.setUrgent(true);
        activeRequests.add(request);

        request = new RequestInfoDTO();
        request.setClient(users.get("milos"));
        request.setRepairman(users.get("nenad"));
        request.setPaymentMethod("gotovina");
        c.set(2019, 3, 5, 0, 0);
        request.setDateBegin(c.getTime());
        c.set(2019, 3, 15, 0, 0);
        request.setDateEnd(c.getTime());
        activeRequests.add(request);
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

        if (user.getType() != 1) {
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
                userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty() ||
                userDTO.getJob().isEmpty() || userDTO.getPrice() == null) {
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
    public List<RequestInfoDTO> search(Activity activity, SearchRequestsParametersDTO searchParameters, UserDTO user) throws Exception {
        List<RequestInfoDTO> result = new LinkedList<>();

        Log.d(TAG, "Search parameters: " + searchParameters);
        for (RequestInfoDTO request : getActiveRequests(user)) {
            Log.d(TAG, "Request: " + request);

            if (searchParameters.getDateBegin() != null && request.getDateBegin().before(searchParameters.getDateBegin())) {
                continue;
            }

            if (searchParameters.isUrgency() && !request.isUrgent()) {
                continue;
            }

            if (searchParameters.getDistance() != null && searchParameters.getDistance() > 0) {
                try {
                    Location home = null;
                    Location dest = null;

                    List<Address> addresses = new Geocoder(activity).getFromLocationName(request.getRepairman().getAddress(), 1);
                    if (addresses.size() > 0) {
                        Double lat = addresses.get(0).getLatitude();
                        Double lon = addresses.get(0).getLongitude();

                        home = new Location("Home");

                        home.setLatitude(lat);
                        home.setLongitude(lon);
                    }

                    addresses = new Geocoder(activity).getFromLocationName(request.getClient().getAddress(), 1);
                    if (addresses.size() > 0) {
                        Double lat = addresses.get(0).getLatitude();
                        Double lon = addresses.get(0).getLongitude();

                        dest = new Location("Destination");

                        dest.setLatitude(lat);
                        dest.setLongitude(lon);
                    }

                    if (home == null || dest == null) {
                        throw new Exception("Can't find address");
                    }

                    float distance = home.distanceTo(dest);
                    Log.d(TAG, "Calculated distance from " + request.getRepairman().getAddress() + " to " + request.getClient().getAddress() + "[m]: " + distance);

                    if (distance > searchParameters.getDistance()) {
                        continue;
                    }
                } catch (Exception e) {
                    // ignore
                }
            }

            result.add(request);
        }

        Collections.sort(result, new Comparator<RequestInfoDTO>() {
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

        Log.d(TAG, "Search result: " + result);

        return result;
    }

    @Override
    public List<RequestInfoDTO> getActiveRequests(UserDTO user) {
        List<RequestInfoDTO> list = new LinkedList<>();

        for (RequestInfoDTO request : activeRequests) {
            if (request.getRepairman().getUsername().equals(user.getUsername())) {
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
    public void realizeRequest(RequestInfoDTO request) throws Exception {
        if (!request.getDateEnd().before(new Date(System.currentTimeMillis()))) {
            throw new Exception("Request can't be realized");
        }

        activeRequests.remove(request);
    }

    @Override
    public void invalidateRequest(RequestInfoDTO request) throws Exception {
        activeRequests.remove(request);
    }
}
