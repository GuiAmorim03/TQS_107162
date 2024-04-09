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
    private Date dateDeparture;
    private Date dateArrival;
    private int travelDuration; // minutos
    private int seats;
    private double price;

    private int maxSEATS = 25;

    public Travel(String departure, String arrival, Date dateDeparture, int travelDuration, double price) {
        this.departure = departure;
        this.arrival = arrival;
        this.dateDeparture = dateDeparture;
        this.travelDuration = travelDuration;
        this.seats = maxSEATS;
        this.price = price;

        this.dateArrival = new Date(dateDeparture.getTime() + travelDuration * 60 * 1000); // vezes 60 segundos e 1000 milisegundos
    }

    public Travel() {
   
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

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public int getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(int travelDuration) {
        this.travelDuration = travelDuration;
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
                ", dateDeparture=" + dateDeparture +
                ", dateArrival=" + dateArrival +
                ", travelDuration=" + travelDuration +
                ", seats=" + seats +
                ", price=" + price +
                '}';
    }

}
