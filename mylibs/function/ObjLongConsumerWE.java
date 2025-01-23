package mylibs.function;

import java.util.function.ObjLongConsumer;
import java.util.Objects;

@FunctionalInterface
public interface ObjLongConsumerWE<T, E extends Exception> {
    void accept(T t, long value) throws E;
    
    default ObjLongConsumer<T> tryCatchWrapping() {
        return (t, value) -> {
            try {
                accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ObjLongConsumer<T> adapt(final ObjLongConsumerWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}