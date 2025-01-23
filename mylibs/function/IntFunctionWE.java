package mylibs.function;

import java.util.function.IntFunction;
import java.util.Objects;

@FunctionalInterface
public interface IntFunctionWE<R, E extends Exception> {
    R apply(int value) throws E;
    
    default IntFunction<R> tryCatchWrapping() {
        return value -> {
            try {
                return apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <R, E extends Exception> IntFunction<R> adapt(final IntFunctionWE<R, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}