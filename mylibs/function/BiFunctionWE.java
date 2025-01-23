package mylibs.function;

import java.util.function.BiFunction;
import java.util.Objects;

@FunctionalInterface
public interface BiFunctionWE<T, U, R, E extends Exception> {
    R apply(T t, U u) throws E;
    
    default BiFunction<T, U, R> tryCatchWrapping() {
        return (t, u) -> {
            try {
                return apply(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, U, R, E extends Exception> BiFunction<T, U, R> adapt(final BiFunctionWE<T, U, R, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}


