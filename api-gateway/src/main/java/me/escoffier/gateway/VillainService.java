package me.escoffier.gateway;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.temporal.ChronoUnit;

@RegisterRestClient(configKey = "villain-service")
public interface VillainService {

    @GET
    @Path("/random")
    @Retry(maxRetries = 5, delay = 100, delayUnit = ChronoUnit.MILLIS)
    Uni<Villain> getRandomVillain();

}
