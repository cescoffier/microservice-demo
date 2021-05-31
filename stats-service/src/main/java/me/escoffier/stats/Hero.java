package me.escoffier.stats;

public class Hero {

    public String name;
    public String picture;
    public int level;

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", level=" + level +
                '}';
    }
}
