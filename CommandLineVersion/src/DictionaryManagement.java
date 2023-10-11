import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DictionaryManagement class handles the management of a dictionary.
 */
public class DictionaryManagement {
    private ArrayList<Word> dictionary = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    /**
     * Constructs a new DictionaryManagement object.
     */
    public DictionaryManagement() {
        dictionary = new ArrayList<>();
    }

    /**
     * Inserts words from the dictionary file(dictionaries.txt) into the ArrayList dictionary.
     */
    public void insertFromFileDictionaries() {
        try (BufferedReader br = new BufferedReader(new FileReader("dictionaries.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String word = parts[0].trim();
                    String explanation = parts[1].trim();
                    insertWord(word, explanation);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + "dictionaries.txt");
        }
    }

    /**
     * Inserts a new word and its explanation into Arraylist dictionary.
     *
     * @param word_target  The word to insert.
     * @param word_explain The explanation of the word.
     */

    public void insertWord(String word_target, String word_explain) {
        Word newWord = new Word(word_target, word_explain);
        dictionary.add(newWord);
    }

    /**
     * Clears the content of the dictionary file(dictionaries.txt).
     */
    public void clearFileDictionaries() {
        String fileName = "dictionaries.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error while updating the file " + fileName);
        }
    }

    /**
     * Updates the content of the dictionary file(dictionaries.txt)
     * with the current dictionary data.
     */
    public void updateFileDictionaries() {
        clearFileDictionaries();
        String fileName = "dictionaries.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Word w : dictionary) {
                writer.write(w.getWord_target() + "\t" + w.getWord_explain());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error while updating the file " + fileName);
        }
    }

    /**
     * Inserts words from the command line into the dictionary.
     */
    public void insertFromCommandline() {
        System.out.print("Enter the number of words: ");
        int n = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter word " + i + ": ");
            String word_target = input.nextLine().trim();
            System.out.print("Enter the explanation of the word: ");
            String word_explain = input.nextLine().trim();
            insertWord(word_target, word_explain);
        }
    }

    /**
     * Removes a word from the dictionary.
     */
    public void removeWord() {
        System.out.print("Enter the word to remove: ");
        String wordToRemove = input.nextLine().trim();

        for (int i = 0; i < dictionary.size(); i++) {
            Word word = dictionary.get(i);
            if (word.getWord_target().equalsIgnoreCase(wordToRemove)) {
                dictionary.remove(i);
                System.out.println("Removed");
                return;
            }
        }
        System.out.println("Can't find!!!");
    }

    /**
     * Updates the explanation of a word in the dictionary.
     */
    public void updateWord() {
        System.out.print("Enter the word to update: ");
        String wordToUpdate = input.nextLine().trim();

        System.out.print("Enter explanation: ");
        String newExplanation = input.nextLine().trim();

        for (Word word : dictionary) {
            if (word.getWord_target().equalsIgnoreCase(wordToUpdate)) {
                word.setWord_explain(newExplanation);
                System.out.println("Updated");
                return;
            }
        }
        System.out.println("Can't find!!!");
    }

    /**
     * Displays all words in the dictionary.
     */
    public void showAllWords() {
        System.out.format("%-4s %-1s %-25s %-10s\n",
                "NO",
                "|",
                "English",
                "| Vietnamese");
        int count = 1;
        for (Word word : dictionary) {
            System.out.format("%-4s %-1s %-25s %-10s\n",
                    count,
                    "|",
                    word.getWord_target(),
                    "| " + word.getWord_explain());
            count++;
        }
    }

    /**
     * Looks up a word in the dictionary and displays its explanation.
     */
    public void dictionaryLookup() {
        System.out.print("Enter the word to look up: ");
        String word_target = input.nextLine().trim();

        for (Word w : dictionary) {
            if (w.getWord_target().equalsIgnoreCase(word_target)) {
                System.out.println("Word: " + w.getWord_target());
                System.out.println("Explanation: " + w.getWord_explain());
                return;
            }
        }
        System.out.println("Can't find!!!");
    }

    /**
     * Searches for words containing a specified substring in the dictionary and displays matching words.
     */
    public void dictionarySearch() {
        System.out.print("Enter the word to search: ");
        String word_target = input.nextLine().trim();

        int count = 1;
        System.out.format("%-4s %-1s %-25s %-10s\n",
                "NO",
                "|",
                "English",
                "| Vietnamese");
        for (Word word : dictionary) {
            if (word.getWord_target().contains(word_target)) {
                System.out.format("%-4s %-1s %-25s %-10s\n",
                        count,
                        "|",
                        word.getWord_target(),
                        "| " + word.getWord_explain());
                count++;
            }
        }
        if (count == 1)
            System.out.println("Can't find!!!");
    }

    /**
     * Inserts words from a specified file into the dictionary.
     */
    public void insertFromFile() {
        System.out.print("Enter file path (e.g., D:\\Codeeeee\\OOP\\Dictionary_OOP\\CommandLineVersion\\src\\dictionaries.txt): ");
        String fileName = input.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String word = parts[0].trim();
                    String explanation = parts[1].trim();
                    insertWord(word, explanation);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + fileName);
        }
    }
}
