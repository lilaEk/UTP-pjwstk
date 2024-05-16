/**
 *
 *  @author Ekiert Rozalia S29585
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {
    private final List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Selector<T> sel) {
        List<T> result = new ArrayList<>();

        for (T element : list) {
            if (sel.select(element)) {
                result.add(element);
            }
        }
        return new ListCreator<>(result);
    }

    public <R> List<R> mapEvery(Mapper<T, R> map) {
        List<R> result = new ArrayList<>();

        for (T element : list) {
            result.add(map.map(element));
        }
        return result;
    }
}  
