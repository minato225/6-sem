package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "Cardriver.DeleteCarDriver", query = "select c from Cardriver c where c.name=:Name")
public class Cardriver {
    private int id;
    private String name;
    private Integer experience;
    private Car carByCarNumber;
    private Collection<Carorder> ordersById;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Experience")
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cardriver cardriver = (Cardriver) o;

        if (id != cardriver.id) return false;
        if (name != null ? !name.equals(cardriver.name) : cardriver.name != null) return false;
        if (experience != null ? !experience.equals(cardriver.experience) : cardriver.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CarNumber", referencedColumnName = "Number")
    public Car getCarByCarNumber() {
        return carByCarNumber;
    }

    public void setCarByCarNumber(Car carByCarNumber) {
        this.carByCarNumber = carByCarNumber;
    }

    @OneToMany(mappedBy = "cardriverByDriverId")
    public Collection<Carorder> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Carorder> ordersById) {
        this.ordersById = ordersById;
    }

    @Override
    public String toString() {
        return "Cardriver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", carByCarNumber=" + carByCarNumber +
                '}';
    }
}
