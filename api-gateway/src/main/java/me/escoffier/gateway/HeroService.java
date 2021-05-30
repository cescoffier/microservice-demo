package me.escoffier.gateway;

import io.smallrye.graphql.client.NamedClient;
import io.smallrye.graphql.client.core.Document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Operation.operation;
import io.smallrye.graphql.client.core.OperationType;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 * GraphQL Client
 * @see https://quarkus.io/version/main/guides/smallrye-graphql-client
 */
@ApplicationScoped
public class HeroService {

    @Inject
    @NamedClient("hero-service")
    DynamicGraphQLClient qlClient;
    
    public Uni<Hero> getRandomHero() {
        
        Document randomHero = document(
                operation(OperationType.QUERY,
                        field("randomHero",
                                field("name"),
                                field("level"),
                                field("image")
                        )));

        
        return qlClient.executeAsync(randomHero)
                .onItem().transform(response -> response.getObject(Hero.class, "randomHero"));
    }


}
