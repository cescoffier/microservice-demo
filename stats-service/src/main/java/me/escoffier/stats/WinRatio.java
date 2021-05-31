package me.escoffier.stats;

public class WinRatio {

    private long count;
    private long hero;
    private long villain;

    public WinRatio accumulate(Fight f) {
        count = count + 1;
        if (f.winner.equals(f.hero.name)) {
            hero = hero + 1;
        } else {
            villain = villain + 1;
        }
        return this;
    }

    public long getCount() {
        return count;
    }

    public long getHero() {
        return hero;
    }

    public long getVillain() {
        return villain;
    }
}
