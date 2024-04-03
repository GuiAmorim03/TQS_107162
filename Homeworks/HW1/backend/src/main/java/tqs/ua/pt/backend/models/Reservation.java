package tqs.ua.pt.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long token;
    @ManyToOne
    private Travel travel;
    private String name;
    private String email;
    private String phone;
    private String nif;
    private String address;
    private String paymentMethod;
    private String paymentNumber;
    private int qtt;

    public Reservation(Travel travel, String name, String email, String phone, String nif, String address,
            String paymentMethod, String paymentNumber, int qtt) {
        this.travel = travel;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nif = nif;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.paymentNumber = paymentNumber;
        this.qtt = qtt;
    }

    // getters and setters

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "token=" + token +
                ", travel=" + travel +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentNumber='" + paymentNumber + '\'' +
                ", qtt=" + qtt +
                '}';
    }
}
