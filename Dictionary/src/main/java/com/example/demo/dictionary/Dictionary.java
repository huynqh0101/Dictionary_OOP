package com.example.demo.dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionary {
    private List<Word> words = new ArrayList();

    public Dictionary() {

    }

    public void addWord(Word word) {
        this.words.add(word);
    }

    public List<Word> getWords() {
        return this.words;
    }

    public Word findWordByTarget(String word_target) {
        Iterator var2 = this.words.iterator();

        Word word;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            word = (Word)var2.next();
        } while(!word.getWordTarget().equalsIgnoreCase(word_target));

        return word;
    }

    public Word findWordByExplain(String word_explain) {
        Iterator var2 = this.words.iterator();

        Word word;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            word = (Word)var2.next();
        } while(!word.getWordExplain().equalsIgnoreCase(word_explain));

        return word;
    }

    public void removeWord(Word word) {
        this.words.remove(word);
    }
}