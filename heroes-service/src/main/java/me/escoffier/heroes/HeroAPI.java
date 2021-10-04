package me.escoffier.heroes;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class HeroAPI {

    final HeroRepository repository;

    public HeroAPI(HeroRepository repo) {
        this.repository = repo;
    }

    @Query("randomHero")
    public Hero random() {
        return repository.getRandomHero();
    }

}
