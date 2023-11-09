package com.example.demo.dictionary.game2;

import java.util.ArrayList;
import java.util.List;

// union find
public class UF {
    private List<Data> list = new ArrayList<>();

    public UF(List<Data> list) {
        this.list = list;
    }

    public boolean similar(String a, String b) {
        /**
         * return 0 if a.length()!= b.length();
         */
        if (Math.abs(a.length() - b.length()) != 0) {
            return false;
        }
        int n = a.length();
        int diffCount = 0; //
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) return false;
        }
        return diffCount == 1;
    }

    public int find(Data data){

    }

    public void union(List<Data> list) {
        for (int j = 0; j < list.size(); j++) {
            for (int i = j - 1; i >= 0; i++) {
                String a = list.get(i).getWord().getWordTarget().toLowerCase();
                String b = list.get(j).getWord().getWordTarget().toLowerCase();
                if (similar(a, b)) {

                }
            }
        }
    }
}
