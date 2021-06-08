package me.escoffier.heroes;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;

@GraphQLApi
public class HeroAPI {

    final HeroRepository repository;

    @Inject
    public HeroAPI(HeroRepository repo) {
        this.repository = repo;
    }

    @Query("randomHero")
    public Hero random() {
        return repository.getRandomHero();
    }

}
