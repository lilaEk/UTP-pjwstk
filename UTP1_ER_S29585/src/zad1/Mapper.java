/**
 *
 *  @author Ekiert Rozalia S29585
 *
 */

package zad1;


public interface Mapper<T, R> {
    R map(T element);
}