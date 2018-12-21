package ru.geekbrains.algoritm.hw_8;

public class Main {

    public static void main(String[] args) {
        ChainingHashTable chainingHashTable= new ChainingHashTable(3);
        chainingHashTable.put(100,"node 1");
        chainingHashTable.put(256,"node 2");
        chainingHashTable.put(132,"node 3");
        chainingHashTable.put(12,"node 4");
        chainingHashTable.put(183,"node 5");
    }
}
