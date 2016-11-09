package com.github.alexminnaar.bst;


public class Node {

    Node parent;
    Node leftChild;
    Node rightChild;
    Integer value;

    public Node(Node newParent,Node newLeftChild, Node newRightChild, Integer newValue) {
        parent=newParent;
        leftChild = newLeftChild;
        rightChild = newRightChild;
        value=newValue;
    }

}
