package mylibs.function;

import java.util.function.DoubleToLongFunction;
import java.util.Objects;

@FunctionalInterface
public interface DoubleToLongFunctionWE<E extends Exception> {
    long applyAsLong(double value) throws E;
    
    default DoubleToLongFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleToLongFunction adapt(final DoubleToLongFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}  