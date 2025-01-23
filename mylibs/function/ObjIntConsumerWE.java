package mylibs.function;

import java.util.function.ObjIntConsumer;
import java.util.Objects;

@FunctionalInterface
public interface ObjIntConsumerWE<T, E extends Exception> {
    void accept(T t, int value) throws E;
    
    default ObjIntConsumer<T> tryCatchWrapping() {
        return (t, value) -> {
            try {
                accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ObjIntConsumer<T> adapt(final ObjIntConsumerWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}
