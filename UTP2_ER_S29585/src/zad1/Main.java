/**
 * @author Ekiert Rozalia S29585
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
    public static void main(String[] args) {

        Function<String, List<String>> flines = (path) -> {
            try {
                List<String> rowsList = new ArrayList<>();
                String line;

                BufferedReader br = new BufferedReader(new FileReader(path));

                while ((line = br.readLine()) != null) {
                    rowsList.add(line);
                }
                br.close();

                return rowsList;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        Function<List<String>, String> join = (list) -> String.join("", list);

        Function<String, List<Integer>> collectInts = (s) -> {

            List<Integer> allInts = new ArrayList<>();
            StringBuilder currentInt = new StringBuilder();

            for (char ch : s.toCharArray()) {
                if (Character.isDigit(ch)) {
                    currentInt.append(ch);
                } else if (currentInt.length() > 0) {
                    allInts.add(Integer.parseInt(currentInt.toString()));
                    currentInt.setLength(0);
                }
            }

            if (currentInt.length() > 0) {
                allInts.add(Integer.parseInt(currentInt.toString()));
            }

            return allInts;
        };

        Function<List<Integer>, Integer> sum = (ints) -> {
            int intSum = 0;
            for (int n : ints) {
                intSum += n;
            }
            return intSum;
        };

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

    }
}
