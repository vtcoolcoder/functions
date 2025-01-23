package mylibs.function;

import java.util.function.DoubleToIntFunction;
import java.util.Objects;

@FunctionalInterface
public interface DoubleToIntFunctionWE<E extends Exception> {
    int applyAsInt(double value) throws E;
    
    default DoubleToIntFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleToIntFunction adapt(final DoubleToIntFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}