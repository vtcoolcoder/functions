package mylibs.function;

import java.util.function.LongSupplier;
import java.util.Objects;

@FunctionalInterface
public interface LongSupplierWE<E extends Exception> {
    long getAsLong() throws E;
    
    default LongSupplier tryCatchWrapping() {
        return () -> {
            try {
                return getAsLong();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongSupplier adapt(final LongSupplierWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
} 