package ru.geekbrains.algoritm.hw_8;

public class ChainingHashTable<Key,Value> {
    private int M=91;
    private int size=0;
    private Object[] st=new Object[M];


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

    

}
