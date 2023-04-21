package group11.EventFiesta.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Organizer extends Account {

    public Organizer() {
        isOrganizer = true;
    }
    private Integer organizerId;
    private Boolean isOrganizer = true;
    private String firstName;
    private String lastName;
    private String email;
    private String business;
    private String password;
    private String confirmPassword;
    private int businessNo;
    private String address;
    private String city;
    private String province;
    private String pincode;
    private String fromcontact;

    private String tocontact;
    private List<Service> service;

    private String securityQuestion;

    private String securityAnswer;

    public List<Service> getService() {
        return service;
    }

    public Boolean getIsOrganizer() {
        return isOrganizer;
    }

    public void setIsOrganizer(Boolean isOrganizer) {
        this.isOrganizer = isOrganizer;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public String getFromcontact() {
        return fromcontact;
    }

    public void setFromcontact(String fromcontact) {
        this.fromcontact = fromcontact;
    }

    public String getTocontact() {
        return tocontact;
    }

    public void setTocontact(String tocontact) {
        this.tocontact = tocontact;
    }


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(int businessNo) {
        this.businessNo = businessNo;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setAccountId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Integer getAccountId() {
        return organizerId;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    @Override
    public boolean verifyEmailAddress() {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "organizerId=" + organizerId +
                ", isOrganizer=" + isOrganizer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", business='" + business + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", businessNo=" + businessNo +
                ", address='" + address + '\'' +
                ", City='" + city + '\'' +
                ", province='" + province + '\'' +
                ", pincode='" + pincode + '\'' +
                ", fromcontact='" + fromcontact + '\'' +
                ", tocontact='" + tocontact + '\'' +
                ", service=" + service +
                '}';
    }
}
