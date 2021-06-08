package me.escoffier.villains;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/")
public class VillainResource {

    @GET
    public Villain get() {
        return Villain.getRandomVillain();
    }
}
