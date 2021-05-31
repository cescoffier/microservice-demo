package me.escoffier.stats;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class FightDeserializer extends ObjectMapperDeserializer<Fight> {

    public FightDeserializer() {
        super(Fight.class);
    }
}