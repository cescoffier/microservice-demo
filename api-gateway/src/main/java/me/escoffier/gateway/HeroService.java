package me.escoffier.gateway;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "hero-service")
public interface HeroService {

    @GET
    @Path("/")
    @NonBlocking
    @CircuitBreaker
    Uni<Hero> getRandomHero();
}
