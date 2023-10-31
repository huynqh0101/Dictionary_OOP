package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

class DictionaryManagement {
    private Dictionary dictionary;
    private Scanner scanner;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.scanner = new Scanner(System.in);
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" Số phần tử: ");
        System.out.print("e:english, v: vietnamese");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("e: ");
            String target = sc.nextLine();
            System.out.println("     ,v: ");
            String explain = sc.nextLine();
            Word s = new Word(target, explain);
            this.dictionary.addword(s);
        }
    }

    public void insertFromFile() {
        System.out.print("Enter file path (e.g.,dictionaries.txt): ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Word word = new Word(wordTarget, wordExplain);
                    this.dictionary.addword(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + fileName);
        }
    }

    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tra cứu:   Tiếng anh: ");
        String search = scanner.nextLine().trim();
        Word result = this.dictionary.findWordByTarget(search);
        if (result != null) {
            System.out.print("\n Tiếng anh: " + result.getWord_target());
            System.out.println("    - Tiếng việt: " + result.getWord_explain());
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }
    }

    // chưa bổ sung chức năng thêm, sửa và xóa dữ liệu từ vựng bằng dòng lệnh.
}
