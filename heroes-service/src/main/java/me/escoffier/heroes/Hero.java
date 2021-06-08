package me.escoffier.heroes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hero {

    public final String name;
    public final int level;
    public final String image;
    public final List<String> powers;
    public final String longName;

    public Hero(String name, String longName, String image, List<String> powers, int level) {
        this.name = name;
        this.level = level;
        this.image = image;
        this.powers = powers;
        this.longName = longName;
    }

    public Hero(String name, String longName, String image, String powers, int level) {
        this(name, longName, image, Arrays.stream(powers.split(",")).map(String::trim).collect(Collectors.toUnmodifiableList()), level);
    }

}
