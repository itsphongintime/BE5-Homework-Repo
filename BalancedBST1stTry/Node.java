package BalancedBST1stTry;

public class Node {
    int value;
    Node left;
    Node right;
    boolean isRed;

    public Node(int value) {
        this.value = value;
        this.isRed = true;
    }
}