package mylibs.function;

import java.util.function.IntToDoubleFunction;
import java.util.Objects;

@FunctionalInterface
public interface IntToDoubleFunctionWE<E extends Exception> {
    double applyAsDouble(int value) throws E;
    
    default IntToDoubleFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntToDoubleFunction adapt(final IntToDoubleFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}