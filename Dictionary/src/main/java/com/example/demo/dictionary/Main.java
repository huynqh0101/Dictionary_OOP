package com.example.demo.dictionary;

public class Main {
    public Main() {
    }
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement management = new DictionaryManagement(dictionary);
        management.insertFromFileDictionaries();
        DictionaryCommandline commandline = new DictionaryCommandline(dictionary, management);
        commandline.dictionaryAdvanced();
    }
}
