package mylibs.function;

import java.util.function.LongConsumer;
import java.util.Objects;

@FunctionalInterface
public interface LongConsumerWE<E extends Exception> {
    void accept(long value) throws E;
    
    default LongConsumer tryCatchWrapping() {
        return value -> {
            try {
                accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongConsumer adapt(final LongConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}