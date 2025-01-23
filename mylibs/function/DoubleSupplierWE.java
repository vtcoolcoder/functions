package mylibs.function;

import java.util.function.DoubleSupplier;
import java.util.Objects;

@FunctionalInterface
public interface DoubleSupplierWE<E extends Exception> {
    double getAsDouble() throws E;
    
    default DoubleSupplier tryCatchWrapping() {
        return () -> {
            try {
                return getAsDouble();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoubleSupplier adapt(final DoubleSupplierWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}