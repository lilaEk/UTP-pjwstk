package zad1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            Path resultFilePath = Paths.get(resultFileName);

            try (BufferedWriter writer = Files.newBufferedWriter(resultFilePath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.toString().endsWith(".txt")) {

                            List<String> lines = Files.readAllLines(file, Charset.forName("Cp1250"));

                            for (String line : lines) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                });

            } catch (IOException e) {
                System.err.println("Błąd podczas otwierania lub zapisu UTP6res: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (InvalidPathException e) {
            System.err.println("Nieprawidłowa ścieżka katalogu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
