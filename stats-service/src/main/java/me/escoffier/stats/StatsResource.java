package me.escoffier.stats;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stats")
public class StatsResource {

    @Channel("ratio")
    Multi<WinRatio> ratio;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<WinRatio> ratio() {
        return ratio;
    }

}
