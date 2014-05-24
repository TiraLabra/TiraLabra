package Types;

public interface ASDNumber<T> {
    public T add(T other);
    public T subtract(T other);
    
    public T multiply(T other);
    public T divide(T other);
    
    public T pow(T other);
}