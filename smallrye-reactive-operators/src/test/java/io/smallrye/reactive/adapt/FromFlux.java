package io.smallrye.reactive.adapt;

import io.smallrye.reactive.Uni;
import reactor.core.publisher.Flux;

public class FromFlux<T> implements UniConverter<Flux<T>, T> {
    @Override
    public Uni<T> from(Flux<T> instance) {
        return Uni.createFrom().publisher(instance);
    }
}
