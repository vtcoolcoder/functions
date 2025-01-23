package mylibs.function;

@FunctionalInterface
public interface BinaryOperatorWE<T, E extends Exception> extends BiFunctionWE<T, T, T, E> {

}