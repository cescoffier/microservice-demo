package me.escoffier.gateway;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import me.escoffier.fight.FightServiceOuterClass;
import me.escoffier.fight.MutinyFightServiceGrpc;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api")
public class Api {

    @Inject
    @RestClient
    VillainService villains;

    @Inject
    HeroService heroes;

    @Inject
    @GrpcClient("fight-service")
    MutinyFightServiceGrpc.MutinyFightServiceStub fights;

    @GET
    public Uni<Fight> fight() {
        Uni<Hero> hero = heroes.getRandomHero();
        Uni<Villain> villain = villains.getRandomVillain();
        return Uni.combine().all().unis(hero, villain).asTuple()
                .onItem().transformToUni(tuple ->
                        fights.fight(FightServiceOuterClass.Fighters.newBuilder()
                                .setHero(tuple.getItem1().toGrpc())
                                .setVillain(tuple.getItem2().toGrpc())
                                .build()
                        )
                                .onItem()
                                .transform(fight -> new Fight(tuple.getItem1(), tuple.getItem2(), fight.getWinner()))
                ).log();
    }

}
