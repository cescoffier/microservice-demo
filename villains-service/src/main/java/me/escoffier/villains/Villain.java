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
    public int level;
    public String picture;
    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Villain getRandomVillain() {
        Random random = new Random();
        int count = (int) Villain.count();
        int index = random.nextInt(count);
        return Villain.findAll()
                .page(index, 1)
                .firstResult();
    }


}
