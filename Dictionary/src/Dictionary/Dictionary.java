package Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Dictionary{
    private List<Word> words =new ArrayList<>();
    public Dictionary(){
    }
    public void insertFromCommandline(){
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        for (int i=0;i<n;i++){
            String target = sc.next();
            String explain  = sc.next();
            Word s = new Word(target,explain);
            words.add(s);
        }

    }

}
