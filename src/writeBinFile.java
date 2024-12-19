import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class writeBinFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bin (1) or CSV (2): ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Filename: ");
        String fileName = sc.nextLine();
        System.out.println("Test Error (y/n): ");
        String test = sc.nextLine();

        /* System.out.println("ID: ");
           String ID = sc.nextLine();
           System.out.println("Title: ");
           String Title = sc.nextLine();
           System.out.println("Release Year: ");
           int RYear = sc.nextInt();
           System.out.println("Publisher: ");
           String Publisher = sc.nextLine();
           System.out.println("Condition: ");
           String Condition = sc.nextLine();
           System.out.println("Platform: ");
           String Platform = sc.nextLine();
           System.out.println("Special Feature: ");
           String specFeat = sc.nextLine();
           System.out.println("Packaging: ");
           String Packaging = sc.nextLine();
           System.out.println("Limit #: ");
           int LimitNum = sc.nextInt();
        */

        if (choice == 1) {
            try{
                if (test.equals("n")) {
                    CollectorEdition[] collectorEditions = new CollectorEdition[2];
                    //CollectorEdition[] collectorEditions = new CollectorEdition[1];
                    collectorEditions[0] = new CollectorEdition("C001", "Rare Mario", 1985, "Nintendo", "EXCELLENT", "Gameboy", "Gold Flavored Cartridge", "SPECIAL", 100);
                    collectorEditions[1] = new CollectorEdition("C002", "John Pork", 200, "God", "GOOD", "Space and Time", "Unlimited Power", "STANDARD", 1);
                    //collectorEditions[0] = new CollectorEdition(ID, Title, RYear, Publisher, Condition, Platform, specFeat, Packaging, LimitNum);

                    utils.writeBinary(fileName, collectorEditions);
                } else{
                    ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName));
                    CollectorEdition[] collectorEditions = new CollectorEdition[2];
                    collectorEditions[0] = new CollectorEdition("C001", "Rare Mario", 1985, "Nintendo", "EXCELLENT", "Gameboy", "Gold Flavored Cartridge", "SPECIAL", 100);
                    collectorEditions[1] = new CollectorEdition("C001", "Rare Mario", 1985, "Nintendo", "EXCELLENT", "Gameboy", "Gold Flavored Cartridge", "SPECIAL", 100);
                    utils.writeBinary(fileName, collectorEditions);
                    //outStream.writeObject("This is invalid data");
                    //outStream.close();
                }
            } catch (IOException e) {
                System.err.println("Error during file operation: " + e.getMessage());
            }
        }else {
            // I looked this part up because I couldn't get it to work properly
            // It has no effect on the actual project I just wanted to have a nice sister program to automate testing
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(fileName));
                writer.println("id,title,releaseYear,publisher,condition,platform");

                if (test.equals("n")) {

                    writer.println("001,Super Mario Bros,1985,Nintendo,GOOD,NES");
                    writer.println("002,Sonic the Hedgehog,1991,Sega,EXCELLENT,GENESIS");
                } else {

                    writer.println("002,Sonic the Hedgehog,1991,Sega,EXCELLENT,GENESIS");
                    writer.println("002,Sonic the Hedgehog,1991,Sega,EXCELLENT,GENESIS");
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}
