package mylibs.function;

import java.util.function.Function;
import java.util.Objects;

@FunctionalInterface
public interface FunctionWE<T, R, E extends Exception> {
    R apply(T t) throws E;
    
    default Function<T, R> tryCatchWrapping() {
        return arg -> {
            try {
                return apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, R, E extends Exception> Function<T, R> adapt(final FunctionWE<T, R, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}