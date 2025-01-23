package mylibs.function;

import java.util.function.DoubleBinaryOperator;
import java.util.Objects;

@FunctionalInterface
public interface DoubleBinaryOperatorWE<E extends Exception> {
    double applyAsDouble(double left, double right) throws E;
    
    default DoubleBinaryOperator tryCatchWrapping() {
        return (left, right) -> {
            try {
                return applyAsDouble(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleBinaryOperator adapt(final DoubleBinaryOperatorWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}
