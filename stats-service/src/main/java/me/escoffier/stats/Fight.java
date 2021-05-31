package me.escoffier.stats;

public class Fight {

    public final Hero hero;
    public final Villain villain;
    public final  String winner;

    public Fight(Hero hero, Villain villain, String winner) {
        this.hero = hero;
        this.villain = villain;
        this.winner = winner;
    }
}
