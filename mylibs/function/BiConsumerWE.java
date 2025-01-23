package mylibs.function;

import java.util.function.BiConsumer;
import java.util.Objects;

@FunctionalInterface
public interface BiConsumerWE<T, U, E extends Exception> {
    void accept(T t, U u) throws E;
    
    default BiConsumer<T, U> tryCatchWrapping() {
        return (t, u) -> {
            try {
                accept(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, U, E extends Exception> BiConsumer<T, U> adapt(final BiConsumerWE<T, U, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}