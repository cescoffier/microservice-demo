package me.escoffier.heroes;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class HeroesApi {

    @Inject
    HeroRepository heroes;

    @Query("randomHero")
    public Hero getRandomHero() {
        return heroes.getRandomHero();
    }

    @Query("allHeroes")
    public List<Hero> getAllHeroes() {
        return heroes.getAllHeroes();
    }

}
