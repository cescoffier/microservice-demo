package me.escoffier.villains;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Villain extends PanacheEntity {

    @Column(unique = true)
    public String name;
    public String otherName;
    public String picture;
    public int level;
    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Villain getRandomVillain() {
        Random random = new Random();
        int index = random.nextInt((int) Villain.count());
        return Villain.findAll().page(index, 1).firstResult();
    }

}
