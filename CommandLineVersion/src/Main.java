public class Main {
    public static void main(String[] args) {
        DictionaryManagement dictManager = new DictionaryManagement();
        //dictManager.insertFromCommandline();
        dictManager.insertFromFile("D:\\Codeeeee\\OOP\\Dictionary_OOP\\CommandLineVersion\\src\\dictionaries.txt");
        dictManager.showAllWords();
    }
}