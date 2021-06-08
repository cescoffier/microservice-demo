package me.escoffier.stats;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FightProcessor {

    private final WinRatio ratio = new WinRatio();

    @Incoming("fights")
    @Outgoing("ratio")
    @Broadcast
    public WinRatio ratio(Fight f) {
        return ratio.accumulate(f);
    }

}
