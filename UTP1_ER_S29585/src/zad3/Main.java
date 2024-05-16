/**
 * @author Ekiert Rozalia S29585
 */

package zad3;


import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );

        double ratePLNvsEUR = 4.30;

        List<String> result = dest
                .stream()
                .filter((String row) -> row.startsWith("WAW"))
                .map((String row) -> {
                    String[] elements = row.split(" ");
                    int roundedPrice = (int) Math.round(Double.parseDouble(elements[2]) * ratePLNvsEUR);
                    String priceInPLN = String.valueOf(roundedPrice);
                    return String.format("to %s - price in PLN: %s", elements[1], priceInPLN);
                })
                .collect(Collectors.toList());

        for (String r : result) System.out.println(r);
    }
}
