package mylibs.function;

import java.util.function.IntConsumer;
import java.util.Objects;

@FunctionalInterface
public interface IntConsumerWE<E extends Exception> {
    void accept(int value) throws E;
    
    default IntConsumer tryCatchWrapping() {
        return value -> {
            try {
                accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntConsumer adapt(final IntConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}