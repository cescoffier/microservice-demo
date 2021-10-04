# 50 shades of microservices demo

* 6 terminals:

** villains
** heroes
** fights
** api
** stats
** commands


## Villains

### Setup
* Open https://code.quarkus.io/?g=org.example&a=villains-service&ne=true&e=resteasy-jackson&e=jdbc-postgresql&e=hibernate-orm-panache


### Development
* Create `me.escoffier.villains.Villain`:

```java
@Entity
public class Villain extends PanacheEntity {

    @Column(unique = true)
    public String name;
    public String otherName;
    public int level;
    public String picture;
    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Villain findRandom() {
        long countVillains = count();
        Random random = new Random();
        int randomVillain = random.nextInt((int) countVillains);
        return findAll().page(randomVillain, 1).firstResult();
    }
}
```

* Show the `application.properties`:

```properties
quarkus.http.port=9000

quarkus.datasource.db-kind=postgresql
quarkus.hibernate-orm.database.generation=drop-and-create
```

* Run quarkus:dev

* Create `me.escoffier.villains.VillainResource`:

```java
@Path("/villains")
public class VillainResource {

    @GET
    public Villain getRandomVillain() {
        return Villain.findRandom();
    }

}
```

* Open http://localhost:9000

## Heroes

* GraphQL, already existing prooject
* Show `Hero` and `HeroRepository` - mention image vs. picture 
* Create `me.escoffier.heroes.HeroesApi`:
```java
@GraphQLApi
public class HeroesApi {

    @Inject
    HeroRepository heroes;

    @Query("randomHero")
    public Hero getRandomHero() {
        return heroes.getRandomHero();
    }

    @Query("allHeroes")
    public List<Hero> getAllHeroes() {
        return heroes.getAllHeroes();
    }

}
```
* mvn quarkus:dev
* open http://localhost:9001/q/dev/
* Go to the graphql UI
* Execute query (with and without the alias)
```graphql
query {
  randomHero {
     name, picture:image, level
  }
}
```

## Fight

* gRPC, proto file already created
* Show proto file
* mvn quarkus:dev
* Implement:
```java
@GrpcService
public class FightServiceImpl implements FightService {

    @Override
    public Uni<FightServiceOuterClass.Fight> fight(FightServiceOuterClass.Fighters request) {
        FightServiceOuterClass.Hero hero = request.getHero();
        FightServiceOuterClass.Villain villain = request.getVillain();

        return Fights.fight(hero, villain)
                .onItem().transform(winner -> FightServiceOuterClass.Fight.newBuilder().setWinner(winner).build());
    }
}
```

* Explain mutiny a bit
* Go to http://localhost:9003/q/dev/ then gRPC view
* Click on Test for the FightService
```json
{
    "hero": {
        "name": "neo",
        "level": 10
    },
    "villain": {
        "name": "clement",
        "level": 11
    }
}
```

## API

* API gateway, reactive
* get a hero / get a villain -> fight

### Villain - REST Client

* Create:
```java
@RegisterRestClient(configKey = "villain-service")
public interface VillainService {

    @GET
    @Path("/random")
    Uni<Villain> getRandomVillain();

}
```
* In `Api` add:
```java
@Inject
@RestClient
VillainService villains;
```

### Fight - gRPC

```java
@Inject
@GrpcClient("fight-service")
MutinyFightServiceGrpc.MutinyFightServiceStub fights;
```

### Implement fight:

```java
@GET
public Uni<Fight> fight() {
    Uni<Hero> hero = heroes.getRandomHero();
    Uni<Villain> villain = villains.getRandomVillain();

    return Uni.combine().all().unis(hero, villain).asTuple()
            .onItem().transformToUni(tuple -> {
                return invokeFightService(tuple.getItem1(), tuple.getItem2());
            })
            .log();
}
```

* Show the application.properties and explain service discovery and config source
* Open http://localhost:9999

### Kafka

In API: 

```java
@Channel("fights")
MutinyEmitter<Fight> emitter;

// ...

.call(f -> emitter.send(f))
```

* In application.properties uncomment the 2 lines related to the channel configuration
* Start the stats service (with kafka.botostrap.servers) and show `me.escoffier.stats.FightProcessor`

* Connect to SSE: http :9005/stats --stream
* Trigger fights from the UI

## Fault Tolerance

If time allows:

* `API`: `@Retry @NonBlocking` on `invokeFightService`
* `VillainService`: `@Retry @CircuitBreaker`

