package mylibs.function;

import java.util.Objects;

@FunctionalInterface
public interface RunnableWE<E extends Exception> {
    void run() throws E;
    
    default Runnable tryCatchWrapping() {
        return () -> {
            try {
                run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    static <E extends Exception> Runnable adapt(final RunnableWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}