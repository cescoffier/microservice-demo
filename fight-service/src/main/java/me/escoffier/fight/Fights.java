package me.escoffier.fight;

import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.Random;

public class Fights {

    public static Uni<String> fight(FightServiceOuterClass.Hero hero, FightServiceOuterClass.Villain villain) {
        Random random = new Random();
        int actualHeroLevel = hero.getLevel() + random.nextInt(50);
        int actualVillainLevel = villain.getLevel() + random.nextInt(50);
        int duration = random.nextInt(500);

        if (actualHeroLevel > actualVillainLevel) {
            return Uni.createFrom().item(hero.getName())
                    .onItem().delayIt().by(Duration.ofMillis(duration));
        } else if (actualHeroLevel < actualVillainLevel) {
            return Uni.createFrom().item(villain.getName())
                    .onItem().delayIt().by(Duration.ofMillis(duration));
        } else {
            return fight(hero, villain);
        }
    }

}
