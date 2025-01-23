package mylibs.function;

import java.util.function.DoubleFunction;
import java.util.Objects;

@FunctionalInterface
public interface DoubleFunctionWE<R, E extends Exception> {
    R apply(double value) throws E;
    
    default DoubleFunction<R> tryCatchWrapping() {
        return value -> {
            try {
                return apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <R, E extends Exception> DoubleFunction<R> adapt(final DoubleFunctionWE<R, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}