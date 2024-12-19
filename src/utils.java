import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.util.Scanner;
import java.util.HashSet;

public class utils {

    public static retroGame[] readCSV(String filename) throws IOException, InvalidGameFormatExcept, DupeGameExcept {
        retroGame[] games = new retroGame[5];
        int count = 0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length != 6) {
                    throw new InvalidGameFormatExcept("Invalid CSV format at line: \"" + line + "\"");
                }

                try {
                    retroGame newGame = new retroGame(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], parts[5]);

                    for (int i = 0; i < count; i++) {
                        if (games[i] != null && games[i].id.equals(newGame.id)) {
                            throw new DupeGameExcept("Duplicate game ID found: " + newGame.id);
                        }
                    }

                    if (count == games.length) {
                        retroGame[] largerArray = new retroGame[games.length * 2];
                        System.arraycopy(games, 0, largerArray, 0, games.length);
                        games = largerArray;
                    }

                    games[count++] = newGame;
                } catch (NumberFormatException e) {
                    throw new InvalidGameFormatExcept("Invalid number format in line: " + line + " "+e);
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        retroGame[] trimmedGames = new retroGame[count];
        System.arraycopy(games, 0, trimmedGames, 0, count);
        return trimmedGames;
    }

    public static void writeBinary(String filename, CollectorEdition[] collectorEditions) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(collectorEditions);
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    public static CollectorEdition[] readBinary(String filename) throws IOException, ClassNotFoundException, InvalidGameFormatExcept, DupeGameExcept {
        ObjectInputStream binFile = null;
        CollectorEdition[] collectorEditions = null;

        try {
            binFile = new ObjectInputStream(new FileInputStream(filename));

            Object obj = binFile.readObject();
            if (!(obj instanceof CollectorEdition[])) {
                throw new InvalidGameFormatExcept("Invalid binary file format: Expected CollectorEdition[], but found " + obj.getClass().getName());
            }

            collectorEditions = (CollectorEdition[]) obj;

            HashSet<String> seenIds = new HashSet<>();
            double highVal = 0;
            CollectorEdition mostVal = null;

            for (CollectorEdition collector : collectorEditions) {
                if (collector == null) continue;

                if (!seenIds.add(collector.id)) {
                    throw new DupeGameExcept("Duplicate game ID found in binary file: " + collector.id);
                }

                double value = collector.calcVal();
                if (value > highVal) {
                    highVal = value;
                    mostVal = collector;
                }
            }
        } catch (EOFException e) {
            System.out.println("End of file reached.");
        } catch (IOException e) {
            System.out.println("Error reading binary file: " + e.getMessage());
        } finally {
            if (binFile != null) {
                binFile.close();
            }
        }
        return collectorEditions;
    }
}