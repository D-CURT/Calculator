package interfaces.functional_interfaces;

@FunctionalInterface
public interface FI_Function_count<T extends Number> {
    T count(T t);
}
