package mylibs.function;

import java.util.function.LongUnaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface LongUnaryOperatorWE<E extends Exception> {
    long applyAsLong(long value) throws E;
    
    default LongUnaryOperator tryCatchWrapping() {
        return value -> {
            try {
                return applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongUnaryOperator adapt(final LongUnaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}