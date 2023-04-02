package ex_2.final_version;

import java.io.*;
import java.util.*;

class BSTNode<T>
{
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T>
{
    public BSTNode<T> Node;
    public boolean NodeHasKey;
    public boolean ToLeft;
    public BSTFind() { Node = null; }
}
class BST<T>
{
    BSTNode<T> Root;
    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> wideList = new ArrayList<>();
        if(Root == null) return null;

        Queue<BSTNode> queue = new LinkedList<BSTNode>();
        queue.add(Root);

        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            wideList.add(node);

            if(node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if(node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }
        return wideList;
    }

    public ArrayList<BSTNode> DeepAllNodes(int status) {
        ArrayList<BSTNode> deepList = new ArrayList<>();
        if(Root == null) return null;
        if(status == 0) {
            DeepAllNodesHelperInOrder(Root, deepList);
        }
        if(status == 1) {
            DeepAllNodesHelperPostOrder(Root, deepList);
        }
        if(status == 2) {
            DeepAllNodesHelperPreOrder(Root, deepList);
        }
        return deepList;
    }

    private void DeepAllNodesHelperInOrder(BSTNode node, ArrayList<BSTNode> deepList) {
        if(node == null) return;
        DeepAllNodesHelperInOrder(node.LeftChild, deepList);
        deepList.add(node);
        DeepAllNodesHelperInOrder(node.RightChild, deepList);
    }

    private void DeepAllNodesHelperPostOrder(BSTNode node, ArrayList<BSTNode> deepList) {
        if(node == null) return;
        DeepAllNodesHelperPostOrder(node.LeftChild, deepList);
        DeepAllNodesHelperPostOrder(node.RightChild, deepList);
        deepList.add(node);
    }

    private void DeepAllNodesHelperPreOrder(BSTNode node, ArrayList<BSTNode> deepList) {
        if(node == null) return;
        deepList.add(node);
        DeepAllNodesHelperPreOrder(node.LeftChild, deepList);
        DeepAllNodesHelperPreOrder(node.RightChild, deepList);
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        if (Root == null) return null;

        BSTFind<T> bstFind = new BSTFind<>();
        bstFind.Node = Root;

        FindNodeByKeyHelper(key, bstFind.Node, bstFind);
        return bstFind;
    }
    private void FindNodeByKeyHelper(int key, BSTNode<T> nodeHelper, BSTFind<T> bstFindHelper) {
        if (key == nodeHelper.NodeKey) {
            bstFindHelper.NodeHasKey = true;
            return;
        }

        if(key > nodeHelper.NodeKey && nodeHelper.RightChild != null) {
            bstFindHelper.NodeHasKey = false;
            bstFindHelper.ToLeft = false;
            bstFindHelper.Node = nodeHelper.RightChild;
            FindNodeByKeyHelper(key, bstFindHelper.Node, bstFindHelper);
        }

        else if(key < nodeHelper.NodeKey && nodeHelper.LeftChild != null) {
            bstFindHelper.NodeHasKey = false;
            bstFindHelper.ToLeft = true;
            bstFindHelper.Node = nodeHelper.LeftChild;
            FindNodeByKeyHelper(key, bstFindHelper.Node, bstFindHelper);
        }

        else if (key < nodeHelper.NodeKey && nodeHelper.LeftChild == null) {
            bstFindHelper.NodeHasKey = false;
            bstFindHelper.ToLeft = true;
            return;
        }
        else if(key > nodeHelper.NodeKey && nodeHelper.RightChild == null) {
            bstFindHelper.NodeHasKey = false;
            bstFindHelper.ToLeft = false;
            return;
        }
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTNode<T> nodeToAdd = new BSTNode<>(key, val, null);

        if(this.Root == null) {
            this.Root = nodeToAdd;
            return true;
        }

        BSTFind<T> bstFind = this.FindNodeByKey(key);

        if(bstFind.NodeHasKey)
            return false;
        if(bstFind.ToLeft && !bstFind.NodeHasKey) {
            bstFind.Node.LeftChild = nodeToAdd;
            nodeToAdd.Parent = bstFind.Node;
            return true;
        }
        if(!bstFind.ToLeft && !bstFind.NodeHasKey) {
            bstFind.Node.RightChild = nodeToAdd;
            nodeToAdd.Parent = bstFind.Node;
            return true;
        }
        return false;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if(Root == null) return null;
        if(Root.LeftChild == null && Root.RightChild == null) return Root;

        if(!FindMax)
            return FinMinHelper(FromNode);
        return FinMaxHelper(FromNode);
    }
    private BSTNode<T> FinMinHelper(BSTNode<T> FromNode) {
        if(FromNode.RightChild == null && FromNode.LeftChild == null)
            return FromNode;
        return FinMinHelper(FromNode.LeftChild);
    }
    private BSTNode<T> FinMaxHelper(BSTNode<T> FromNode) {
        if(FromNode.RightChild == null && FromNode.LeftChild == null)
            return FromNode;
        return FinMaxHelper(FromNode.RightChild);
    }

    public boolean DeleteNodeByKey(int key)
    {
        if(this.Root == null) return false;

        if(this.Root.LeftChild == null && this.Root.RightChild == null && this.Root.NodeKey == key) {
            this.Root = null;
            return true;
        }

        if(this.Root.NodeKey == key && this.Root.LeftChild != null && this.Root.RightChild == null) {
            Root = Root.LeftChild;
            Root.Parent = null;
            return true;
        }

        else if(this.Root.NodeKey == key && this.Root.LeftChild == null && this.Root.RightChild != null) {
            Root = Root.RightChild;
            Root.Parent = null;
            return true;
        }
        BSTFind<T> bstFind = this.FindNodeByKey(key);

        if(bstFind.NodeHasKey && bstFind.Node.LeftChild == null
                && bstFind.Node.RightChild == null && bstFind.ToLeft == true) {
            bstFind.Node.Parent.LeftChild = null;
            bstFind.Node.Parent = null;
            return true;
        }

        if(bstFind.NodeHasKey && bstFind.Node.LeftChild == null
                && bstFind.Node.RightChild == null && bstFind.ToLeft == false) {
            bstFind.Node.Parent.RightChild = null;
            bstFind.Node.Parent = null;
            return true;
        }
        if(!bstFind.NodeHasKey) return false;

        if(bstFind.NodeHasKey && bstFind.Node.RightChild != null && bstFind.Node.LeftChild == null) {
            bstFind.Node.RightChild.Parent = bstFind.Node.Parent;
            bstFind.Node.Parent.RightChild = bstFind.Node.RightChild;
            bstFind.Node.Parent = null;
            bstFind.Node.RightChild = null;
            return true;
        }

        if(bstFind.NodeHasKey && bstFind.Node.RightChild == null && bstFind.Node.LeftChild != null) {
            bstFind.Node.LeftChild.Parent = bstFind.Node.Parent;
            bstFind.Node.Parent.LeftChild = bstFind.Node.LeftChild;
            bstFind.Node.Parent = null;
            bstFind.Node.LeftChild = null;
            return true;
        }

        if(bstFind.NodeHasKey && bstFind.Node.LeftChild != null
                && bstFind.Node.RightChild != null && bstFind.Node != Root) {
            BSTNode<T> successor = this.FinMinMax(bstFind.Node.RightChild, false);
            successor.Parent.LeftChild = null;
            successor.Parent = bstFind.Node.Parent;
            bstFind.Node.Parent.RightChild = successor;
            successor.RightChild = bstFind.Node.RightChild;
            bstFind.Node.RightChild.Parent = successor;
            successor.LeftChild = bstFind.Node.LeftChild;
            bstFind.Node.LeftChild.Parent = successor;
            bstFind.Node.Parent = null;
            bstFind.Node.LeftChild = null;
            bstFind.Node.RightChild = null;
            return true;
        }

        if(bstFind.NodeHasKey && bstFind.Node.LeftChild != null
                && bstFind.Node.RightChild != null && bstFind.Node == Root) {
            BSTNode<T> successor = this.FinMinMax(Root.RightChild, false);
            successor.Parent.LeftChild = null;
            successor.Parent = null;
            successor.RightChild = Root.RightChild;
            Root.RightChild.Parent = successor;
            successor.LeftChild = Root.LeftChild;
            Root.LeftChild.Parent = successor;
            Root = null;
            this.Root = successor;
            return true;
        }
        return false;
    }

    public int Count()
    {
        if(this.Root == null) return 0;
        if(this.Root.LeftChild == null && this.Root.RightChild == null) return 1;
        return CountHelper(this.Root);
    }

    private int CountHelper(BSTNode<T> node) {
        if(node == null) return 0;
        int count = 1;

        if(node.LeftChild != null || node.RightChild != null) {
            count += CountHelper(node.LeftChild);
            count += CountHelper(node.RightChild);
        }
        return count;
    }
}
