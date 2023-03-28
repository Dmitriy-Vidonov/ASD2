package ex_1.final_version;

import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if(ParentNode != null)
            NewChild.Parent = ParentNode;
        else return;
        if(ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if(NodeToDelete == null) return;
        if(NodeToDelete == this.Root && this.Root.Children != null) {
            for(SimpleTreeNode<T> child : new ArrayList<>(NodeToDelete.Children)) DeleteNode(child);
            this.Root = null;
            return;
        }
        else if(NodeToDelete == this.Root && this.Root.Children == null) {
            this.Root = null;
            return;
        }

        if(NodeToDelete.Parent != null) {
            NodeToDelete.Parent.Children.remove(NodeToDelete);
            NodeToDelete.Parent = null;
        }

        if(NodeToDelete.Children != null) {
            for(SimpleTreeNode<T> child : new ArrayList<>(NodeToDelete.Children))
                DeleteNode(child);
        }
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        if(this.Root == null) return null;
        List<SimpleTreeNode<T>> nodeList = new ArrayList<>();
        GetAllNodesHelper(this.Root, nodeList);
        return nodeList;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {

        if(this.Root == null) return null;
        List<SimpleTreeNode<T>> nodesByVal = new ArrayList<>();
        FindNodesByValueHelper(val, this.Root, nodesByVal);
        return nodesByVal;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        if(OriginalNode == null || NewParent == null || OriginalNode == NewParent)
            return;
        if(OriginalNode.Parent == null || OriginalNode == this.Root)
            return;
        else {
            OriginalNode.Parent.Children.remove(OriginalNode);
            OriginalNode.Parent = NewParent;
        }
        if(OriginalNode.Parent.Children == null)
            OriginalNode.Parent.Children = new ArrayList<>();
        OriginalNode.Parent.Children.add(OriginalNode);
    }

    public int Count()
    {
        if(this.Root == null) return 0;
        return countHelper(this.Root);
    }

    public int LeafCount()
    {
        if(this.Root == null) return 0;
        return leafCountHelper(this.Root);
    }

    private void GetAllNodesHelper(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodeList){
        if(node == null) return;
        nodeList.add(node);
        if(node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children)
                GetAllNodesHelper(child, nodeList);
        }
    }

    private void FindNodesByValueHelper(T val, SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodesByVal) {
        if(node == null) return;
        if(node.NodeValue == val)
            nodesByVal.add(node);
        if (node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children)
                FindNodesByValueHelper(val, child, nodesByVal);
        }
    }

    private int countHelper(SimpleTreeNode<T> node) {
        if(node == null) return 0;
        int count = 1;

        if(node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children)
                count += countHelper(child);
        }
        return count;
    }

    private int leafCountHelper(SimpleTreeNode<T> node) {
        if(node == null) return 0;
        int count = 0;
        if(node.Children == null || node.Children.isEmpty())
            count++;
        if (node.Children != null) {
            for(SimpleTreeNode<T> child : node.Children)
                count += leafCountHelper(child);
        }
        return count;
    }
}