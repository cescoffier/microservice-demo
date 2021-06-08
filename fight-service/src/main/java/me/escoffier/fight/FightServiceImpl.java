package me.escoffier.fight;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class FightServiceImpl implements FightService {
    @Override
    public Uni<FightServiceOuterClass.Fight> fight(FightServiceOuterClass.Fighters request) {
        FightServiceOuterClass.Hero hero = request.getHero();
        FightServiceOuterClass.Villain villain = request.getVillain();

        return Fights.fight(hero, villain)
                .onItem().transform(winner -> {
                    return FightServiceOuterClass.Fight.newBuilder().setWinner(winner).build();
                });
    }
}
