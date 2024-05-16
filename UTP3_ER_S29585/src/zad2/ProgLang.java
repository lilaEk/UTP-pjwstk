package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private final Map<String, Set<String>> langsMap;
    private final Map<String, Set<String>> progsMap;

    public ProgLang(String file) {
        this.langsMap = new LinkedHashMap<>();
        this.progsMap = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] dividedLine = line.split("\t");
                String lang = dividedLine[0];

                for (int i = 1; i < dividedLine.length; i++) {
                    String prog = dividedLine[i];

                    langsMap.computeIfAbsent(lang, k -> new LinkedHashSet<>()).add(prog);
                    progsMap.computeIfAbsent(prog, k -> new LinkedHashSet<>()).add(lang);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Set<String>> getLangsMap() {
        return new LinkedHashMap<>(langsMap);
    }

    public Map<String, Set<String>> getProgsMap() {
        return new LinkedHashMap<>(progsMap);
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(langsMap, Comparator.<Map.Entry<String, Set<String>>>comparingInt(entry -> -entry.getValue().size())
                .thenComparing(Map.Entry::getKey));
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(progsMap, Comparator.<Map.Entry<String, Set<String>>>comparingInt(entry -> -entry.getValue().size())
                .thenComparing(Map.Entry::getKey));
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(progsMap, e -> e.getValue().size() > n);
    }

    private <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing, LinkedHashMap::new));
    }

    private <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing, LinkedHashMap::new));
    }
}