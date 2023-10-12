package com.example.demo.dictionary;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline(Dictionary dictionary, DictionaryManagement dictionaryManagement) {
        this.dictionary = dictionary;
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        List<Word> words = this.dictionary.getWords();
        words.sort(Comparator.comparing(Word::getWordTarget));
        System.out.println("No | English    | Vietnamese");

        for(int i = 0; i < words.size(); ++i) {
            Word word = (Word)words.get(i);
            System.out.printf("%-3d| %-10s | %7s%n", i + 1, word.getWordTarget(), word.getWordExplain());
        }
    }

    public void dictionaryBasic() {
        this.dictionaryManagement.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting the application.");
                        break;
                    case 1:
                        this.dictionaryManagement.addWordFromCommandLine();
                        break;
                    case 2:
                        this.dictionaryManagement.deleteWordFromCommandLine();
                        break;
                    case 3:
                        this.dictionaryManagement.editWord();
                        break;
                    case 4:
                        this.showAllWords();
                        break;
                    case 5:
                        this.dictionaryManagement.dictionaryLookup1();
                        break;
                    case 6:
                        this.dictionaryManagement.dictionarySearcher();
                    case 7:
                        break;
                    case 8:
                        this.dictionaryManagement.insertFromFile();
                        break;
                    case 9:
                        this.dictionaryManagement.dictionaryExportToFile();
                        break;
                    default:
                        System.out.println("Action not supported.");
                }
            } else {
                System.out.println("Action not supported.");
                scanner.nextLine();
            }
        } while(choice != 0);

        scanner.close();
    }
}
