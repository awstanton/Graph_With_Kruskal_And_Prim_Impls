
package javaapp3;


public interface SetInf<T> {
    boolean insert(T element);
    boolean search(T element);
    boolean delete(T element);
}
/*
abstract class DataSet<T> implements SetInf<T> {
    protected T elements[];
    protected int size;
    @Override
    public abstract boolean insert(T element);
    @Override
    public abstract T search(T element);
    @Override
    public abstract boolean delete(T element);
}
*/