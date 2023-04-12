package ex_3.final_version;
import java.util.*;

class aBST
{
    public Integer Tree [];
    public aBST(int depth)
    {
        int tree_size = getSize(depth);
        Tree = new Integer[tree_size];
        for(int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public int getSize(int depth) {
        if (depth < 0) {
            return 0;
        }
        return (int) Math.pow(2.0, depth + 1) -1;
    }

    public Integer FindKeyIndex(int key) {
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        if (index >= Tree.length) return null;

        if (Tree[index] != null && Tree[index] == key) return index;

        if (Tree[index] == null) {
            if (index == 0) return null;

            int parentIndex = (index - 1) / 2;
            if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if (index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]))) {
                return index * -1;
            }
            else {
                return null;
            }
        }
        Integer left = FindKeyHelper(index * 2 + 1, key);
        if (left != null) return left;

        Integer right = FindKeyHelper(index * 2 + 2, key);
        if (right != null) return right;

        return null;
    }

    public int AddKey(int key)
    {
        int[] arr = {-1};
        return treeWalker(0, key, arr);
    }

    public int treeWalker(int index, Integer key, int[] arr) {
        if(index >= this.Tree.length) return arr[0];
        if (Tree[index] != null && Objects.equals(Tree[index], key))
        {
            arr[0] = index;
            return index;
        }
        int parent = (index-1)/2;

        if(Tree[index] == null) {
            if(Tree[index] == null) {
                if(index == 0) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                        && Tree[parent] <= Tree[0] && key < Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                        && Tree[parent] <= Tree[0] && key < Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                        && Tree[parent] >= Tree[0] && key > Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                        && Tree[parent] >= Tree[0] && key > Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
            }
        }
        treeWalker(index * 2 + 1, key, arr);
        treeWalker(index * 2 + 2, key, arr);
        return arr[0];
    }
    public boolean isLeftChild(int index) {
        return ((index - 1)/2 == (index/2));
    }
}
