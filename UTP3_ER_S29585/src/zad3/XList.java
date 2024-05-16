package zad3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    public XList(Collection<T> collection) {
        super(collection);
    }

    public XList(T... elements) {
        super(Arrays.asList(elements));
    }

    //OF
    public static <T> XList<T> of(T... elements) {
        return new XList<T>(elements);
    }

    public static XList<Integer> of(Set<Integer> set) {
        return new XList<>(set);
    }

    public static <T> XList<T> of(List<T>... lists) {
        XList<T> result = new XList<>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    //TOKENS OF
    public static XList tokensOf(String string) {
        return XList.of(string.split(" "));
    }

    public static XList<String> tokensOf(String string, String separator) {
        return XList.of(string.split(separator));
    }

    //CHARS OF
    public static XList charsOf(String number) {
        char[] charArray = number.toCharArray();
        XList<Character> result = new XList<>();
        for (char c : charArray) {
            result.add(c);
        }
        return result;
    }

    //UNION
    public XList<T> union(Collection<T> collection) {
        XList<T> result = XList.of(this);
        result.addAll(collection);
        return result;
    }

    public XList<T> union(T... elements) {
        XList<T> result = XList.of(this);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public static XList union(List<String> e1, List<String> e2, XList<String> e3) {
        XList<List<String>> result = new XList<>();

        result.add(e1);
        result.add(e2);
        result.add(e3);

        return result;
    }

    //COMBINE
    public XList<XList<String>> combine() {
        XList<XList<String>> result = new XList<>();

        List<T> list1 = new ArrayList<>((Collection) this.get(0));
        List<T> list2 = new ArrayList<>((Collection) this.get(1));
        List<T> list3 = new ArrayList<>((Collection) this.get(2));


        for (int i = 0; i < list3.size(); i++) {
            T elementFromList3 = list3.get(i);

            for (int j = 0; j < list2.size(); j++) {
                T elementFromList2 = list2.get(j);

                for (int k = 0; k < list1.size(); k++) {
                    T elementFromList1 = list1.get(k);

                    result.add(XList.of(
                            String.valueOf(elementFromList1),
                            String.valueOf(elementFromList2),
                            String.valueOf(elementFromList3)
                    ));
                }
            }
        }
        return result;
    }

    //DIFF
    public XList<T> diff(Collection<T> collection) {
        XList<T> result = new XList<>(this);
        result.removeAll(collection);
        return result;
    }

    //UNIQUE
    public XList<T> unique() {
        return new XList<>(new LinkedHashSet<>(this));
    }

    //COLLECT
    public <R> XList<R> collect(Function<T, R> mapper) {
        return this.stream()
                .map(mapper)
                .collect(Collectors.toCollection(XList::new));
    }

    //JOIN
    public String join(String separator) {
        return this.stream()
                .map(Object::toString)
                .collect(Collectors.joining(separator));
    }

    public String join() {
        return this.join("");
    }

    //FOR EACH WITH INDEX
    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(get(i), i);
        }
    }
}