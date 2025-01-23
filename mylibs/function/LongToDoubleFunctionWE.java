package mylibs.function;

import java.util.function.LongToDoubleFunction;
import java.util.Objects;

@FunctionalInterface
public interface LongToDoubleFunctionWE<E extends Exception> {
    double applyAsDouble(long value) throws E;
    
    default LongToDoubleFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongToDoubleFunction adapt(final LongToDoubleFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}