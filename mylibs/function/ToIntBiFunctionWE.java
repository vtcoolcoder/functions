package mylibs.function;

import java.util.function.ToIntBiFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToIntBiFunctionWE<T, U, E extends Exception> {
    int applyAsInt(T t, U u) throws E;
    
    default ToIntBiFunction<T, U> tryCatchWrapping() {
        return (t, u) -> {
            try {
                return applyAsInt(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, U, E extends Exception> ToIntBiFunction<T, U> adapt(final ToIntBiFunctionWE<T, U, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}