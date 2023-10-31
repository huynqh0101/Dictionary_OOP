package Dictionary;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandLine he = new DictionaryCommandLine(dictionary,dictionaryManagement);
        he.showAllWords();
    }
}
