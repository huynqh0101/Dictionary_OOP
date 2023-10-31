package Dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Dictionary {
    List<Word> words = new ArrayList<>();

    public Dictionary() {
    }

    public List<Word> getWords() {
        return this.words;
    }

    public void addword(Word word) {
        this.words.add(word);
    }

    //code nqh

    public Word findWordByTarget(String word_target) {
        Iterator var2 = this.words.iterator();

        Word word;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            word = (Word) var2.next();
        } while (!word.getWord_target().equalsIgnoreCase(word_target));

        return word;
    }

    //code nqh
    public Word findWordByExplain(String word_explain) {
        Iterator var2 = this.words.iterator();

        Word word;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            word = (Word) var2.next();
        } while (!word.getWord_explain().equalsIgnoreCase(word_explain));

        return word;
    }


}
