import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private ArrayList<Word> dictionary = new ArrayList<>();

    public DictionaryManagement() {
        dictionary = new ArrayList<>();
    }

    public void insertWord(String word_target, String word_explain) {
        Word newWord = new Word(word_target, word_explain);
        dictionary.add(newWord);
    }


    //thay doi

    public void insertFromCommandline(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String word_target = scanner.nextLine().trim();
            String word_explain = scanner.nextLine().trim();
            insertWord(word_target, word_explain);
        }
    }

    public void showAllWords(){
        System.out.format("%-5s %-10s %-10s\n",
                "NO  |",
                "English",
                "| Vietnamese");
        int count = 1;
        for (Word word : dictionary) {
            System.out.format("%-5s %-10s %-10s\n",
                    count++ + "   |",
                    word.getWord_target(),
                    "| " +word.getWord_explain());
        }
    }


    public void insertFromFile(String fileName) {
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
                System.out.println("Lỗi khi đọc tệp " + fileName);
            }
    }
    public void dictionaryLookup() {

    }
}
