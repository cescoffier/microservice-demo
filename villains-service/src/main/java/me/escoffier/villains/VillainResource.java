package me.escoffier.villains;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/")
public class VillainResource {

    @GET
    @Path("/random")
    public Villain getRandomVillain() {
        return Villain.getRandomVillain();
    }

    @GET
    public List<Villain> getAllVillains() {
        return Villain.listAll();
    }

}
