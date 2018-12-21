package ru.geekbrains.algoritm.hw_8;

import java.util.Arrays;

public class ChainingHashTable<Key,Value> {
    private int M;
    private int size=0;
    private Object[] st;
    public ChainingHashTable(int m) {
        M = m;
        st= new Object[M];
    }




    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    private int hash(Key key){
        return (key.hashCode()& 0x7fffffff)%M;
    }

    public boolean contains(Key key){
        return st[hash(key)]!=null;
    }

    public void put(Key key, Value value){
        if (key==null){
            throw new IllegalArgumentException("wrong key");
        }
        int i=hash(key);
        Node node= (Node)st[i];
        while (node!=null){
            if (key.equals(node.key)){
                node.value=value;
                return;
            } else {
                node=node.next;
            }
        }
        st[i]= new Node(key,value,(Node)st[i]);
        size++;
        if (size>M*0.75)
            {rehash(M);}
        return;
    }

    public Value get(Key key){
        if (key==null){
            throw new IllegalArgumentException("wrong key");
        }
        int i = hash(key);
        Node x=(Node)st[i];
        while (x!=null){
            if (x.key==key){
                size--;
                return x.value;
            } else{
                x=x.next;
            }
        }
        return null;
    }

    private int nextPrime(int number){
        int temp=number+1;
        while (!isPrime(temp)){
            temp++;
        }
        return temp;
    }

    private boolean isPrime(int number){
        if ((number%10==2)||(number%10==4)||(number%10==5)||(number%10==6)||(number%10==8)||(number%10==0)){
            return false;
        }
        int temp=(int)Math.sqrt((double)number);
        for (int i = 2; i <temp ; i++) {
            if (number%i==0){
                return false;
            }
        }
        return true;
    }

    private void rehash(int number){
        int newM=nextPrime(number*2);
        ChainingHashTable cht = new ChainingHashTable<>(newM);
        Node x;
        for (int i = 0; i <number-1 ; i++) {
            x=(Node)st[i];
            while (x!=null){
                cht.put(x.key,x.value);
                x=x.next;
            }
        }
        st=null;
        st= Arrays.copyOf(cht.st,newM);
        M=newM;
        size=cht.size;
    }


}
