package mylibs.function;

import java.util.function.ObjDoubleConsumer;
import java.util.Objects;

@FunctionalInterface
public interface ObjDoubleConsumerWE<T, E extends Exception> {
    void accept(T t, double value) throws E;
    
    default ObjDoubleConsumer<T> tryCatchWrapping() {
        return (t, value) -> {
            try {
                accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ObjDoubleConsumer<T> adapt(final ObjDoubleConsumerWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}