/**
 *
 *  @author Ekiert Rozalia S29585
 *
 */

package zad1;


import java.util.*;

public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(3, 5, 6, 7, 12);
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("55555", "1", "4444");
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {

        Selector<Integer> sel = new Selector<Integer>() {
            @Override
            public boolean select(Integer element) {
                return element < 10;
            }
        };
        Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer element) {
                return element + 10;
            }
        };
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public List<Integer> test2(List<String> src) {
        Selector<String> sel = new Selector<String>() {
            @Override
            public boolean select(String element) {

                return element.length() >3;
            }
        };

        Mapper<String, Integer> map = new Mapper<String, Integer>() {
            @Override
            public Integer map(String element) {
                return element.length()+10;
            }
        };

        return ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public static void main(String[] args) {
        new Main();
    }
}
