package tqs.ua.pt.carsservice;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    private String maker;
    private String model;

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Car() {
        this.maker = null;
        this.model = null;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getCarId() != null ? !getCarId().equals(car.getCarId()) : car.getCarId() != null) return false;
        if (getMaker() != null ? !getMaker().equals(car.getMaker()) : car.getMaker() != null) return false;
        return getModel() != null ? getModel().equals(car.getModel()) : car.getModel() == null;
    }

    @Override
    public int hashCode() {
        int result = getCarId() != null ? getCarId().hashCode() : 0;
        result = 31 * result + (getMaker() != null ? getMaker().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        return result;
    }
    
    
}
