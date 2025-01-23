package mylibs.function;

import java.util.function.IntPredicate;
import java.util.Objects;

@FunctionalInterface
public interface IntPredicateWE<E extends Exception> {
    boolean test(int value) throws E;
    
    default IntPredicate tryCatchWrapping() {
        return value -> {
            try {
                return test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> IntPredicate adapt(final IntPredicateWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}