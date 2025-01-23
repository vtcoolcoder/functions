package mylibs.function;

import java.util.function.IntBinaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface IntBinaryOperatorWE<E extends Exception> {
    int applyAsInt(int left, int right) throws E;
    
    default IntBinaryOperator tryCatchWrapping() {
        return (left, right) -> {
            try {
                return applyAsInt(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntBinaryOperator adapt(final IntBinaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}