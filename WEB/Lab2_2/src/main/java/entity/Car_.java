package entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Car.class)
public class Car_ {
    public static volatile SingularAttribute<Car, Integer> number;
    public static volatile SingularAttribute<Car, Brand> brand;
    public static volatile SingularAttribute<Car, Integer> mileage;
    public static volatile SingularAttribute<Car, Boolean> state;
}