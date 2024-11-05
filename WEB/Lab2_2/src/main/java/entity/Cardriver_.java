package entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cardriver.class)
public class Cardriver_ {
    public static volatile SingularAttribute<Cardriver, Integer> id;
    public static volatile SingularAttribute<Cardriver, String> name;
    public static volatile SingularAttribute<Cardriver, Integer> experience;
    public static volatile ListAttribute<Cardriver, Car> car;
}