package mylibs.function;

import java.util.function.DoubleConsumer;
import java.util.Objects;

@FunctionalInterface
public interface DoubleConsumerWE<E extends Exception> {
    void accept(double value) throws E;
    
    default DoubleConsumer tryCatchWrapping() {
        return value -> {
            try {
                accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleConsumer adapt(final DoubleConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}