package me.escoffier.villains

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.coroutines.awaitSuspending
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/")
class VillainResource {

    @GET
    @Path("/all")
    fun getAll() : Multi<Villain> {
        return Villain.streamAll();
    }

    @GET
    suspend fun getRandomVillain() : Villain {
        return Villain.getRandomVillain().awaitSuspending()
    }

}