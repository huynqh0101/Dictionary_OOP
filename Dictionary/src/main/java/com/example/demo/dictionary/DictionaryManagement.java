package com.example.demo.dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;
    private Scanner scanner;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.scanner = new Scanner(System.in);
    }

    public void insertFromCommandline() {
        System.out.print("Nhập số lượng từ vựng: ");
        int n = this.scanner.nextInt();
        this.scanner.nextLine();

        for(int i = 0; i < n; ++i) {
            System.out.println("Nhập từ vựng thứ " + (i + 1) + ":");
            System.out.print("Tiếng Anh: ");
            String wordTarget = this.scanner.nextLine();
            System.out.print("Tiếng Việt: ");
            String wordExplain = this.scanner.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            this.dictionary.addWord(word);
            System.out.println("Từ đã được thêm vào từ điển.");
        }

    }

    // insert file từ bên ngopaif vào
    public void insertFromFile() {
        System.out.print("Enter file path (e.g., dictionaries.txt): ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Word word = new Word(wordTarget, wordExplain);
                    this.dictionary.addWord(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + fileName);
        }
    }

    //tự động lấy dữ liệu khi chạy chương trình
    public void insertFromFileDictionaries() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Huy\\IdeaProjects\\Dictionary_OOP\\dictionaries.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Word word = new Word(wordTarget, wordExplain);
                    this.dictionary.addWord(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + "dictionaries.txt");
        }
    }

    public void dictionaryLookup1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ tiếng Anh cần tra cứu: ");
        String searchWord = scanner.nextLine().trim();
        Word result = this.dictionary.findWordByTarget(searchWord);
        if (result != null) {
            System.out.println("Từ vựng tiếng Anh: " + result.getWordTarget());
            System.out.println("Giải nghĩa tiếng Việt: " + result.getWordExplain());
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }

        //scanner.close();
    }

    public void dictionaryLookup2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập giải thích tiếng Việt cần tra cứu: ");
        String searchWordExplain = scanner.nextLine().trim();
        Word result = this.dictionary.findWordByExplain(searchWordExplain);
        if (result != null) {
            System.out.println("Từ vựng tiếng Anh: " + result.getWordTarget());
            System.out.println("Giải nghĩa tiếng Việt: " + result.getWordExplain());
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }

       // scanner.close();
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần tra cứu: ");
        String searchLetter = scanner.nextLine().toLowerCase();
        boolean found = false;
        Iterator var4 = this.dictionary.getWords().iterator();

        while(var4.hasNext()) {
            Word word = (Word)var4.next();
            String firstLetter = word.getWordTarget().substring(0, searchLetter.length()).toLowerCase();
            if (firstLetter.equals(searchLetter)) {
                System.out.println("Từ vựng tiếng Anh: " + word.getWordTarget());
                System.out.println("Giải nghĩa tiếng Việt: " + word.getWordExplain());
                System.out.println("-----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy từ nào có chữ cái đầu là '" + searchLetter + "'.");
        }

        //scanner.close();
    }

    public void addWordFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ tiếng Anh: ");
        String wordTarget = scanner.nextLine();
        System.out.print("Nhập giải thích tiếng Việt: ");
        String wordExplain = scanner.nextLine();
        Word word = new Word(wordTarget, wordExplain);
        this.dictionary.addWord(word);
        System.out.println("Từ đã được thêm vào từ điển.");
        //scanner.close();
    }

    public void deleteWordFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ tiếng Anh cần xóa: ");
        String searchWordTarget = scanner.nextLine();
        Word wordToDelete = this.dictionary.findWordByTarget(searchWordTarget);
        if (wordToDelete != null) {
            this.dictionary.removeWord(wordToDelete);
            System.out.println("Từ đã được xóa khỏi từ điển.");
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }

        //scanner.close();
    }

    public void editWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ tiếng Anh cần sửa: ");
        String searchWordTarget = scanner.nextLine();
        Word wordToEdit = this.dictionary.findWordByTarget(searchWordTarget);
        if (wordToEdit != null) {
            System.out.print("Nhập từ tiếng Anh mới: ");
            String newWordTarget = scanner.nextLine();
            System.out.print("Nhập giải thích tiếng Việt mới: ");
            String newWordExplain = scanner.nextLine();
            wordToEdit.setWordTarget(newWordTarget);
            wordToEdit.setWordExplain(newWordExplain);
            System.out.println("Từ đã được sửa.");
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Huy\\IdeaProjects\\Dictionary_OOP\\dictionaries.txt");
            List<Word> words = this.dictionary.getWords();
            Iterator var3 = words.iterator();

            while(var3.hasNext()) {
                Word word = (Word)var3.next();
                String wordTarget = word.getWordTarget();
                String wordExplain = word.getWordExplain();
                fileWriter.write(wordTarget + "\t" + wordExplain + "\n");
            }

            fileWriter.close();
            System.out.println("Dữ liệu từ điển đã được xuất ra tệp thành công.");
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }
}