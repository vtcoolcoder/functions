package mylibs.function;

import java.util.function.ToDoubleBiFunction;
import java.util.Objects;

@FunctionalInterface
public interface ToDoubleBiFunctionWE<T, U, E extends Exception> {
    double applyAsDouble(T t, U u) throws E;
    
    default ToDoubleBiFunction<T, U> tryCatchWrapping() {
        return (t, u) -> {
            try {
                return applyAsDouble(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <T, U, E extends Exception> ToDoubleBiFunction<T, U> adapt(final ToDoubleBiFunctionWE<T, U, E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}