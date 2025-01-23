package mylibs.function;


import static java.util.stream.IntStream.IntMapMultiConsumer;

import java.util.Objects;


@FunctionalInterface
public interface IntMapMultiConsumerWE<E extends Exception> {
    void accept(int value, IntConsumerWE<?> ic) throws E;
    
    
    private IntMapMultiConsumerWE<E> init() {
        return (value, ic) -> {
            try {
                accept(value, ic);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    
    default IntMapMultiConsumer tryCatchWrapping() {
        return (value, ic) -> init();
    }
    
    
    static <E extends Exception> IntMapMultiConsumer adapt(final IntMapMultiConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}



