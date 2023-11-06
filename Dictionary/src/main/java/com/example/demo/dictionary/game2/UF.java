package com.example.demo.dictionary.game2;

import java.util.ArrayList;

// thuật toán unionfind
public class UF {
    private ArrayList<Integer> id;
    private ArrayList<Integer> size;

    public UF(int n) {
        id = new ArrayList<>();
        size = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            id.add(i);
            size.add(1);
        }
    }

    public int find(int i) {
        while (i != id.get(i)) {
            id.set(i, id.get(id.get(i)));
            i = id.get(i);
        }
        return i;
    }

    public void union(int a, int b) {
        int roota = find(a);
        int rootb = find(b);
        if (roota != rootb) {
            if (size.get(a) >= size.get(b)) {
                id.set(rootb, roota);
                size.set(roota, size.get(roota) + size.get(rootb));
            } else {
                id.set(roota, rootb);
                size.set(rootb, size.get(rootb) + size.get(roota));
            }
        }
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public int length(int i) {
        return size.get(find(i));
    }
}
