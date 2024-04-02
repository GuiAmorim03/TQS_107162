package tqs.ua.pt.backend.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long travelId;
    private String departure;
    private String arrival;
    private Date date;
    private int seats;
    private double price;

    private int MAX_SEATS = 25;

    public Travel(String departure, String arrival, Date date, double price) {
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.seats = MAX_SEATS;
        this.price = price;
    }

    public void addSeats(int seats) {
        if (this.seats - seats < 0) {
            throw new IllegalArgumentException("Not enough seats available");
        } else {
            this.seats -= seats;
        }
    }

    // getters and setters

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelId=" + travelId +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", date=" + date +
                ", seats=" + seats +
                ", price=" + price +
                '}';
    }

}
