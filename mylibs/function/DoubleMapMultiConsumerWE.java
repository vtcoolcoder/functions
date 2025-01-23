package mylibs.function;


import static java.util.stream.DoubleStream.DoubleMapMultiConsumer;

import java.util.Objects;


@FunctionalInterface
public interface DoubleMapMultiConsumerWE<E extends Exception> {
    void accept(double value, DoubleConsumerWE<?> dc) throws E;
    
    
    private DoubleMapMultiConsumerWE<E> init() {
        return (value, dc) -> {
            try {
                accept(value, dc);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    
    default DoubleMapMultiConsumer tryCatchWrapping() {
        return (value, dc) -> init();
    }
   
    
    static <E extends Exception> DoubleMapMultiConsumer adapt(final DoubleMapMultiConsumerWE<E> func) {
        Objects.requireNonNull(func);
        return func.tryCatchWrapping();
    }
}





