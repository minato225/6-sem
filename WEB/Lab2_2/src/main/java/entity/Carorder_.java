package entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Carorder.class)
public class Carorder_ {
    public static volatile SingularAttribute<Carorder, Integer> id;
    public static volatile SingularAttribute<Carorder, Brand> brand;
    public static volatile SingularAttribute<Carorder, Integer> mileage;
    public static volatile ListAttribute<Carorder, Cardriver> carDriver;
    public static volatile SingularAttribute<Carorder, String> departure;
    public static volatile SingularAttribute<Carorder, String> destination;
    public static volatile SingularAttribute<Carorder, Date> startTime;

}