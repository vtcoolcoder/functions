package mylibs.function;

import java.util.function.IntSupplier;
import java.util.Objects;

@FunctionalInterface
public interface IntSupplierWE<E extends Exception> {
    int getAsInt() throws E;
    
    default IntSupplier tryCatchWrapping() {
        return () -> {
            try {
                return getAsInt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntSupplier adapt(final IntSupplierWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}