/**
 * @author Ekiert Rozalia S29585
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    String path;

    public Finder(String fname) {
        this.path = fname;
    }

    public int getIfCount() {
        int counter = 0;
        boolean isCode = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//"));
                }

                if (isCode) {
                    counter += countOccurrences(line, "\\bif\\b");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    public int getStringCount(String word) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {

                    counter += countOccurrences(line, word);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    private int countOccurrences(String line, String regex) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {

            count++;
        }

        return count;
    }
}
