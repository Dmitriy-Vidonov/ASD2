package ex_3.test3;
import java.util.*;

public class aBST {
    public Integer Tree [];

    public aBST(int depth) {
        int tree_size = getSize(depth);
        Tree = new Integer[tree_size];
        for(int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public int getSize(int depth) {
        if (depth < 0) {
            return 0;
        }
        return (int)(Math.pow(2.0, (depth + 1)) - 1);
    }

    public Integer FindKeyIndex(int key) {
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        if (index >= Tree.length) return null;

        if (Tree[index] != null && Tree[index] == key) return index;

        if (Tree[index] == null) {
            if (index == 0) return 0;

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

    public int AddKey(int key) {
        int index = 0;
        int parentIndex = -1;
        while (index < Tree.length && Tree[index] != null) {
            if (Tree[index] == key) {
                return index;
            }
            parentIndex = index;
            if (key < Tree[index]) {
                index = 2 * index + 1;
            } else {
                index = 2 * index + 2;
            }
        }
        if (index < Tree.length) {
            Tree[index] = key;
            if (parentIndex != -1 && (key < Tree[parentIndex] || Tree[parentIndex] == null)) {
                return index;
            }
            int childIndex = index;
            while (childIndex > 0) {
                parentIndex = (childIndex - 1) / 2;
                if (key > Tree[parentIndex]) {
                    return index;
                }
                swap(Tree, childIndex, parentIndex);
                childIndex = parentIndex;
            }
            return index;
        }
        return -1;
    }

    private void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
