package mylibs.function;

import java.util.function.LongBinaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface LongBinaryOperatorWE<E extends Exception> {
    long applyAsLong(long left, long right) throws E;
    
    default LongBinaryOperator tryCatchWrapping() {
        return (left, right) -> {
            try {
                return applyAsLong(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongBinaryOperator adapt(final LongBinaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}