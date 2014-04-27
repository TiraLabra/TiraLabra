package com.mycompany.tiralabra_maven.datastructures;

class Node implements Valuable {
    private final int key;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        return key == ((Node) obj).key;
    }

    @Override
    public String toString() {
        return key + "";
    }
    
}
