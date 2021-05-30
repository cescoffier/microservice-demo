package me.escoffier.gateway;

import me.escoffier.fight.FightServiceOuterClass;

public class Villain {

    public String name;
    public String picture;
    public int level;

    public FightServiceOuterClass.Villain toGrpc() {
        return FightServiceOuterClass.Villain.newBuilder()
                .setLevel(level)
                .setName(name)
                .setImage(picture)
                .build();
    }

    public static Villain fromGrpc(FightServiceOuterClass.Villain h) {
        Villain villain = new Villain();
        villain.name = h.getName();
        villain.level = h.getLevel();
        villain.picture = h.getImage();
        return villain;
    }

}
