/**
 * @author Ekiert Rozalia S29585
 */

package zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream()));

        Map<String, List<String>> anagramMap = br.lines().collect(Collectors.groupingBy(
                word -> {
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                },
                LinkedHashMap::new,
                Collectors.toList()
        ));

        int maxAnagramCount = anagramMap.values().stream()
                .map(List::size)
                .max(Integer::compare)
                .orElse(0);

        anagramMap.values().stream()
                .filter(list -> list.size() == maxAnagramCount)
                .forEach(list -> System.out.println(String.join(" ", list)));
    }
}