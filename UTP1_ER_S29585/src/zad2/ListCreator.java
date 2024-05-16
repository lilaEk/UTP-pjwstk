/**
 *
 *  @author Ekiert Rozalia S29585
 *
 */

package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListCreator<T> {

    private final List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Function<T, Boolean> sel) {
        List<T> result = new ArrayList<>();

        for (T element : list) {
            if (sel.apply(element)) {
                result.add(element);
            }
        }
        return new ListCreator<>(result);
    }

    public <R> List<R> mapEvery(Function<T, R> map) {
        List<R> result = new ArrayList<>();

        for (T element : list) {
            result.add(map.apply(element));
        }
        return result;
    }
}