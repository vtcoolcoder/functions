package mylibs.function;

import java.util.function.BiPredicate;
import java.util.Objects;

@FunctionalInterface
public interface BiPredicateWE<T, U, E extends Exception> {
    boolean test(T t, U u) throws E;
    
    default BiPredicate<T, U> tryCatchWrapping() {
        return (t, u) -> {
            try {
                return test(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, U, E extends Exception> BiPredicate<T, U> adapt(final BiPredicateWE<T, U, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}