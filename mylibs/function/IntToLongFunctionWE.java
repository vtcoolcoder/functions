package mylibs.function;

import java.util.function.IntToLongFunction;
import java.util.Objects;

@FunctionalInterface
public interface IntToLongFunctionWE<E extends Exception> {
    long applyAsLong(int value) throws E;
    
    default IntToLongFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntToLongFunction adapt(final IntToLongFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}