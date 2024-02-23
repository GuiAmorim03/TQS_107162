package tqs.ua.pt;

import java.util.Objects;

public class Address {
    
    private String houseNumber;
    private String city;
    private String road;

    public Address(String houseNumber, String city, String road, String zip) {
        this.houseNumber = houseNumber;
        this.city = city;
        this.road = road;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getRoad() {
        return road;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", road='" + road + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.toString().length();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.getCity(), other.getCity())) {
            return false;
        }
        if (!Objects.equals(this.getHouseNumber(), other.getHouseNumber())) {
            return false;
        }
        return Objects.equals(this.getRoad(), other.getRoad());
    }


}
