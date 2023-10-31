package Dictionary;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DictionaryCommandLine {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandLine(Dictionary dictionary, DictionaryManagement dictionaryManagement) {
        this.dictionary = dictionary;
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        List<Word> words = this.dictionary.getWords();
        words.sort(Comparator.comparing(Word::getWord_target));
        System.out.println("No | English    | Vietnamese");

        for (int i = 0; i < words.size(); ++i) {
            Word word = (Word) words.get(i);
            System.out.printf("%-3d| %-10s | %7s%n", i + 1, word.getWord_target(), word.getWord_explain());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public void dictionarySearcher() {

    }
}
