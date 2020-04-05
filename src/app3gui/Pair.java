
package app3gui;

/**
 *
 * @author Andrew
 */
public class Pair<T, V> {
    T value1;
    V value2;
    public Pair(T value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    void setPair(T value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
