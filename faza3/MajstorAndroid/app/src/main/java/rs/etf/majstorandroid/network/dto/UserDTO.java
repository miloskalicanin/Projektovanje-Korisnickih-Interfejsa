package rs.etf.majstorandroid.network.dto;

import java.util.List;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    private int type = 0; // 0 - kupac, 1 - majstor
    // polja u nastavku se koriste samo kod majstora
    private String job;
    private Double rating;
    private Integer price;
    private Integer experience;
    private List<String> specialTechniques;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String address, String phoneNumber, String username, String password, int type, String job, Double rating, Integer price, Integer experience, List<String> specialTechniques) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.type = type;
        this.job = job;
        this.rating = rating;
        this.price = price;
        this.experience = experience;
        this.specialTechniques = specialTechniques;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public List<String> getSpecialTechniques() {
        return specialTechniques;
    }

    public void setSpecialTechniques(List<String> specialTechniques) {
        this.specialTechniques = specialTechniques;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", job='" + job + '\'' +
                ", description=" + rating +
                ", price=" + price +
                ", experience=" + experience +
                ", specialTechniques=" + specialTechniques +
                '}';
    }
}
