package mylibs.function;

import java.util.function.IntUnaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface IntUnaryOperatorWE<E extends Exception> {
    int applyAsInt(int value) throws E;
    
    default IntUnaryOperator tryCatchWrapping() {
        return value -> {
            try {
                return applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntUnaryOperator adapt(final IntUnaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}