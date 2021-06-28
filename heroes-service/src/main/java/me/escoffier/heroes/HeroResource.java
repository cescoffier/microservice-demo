package me.escoffier.heroes;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HeroResource {

    @GET
    public Uni<Hero> get() {
        return Hero.getRandomHero();
    }

    @GET
    @Path("/all")
    public Multi<Hero> all() {
        return Hero.streamAll();
    }

}
