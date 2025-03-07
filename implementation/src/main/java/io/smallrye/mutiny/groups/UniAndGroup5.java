package io.smallrye.mutiny.groups;

import static io.smallrye.mutiny.helpers.ParameterValidation.nonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import io.smallrye.common.annotation.CheckReturnValue;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.tuples.Functions;
import io.smallrye.mutiny.tuples.Tuple5;
import io.smallrye.mutiny.tuples.Tuples;

public class UniAndGroup5<T1, T2, T3, T4, T5> extends UniAndGroupIterable<T1> {

    public UniAndGroup5(Uni<? extends T1> source, Uni<? extends T2> o1, Uni<? extends T3> o2,
            Uni<? extends T4> o3, Uni<? extends T5> o4) {
        super(source, Arrays.asList(o1, o2, o3, o4));
    }

    @CheckReturnValue
    public UniAndGroup5<T1, T2, T3, T4, T5> collectFailures() {
        super.collectFailures();
        return this;
    }

    @CheckReturnValue
    public Uni<Tuple5<T1, T2, T3, T4, T5>> asTuple() {
        return combine(Tuple5::of);
    }

    @CheckReturnValue
    public <O> Uni<O> combinedWith(Functions.Function5<T1, T2, T3, T4, T5, O> combinator) {
        Functions.Function5<T1, T2, T3, T4, T5, O> actual = Infrastructure
                .decorate(nonNull(combinator, "combinator"));
        return combine(actual);
    }

    @SuppressWarnings("unchecked")
    private <O> Uni<O> combine(Functions.Function5<T1, T2, T3, T4, T5, O> combinator) {
        Function<List<?>, O> function = list -> {
            Tuples.ensureArity(list, 5);
            T1 item1 = (T1) list.get(0);
            T2 item2 = (T2) list.get(1);
            T3 item3 = (T3) list.get(2);
            T4 item4 = (T4) list.get(3);
            T5 item5 = (T5) list.get(4);
            return combinator.apply(item1, item2, item3, item4, item5);
        };
        return super.combinedWith(function);
    }

}
