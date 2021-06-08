package me.escoffier.gateway;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.graphql.client.NamedClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import me.escoffier.fight.FightService;
import me.escoffier.fight.FightServiceOuterClass;
import me.escoffier.fight.MutinyFightServiceGrpc;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api")
public class Api {


    @GET
    public Uni<Fight> fight() {
        return Uni.createFrom().nullItem();
    }

    private Uni<Fight> invokeFightService(FightService fs, Hero hero, Villain villain) {
        FightServiceOuterClass.Fighters fighters = FightServiceOuterClass.Fighters.newBuilder()
                .setHero(hero.toGrpc())
                .setVillain(villain.toGrpc())
                .build();

        return fs.fight(fighters)
                .onItem().transform(result -> {
                        String winner = result.getWinner();
                        return new Fight(hero, villain, winner);
        });
    }
}
