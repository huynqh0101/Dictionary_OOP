package Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Dictionary{
    List<Word> words =new ArrayList<>();
    public Dictionary(){
    }

    public List<Word> getWords() {
        return this.words;
    }

    public void addword(Word word){
        this.words.add(word);
    }
}
