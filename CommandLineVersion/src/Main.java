import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DictionaryManagement dictManager = new DictionaryManagement();

        dictManager.insertFromFileDictionaries();

        System.out.print("Welcome to My Application! \n" +
                "[0] Exit \n" +
                "[1] Add \n" +
                "[2] Remove \n" +
                "[3] Update \n" +
                "[4] Display \n" +
                "[5] Lookup \n" +
                "[6] Search \n" +
                "[7] Game \n" +
                "[8] Import from file \n \n");

        do {
            Scanner input = new Scanner(System.in);

            System.out.print("Your action(0-8): ");
            int action = input.nextInt();
            if (action == 0) break;
            switch (action) {
                case 1:
                    dictManager.insertFromCommandline();
                    break;
                case 2:
                    dictManager.removeWord();
                    break;
                case 3:
                    dictManager.updateWord();
                    break;
                case 4:
                    dictManager.showAllWords();
                    break;
                case 5:
                    dictManager.dictionaryLookup();
                    break;
                case 6:
                    dictManager.dictionarySearch();
                    break;
                case 7:
                    break;
                case 8:
                    dictManager.insertFromFile();
                    break;
                default: {
                    System.out.print("Your actions cannot be recognized! Please do it again!");
                    break;
                }
            }
            System.out.println();
        } while (true);

        dictManager.updateFileDictionaries();
    }
}