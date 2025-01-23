package mylibs.function;

import java.util.function.ToLongFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToLongFunctionWE<T, E extends Exception> {
    long applyAsLong(T value) throws E;
    
    default ToLongFunction<T> tryCatchWrapping() {
        return value -> {
            try {
                return applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ToLongFunction<T> adapt(final ToLongFunctionWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}