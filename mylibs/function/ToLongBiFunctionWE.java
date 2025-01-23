package mylibs.function;

import java.util.function.ToLongBiFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToLongBiFunctionWE<T, U, E extends Exception> {
    long applyAsLong(T t, U u) throws E;
    
    default ToLongBiFunction<T, U> tryCatchWrapping() {
        return (t, u) -> {
            try {
                return applyAsLong(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };    
    }
    
    static <T, U, E extends Exception> ToLongBiFunction<T, U> adapt(final ToLongBiFunctionWE<T, U, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}