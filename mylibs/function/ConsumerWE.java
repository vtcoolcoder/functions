package mylibs.function;

import java.util.function.Consumer;
import java.util.Objects;

@FunctionalInterface
public interface ConsumerWE<T, E extends Exception> {
    void accept(T t) throws E;
    
    default Consumer<T> tryCatchWrapping() {
        return arg -> {
            try {
                accept(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> Consumer<T> adapt(final ConsumerWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}