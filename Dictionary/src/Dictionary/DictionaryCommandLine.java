package Dictionary;

import java.util.Collections;
public class DictionaryCommandLine {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandLine(Dictionary dictionary, DictionaryManagement dictionaryManagement) {
        this.dictionary = dictionary;
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        int s = dictionary.word.size();
        Collections.sort(dictionary.word, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }
}
