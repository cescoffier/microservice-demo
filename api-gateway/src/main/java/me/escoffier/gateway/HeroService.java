package me.escoffier.gateway;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class HeroService {

    @ConfigProperty(name = "hero-service")
    String url;

    @Inject
    Vertx vertx;
    private WebClient client;
    private JsonObject query;

    @PostConstruct
    public void init() {
        client = WebClient.create(vertx);
        query = new JsonObject();
        query.put("query", "{randomHero {"
                    + "name, "
                    + "picture:image, "
                    + "level "
                    + "}"
                + "}");
    }

    @Retry(maxRetries = 5, delay = 100, delayUnit = ChronoUnit.MILLIS)
    public Uni<Hero> getRandomHero() {
        return client.postAbs(url + "/graphql")
                .sendJsonObject(query)
                .onItem().transform(resp -> resp.bodyAsJsonObject().getJsonObject("data").getJsonObject("randomHero"))
                .onItem().transform(json -> json.mapTo(Hero.class))
                .log("hero-service");
    }

    //    private DynamicGraphQLClient qlClient;
    //
    //    @PostConstruct
    //    public void init() {
    //        qlClient = DynamicGraphQLClientBuilder.newBuilder().url(url + "/graphql").build();
    //    }
    //
    //    public Uni<Hero> getRandomHero() {
    //        Field q = field("randomHero");
    //        q.setFields(List.of(field("name"), field("level"), field("image")));
    //        Operation query = Operation.operation(q);
    //
    //        return qlClient.executeAsync(document(query))
    //                .onItem().transform(response -> createHero(response.getData()));
    //    }
    //
//        private Hero createHero(JsonObject data) {
//            JsonObject res = data.getJsonObject("randomHero");
//            Hero hero = new Hero();
//            hero.name = res.getString("name");
//            hero.level = res.getInteger("level");
//            hero.picture = res.getString("image");
//            return hero;
//        }

}
