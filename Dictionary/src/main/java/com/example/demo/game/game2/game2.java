package com.example.demo.game.game2;

import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class game2 {
    // Hàm kiểm tra xem 2 từ có ký tự gần giống nhau hay không
    protected boolean areSimilar(String s1, String s2) {
        int differenceCount = 0;
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                differenceCount++;
                if (differenceCount > 1) {
                    return false;
                }
            }
        }
        return differenceCount == 1;
    }
    class UnionFind {
        private Map<String, String> parent;

        public UnionFind() {
            this.parent = new HashMap<>();
        }

        public String find(String s) {
            if (!parent.containsKey(s)) {
                parent.put(s, s);
            }

            if (!s.equals(parent.get(s))) {
                parent.put(s, find(parent.get(s)));
            }

            return parent.get(s);
        }

        public void union(String s1, String s2) {
            String root1 = find(s1);
            String root2 = find(s2);

            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }

    private static List<Answer> answers = new ArrayList<>();

    // Hàm đọc file và trả về danh sách từ
    public List<String> readFile(String path) {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                wordList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + path);
        }
        return wordList;
    }



    private boolean eq(String s1,String s2){
        return s1.equals(s2);
    }

    // Hàm thực hiện Union-Find với danh sách từ đã đọc
    public List<Answer> UnionFind(List<String> wordList) {
        UnionFind uf = new UnionFind();

        // Union các từ có ký tự gần giống nhau
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (areSimilar(wordList.get(i), wordList.get(j))) {
                    uf.union(wordList.get(i), wordList.get(j));
                }
            }
        }

        // Xây dựng danh sách các Answer từ các nhóm đã được tạo
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : wordList) {
            String root = uf.find(word);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(word);
        }

        // Chọn 4 từ từ mỗi nhóm để tạo Answer
        for (List<String> group : groups.values()) {
            if (group.size() >= 4) {
                int n = group.size();
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < i + n; j++) {
                        if (areSimilar(group.get(i), group.get(j % n))) {
                            String s1 = group.get(i);
                            String s2 = group.get(j % n);
                            for (int k = j + 1; k < j + n; k++) {
                                // s1#s2#s3#s4
                                String s3 = group.get(k % n);
                                if (areSimilar(group.get(j % n), group.get(k % n)) && !eq(s3,s1)) {
                                    for (int l = k + 1; l < k + n; l++) {
                                        if (areSimilar(group.get(l % n), group.get(k % n))
                                                && !eq(s1,group.get(l%n)) && !eq(s2,group.get(l%n))) {
                                            Answer s = new Answer(s1, s2, s3, group.get(l % n));
                                            answers.add(s);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answers;
    }

    // In ra kết quả
    public void printResult() {
        Collections.shuffle(answers);
        for (Answer answer : answers) {
            System.out.println("Answer: " + answer.getAnswer1() + ", " + answer.getAnswer2() + ", " +
                    answer.getAnswer3() + ", " + answer.getAnswer4());
        }
    }

    public static void main(String[] args) {
        game2 game = new game2();
        String path = "src\\main\\java\\com\\example\\demo\\game\\game2\\game2.2.txt";
        List<String> wordList = game.readFile(path);
        //game.UnionFind(wordList);
        //game.printResult();
        String s ="at";
        System.out.println(wordList.contains(s));

    }
}