package mylibs.function;

import java.util.function.LongPredicate;
import java.util.Objects;

@FunctionalInterface
public interface LongPredicateWE<E extends Exception> {
    boolean test(long value) throws E;
    
    default LongPredicate tryCatchWrapping() {
        return value -> {
            try {
                return test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> LongPredicate adapt(final LongPredicateWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}