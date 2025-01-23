package mylibs.function;

import java.util.function.BooleanSupplier;
import java.util.Objects;

@FunctionalInterface
public interface BooleanSupplierWE<E extends Exception> {
    boolean getAsBoolean() throws E;
    
    default BooleanSupplier tryCatchWrapping() {
        return () -> {
            try {
                return getAsBoolean();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> BooleanSupplier adapt(final BooleanSupplierWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}
