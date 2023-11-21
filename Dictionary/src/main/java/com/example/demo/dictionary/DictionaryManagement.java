package com.example.demo.Dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromFile(Dictionary dictionary, String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String wordTarget = bufferedReader.readLine();
            wordTarget = wordTarget.replace("|", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(wordTarget.trim());
                String wordExplain = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (!line.startsWith("|")) wordExplain += line + "\n";
                    else {
                        wordTarget = line.replace("|", "");
                        break;
                    }
                word.setWordExplain(wordExplain.trim());
                dictionary.add(word);
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + path);
        }
    }

    public ObservableList<String> lookupWord(Dictionary dictionary, String key) {
        Iterator wordIterator = dictionary.iterator();
        ObservableList<String> list = FXCollections.observableArrayList();
        while (wordIterator.hasNext()) {
            Word word = (Word) wordIterator.next();
            String wordTarget = word.getWordTarget().toLowerCase();
            if (wordTarget.length() >= key.length()) {
                String firstLetter = wordTarget.substring(0, key.length());
                if (firstLetter.equals(key)) {
                    list.add(word.getWordTarget());
                }
            }
        }
        return list;
    }

    public int dictionarySearcher(Dictionary dictionary, String keyWord) {
        try {
            dictionary.sort(new Comparator<Word>() {
                @Override
                public int compare(Word word1, Word word2) {
                    return word1.getWordTarget().compareTo(word2.getWordTarget());
                }
            });
            int left = 0;
            int right = dictionary.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int check = dictionary.get(mid).getWordTarget().compareTo(keyWord);
                if (check == 0) return mid;
                if (check <= 0) left = mid + 1;
                else right = mid - 1;
            }
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
        return -1;
    }

    public void updateWord(Dictionary dictionary, int index, String meaning) {
        try {
            dictionary.get(index).setWordExplain(meaning);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void deleteWord(Dictionary dictionary, int index) {
        try {
            dictionary.remove(index);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void addWord(Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void exportToFile(Dictionary dictionary, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (Word word : dictionary) {
                bufferedWriter.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public boolean contains(Word word) {
        for (Word i : dictionary) {
            if (word.getWordTarget().equals(i.getWordTarget())) {
                return true;
            }
        }
        return false;
    }

    
    public void saveChangeToFile(String change, Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (change.equals("Delete")) {
                bufferedWriter.write("|Delete: " + word.getWordTarget() + "\n__________");
                bufferedWriter.newLine();
            } else if (change.equals("Edit")) {
                bufferedWriter.write("|Edit: " + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            } else if (change.equals("Add")) {
                bufferedWriter.write("|Add: " + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void insertFromFileChange(Dictionary dictionary, String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            if(line == null) return;
            String querry = line.split(":", 2)[0].trim();
            String wordTarget = line.split(":", 2)[1].trim();
            String wordExplain;
            String temp;
            while ((line = bufferedReader.readLine()) != null) {
                if (querry.contains("|Delete")) {
                    int index = dictionarySearcher(dictionary, wordTarget);
                    if (index != -1) {
                        deleteWord(dictionary, index);
                    }
                    line = bufferedReader.readLine();
                    if (line != null){
                        querry = line.split(":", 2)[0].trim();
                        wordTarget = line.split(":", 2)[1].trim();
                    }

                }
                else if (querry.contains("|Edit")) {
                    temp = wordTarget;
                    wordExplain = "";
                    do {
                        if (!line.startsWith("|")) wordExplain += line + "\n";
                        else {
                            querry = line.split(":", 2)[0].trim();
                            wordTarget = line.split(":", 2)[1].trim();
                            break;
                        }
                    } while ((line = bufferedReader.readLine()) != null);
                    int index = dictionarySearcher(dictionary, temp);
                    if (index != -1) {
                        updateWord(dictionary, index, wordExplain);
                    }
                }
                else if (querry.contains("|Add")) {
                    temp = wordTarget;
                    wordExplain = "";
                    do {
                        if (!line.startsWith("|")) wordExplain += line + "\n";
                        else {
                            querry = line.split(":", 2)[0].trim();
                            wordTarget = line.split(":", 2)[1].trim();
                            break;
                        }
                    } while ((line = bufferedReader.readLine()) != null);
                    Word word = new Word(temp, wordExplain);
                    dictionary.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}