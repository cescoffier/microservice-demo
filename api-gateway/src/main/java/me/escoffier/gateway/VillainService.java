package me.escoffier.gateway;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "villain-service")
public interface VillainService {

    @GET
    @Path("/")
    Uni<Villain> getVillain();

}
