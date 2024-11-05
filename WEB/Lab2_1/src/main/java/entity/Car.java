package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.SelectBrokenCars", query = "select c from Car c where c.state=0"),
        @NamedQuery(name = "Car.PutCarForRepair", query = "update Car set state=1 where number=:Number"),
})
public class Car {
    private int number;
    private String brand;
    private Integer mileage;
    private Byte state;
    private Collection<Cardriver> cardriversByNumber;

    @Id
    @Column(name = "Number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "Mileage")
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Basic
    @Column(name = "State")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (number != car.number) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (mileage != null ? !mileage.equals(car.mileage) : car.mileage != null) return false;
        if (state != null ? !state.equals(car.state) : car.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "carByCarNumber")
    public Collection<Cardriver> getCardriversByNumber() {
        return cardriversByNumber;
    }

    public void setCardriversByNumber(Collection<Cardriver> cardriversByNumber) {
        this.cardriversByNumber = cardriversByNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", brand='" + brand + '\'' +
                ", mileage=" + mileage +
                ", state=" + state +
                '}';
    }
}
