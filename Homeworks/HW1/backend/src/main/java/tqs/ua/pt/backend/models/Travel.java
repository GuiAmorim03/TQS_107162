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
    private Long tripId;
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
        this.seats = 0;
        this.price = price;
    }

    public void addSeats(int seats) {
        if (this.seats + seats > MAX_SEATS) {
            throw new IllegalArgumentException("Not enough seats available");
        } else {
            this.seats += seats;
        }
    }

    // getters and setters

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
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
                "tripId=" + tripId +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", date=" + date +
                ", seats=" + seats +
                ", price=" + price +
                '}';
    }

}
