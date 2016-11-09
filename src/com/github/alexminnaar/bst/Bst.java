package com.github.alexminnaar.bst;


public class Bst {

    Node root;

    public Bst(Node newRoot) {
        root = newRoot;
    }

    /**
     * Perform binary search for a given value.
     *
     * @param value value to be searched for in the tree
     * @return boolean value indicating whether the value exists in the tree.
     */
    public boolean search(Integer value) {

        boolean foundVal = false;

        Node currentNode = root;

        while (true) {

            //if smaller than current node, go to left child, else go to right child. If child doesn't exist then node not found
            if (currentNode.value.equals(value)) {
                foundVal = true;
                break;
            } else if (currentNode.value < value) {
                if (currentNode.leftChild != null) {
                    currentNode = currentNode.leftChild;
                } else {
                    break;
                }
            } else if (currentNode.value > value) {
                if (currentNode.rightChild != null) {
                    currentNode = currentNode.rightChild;
                } else {
                    break;
                }
            }
        }

        return foundVal;
    }

    /**
     * Insert new node into bst by performing binary search and inserting the node at the appropriate leaf.
     *
     * @param newNode node to be added to the tree.
     */
    public void addNode(Node newNode) {

        Node currentNode = root;

        while (true) {

            //if node already is in tree, do nothing
            if (currentNode.value.equals(newNode.value)) {
                break;
            } else if (currentNode.value < newNode.value) {
                if (currentNode.leftChild != null) {
                    currentNode = currentNode.leftChild;
                }
                //insert new node where it should be wrt binary search
                else {
                    newNode.parent = currentNode;
                    currentNode.leftChild = newNode;
                    break;
                }
            } else if (currentNode.value > newNode.value) {
                if (currentNode.rightChild != null) {
                    currentNode = currentNode.rightChild;
                }
                //insert new node where it should be wrt binary search
                else {
                    newNode.parent = currentNode;
                    currentNode.rightChild = newNode;
                    break;
                }
            }

        }

    }
}
