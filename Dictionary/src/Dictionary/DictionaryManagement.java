package Dictionary;

import java.util.Scanner;
import java.util.*;

class DictionaryManagement{
    private Dictionary dictionary;
    private Scanner sc;
    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.sc = new Scanner(System.in);
    }
    public void insertFromCommandline(){
        Scanner sc =new Scanner(System.in);
        System.out.print(" Số phần tử: ");
        System.out.print("e:english, v: vietnamese");
        int n = sc.nextInt();
        for (int i=0;i<n;i++){
            System.out.print("e: ");
            String target = sc.nextLine();
            System.out.println("     ,v: ");
            String explain  = sc.nextLine();
            Word s = new Word(target,explain);
            this.dictionary.addword(s);
        }
    }
}
