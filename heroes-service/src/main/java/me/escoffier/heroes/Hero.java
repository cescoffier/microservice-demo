package me.escoffier.heroes;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Hero extends PanacheEntity {

    @Column(unique = true)
    public String name;
    public String otherName;
    public int level;
    public String picture;
    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Uni<Hero> getRandomHero() {
        Random random = new Random();
        return Hero.count()
                .onItem().transform(l -> random.nextInt(l.intValue()))
                .onItem().transformToUni(index -> Hero.findAll().page(index, 1).firstResult());
    }
}
