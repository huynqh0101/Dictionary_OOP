package com.example.demo.dictionary.game2;

import com.example.demo.dictionary.Word;

public class Data {
    private Word word;
    private int id;
    private int count;

    public Data() {

    }

    public Data(Word word, int id, int count) {
        this.word = word;
        this.id = id;
        this.count = count;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUF() {
        return this.word.getWordTarget();
    }

}
