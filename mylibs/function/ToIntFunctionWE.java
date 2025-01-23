package mylibs.function;

import java.util.function.ToIntFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToIntFunctionWE<T, E extends Exception> {
    int applyAsInt(T value) throws E;
    
    default ToIntFunction<T> tryCatchWrapping() {
        return value -> {
            try {
                return applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ToIntFunction<T> adapt(final ToIntFunctionWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}