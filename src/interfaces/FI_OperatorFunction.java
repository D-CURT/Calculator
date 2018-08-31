package interfaces;

@FunctionalInterface
public interface FI_OperatorFunction<T> {
    T count(T first, T second);
}
