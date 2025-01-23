package mylibs.function;

import java.util.function.Predicate;
import java.util.Objects;

@FunctionalInterface
public interface PredicateWE<T, E extends Exception> {
    boolean test(T t) throws E;
    
    default Predicate<T> tryCatchWrapping() {
        return arg -> {
            try {
                return test(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> Predicate<T> adapt(final PredicateWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}