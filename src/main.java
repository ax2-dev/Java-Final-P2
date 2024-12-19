import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter collectors file path:");
        String binPath = sc.nextLine();
        System.out.println("Enter games file path:");
        String gamesPath = sc.nextLine();
        try {

            System.out.println("Reading Retro Games from CSV:");
            retroGame[] retroGames = utils.readCSV(gamesPath);
            for (retroGame game : retroGames) {
                if (game != null) {
                    System.out.println("ID: " + game.id + ", Title: " + game.title + ", Value: $" + game.calcVal());
                }
            }

            System.out.println("\nMost Valuable Retro Game:");
            game mostValuableRetro = anaData.findMostValuable(retroGames);
            if (mostValuableRetro != null) {
                System.out.println("ID: " + mostValuableRetro.id + ", Value: $" + mostValuableRetro.calcVal());
            }

            System.out.println("\nSorting Retro Games by value:");
            anaData.sortGamesByValue(retroGames);
            for (retroGame game : retroGames) {
                if (game != null) {
                    System.out.println("ID: " + game.id + ", Value: $" + game.calcVal());
                }
            }

            System.out.println("\nReading Collectors Edition from binary file:");
            CollectorEdition[] readCollectorEditions = utils.readBinary(binPath);
            for (CollectorEdition game : readCollectorEditions) {
                if (game != null) {
                    System.out.println("ID: " + game.id + ", Title: " + game.title + ", Value: $" + game.calcVal());
                }
            }

            System.out.println("\nMost Valuable Collectors Edition:");
            game mostValuableCollector = anaData.findMostValuable(readCollectorEditions);
            if (mostValuableCollector != null) {
                System.out.println("ID: " + mostValuableCollector.id + ", Value: $" + mostValuableCollector.calcVal());
            }

        } catch (IOException e) {
            System.err.println("Error during file operation: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error during object deserialization: " + e.getMessage());
        } catch (InvalidGameFormatExcept | DupeGameExcept e) {
            System.err.println("Data error: " + e.getMessage());
        }
    }
}
