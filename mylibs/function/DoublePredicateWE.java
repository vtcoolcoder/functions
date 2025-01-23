package mylibs.function;

import java.util.function.DoublePredicate;
import java.util.Objects;

@FunctionalInterface
public interface DoublePredicateWE<E extends Exception> {
    boolean test(double value) throws E;
    
    default DoublePredicate tryCatchWrapping() {
        return value -> {
            try {
                return test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> DoublePredicate adapt(final DoublePredicateWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}