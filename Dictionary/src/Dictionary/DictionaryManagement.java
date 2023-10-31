package Dictionary;

import java.util.Scanner;

class DictionaryManagement{
    public void insertFromCommandline(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int k = 0;
        Dictionary Di = new Dictionary();
        while (k<n){
            String target = sc.nextLine();
            String explain = sc.nextLine();
            Word s = new Word();
            s.setWord_target(target);
            s.setWord_explain(explain);
            Di.word.add(s);
            k++;
        }
    }
}
