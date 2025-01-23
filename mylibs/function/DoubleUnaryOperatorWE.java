package mylibs.function;

import java.util.function.DoubleUnaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface DoubleUnaryOperatorWE<E extends Exception> {
    double applyAsDouble(double value) throws E;
    
    default DoubleUnaryOperator tryCatchWrapping() {
        return value -> {
            try {
                return applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleUnaryOperator adapt(final DoubleUnaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}