package mylibs.function;

import java.util.function.ToDoubleFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToDoubleFunctionWE<T, E extends Exception> {
    double applyAsDouble(T value) throws E;
    
    default ToDoubleFunction<T> tryCatchWrapping() {
        return value -> {
            try {
                return applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, E extends Exception> ToDoubleFunction<T> adapt(final ToDoubleFunctionWE<T, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}