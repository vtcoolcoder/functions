package mylibs.function;


import static java.util.stream.LongStream.LongMapMultiConsumer;

import java.util.Objects;


@FunctionalInterface
public interface LongMapMultiConsumerWE<E extends Exception> {
    void accept(long value, LongConsumerWE<?> lc) throws E;
    
    
    private LongMapMultiConsumerWE<E> init() {
        return (value, lc) -> {
            try {
                accept(value, lc);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    
    default LongMapMultiConsumer tryCatchWrapping() {
        return (value, lc) -> init();
    }
    
    
    static <E extends Exception> LongMapMultiConsumer adapt(final LongMapMultiConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}

