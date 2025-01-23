package mylibs.function;

import java.util.function.LongToIntFunction;
import java.util.Objects;

@FunctionalInterface
public interface LongToIntFunctionWE<E extends Exception> {
    int applyAsInt(long value) throws E;
    
    default LongToIntFunction tryCatchWrapping() {
        return value -> {
            try {
                return applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongToIntFunction adapt(final LongToIntFunctionWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}