package mylibs.function;

import java.util.function.LongFunction;
import java.util.Objects;

@FunctionalInterface
public interface LongFunctionWE<R, E extends Exception> {
    R apply(long value) throws E;
    
    default LongFunction<R> tryCatchWrapping() {
        return value -> {
            try {
                return apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <R, E extends Exception> LongFunction<R> adapt(final LongFunctionWE<R, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}