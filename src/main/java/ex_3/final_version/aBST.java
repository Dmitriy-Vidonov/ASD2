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
        int sum = 0;
        if(depth == 0) return 1;
        depth++;
        while(depth >= 0) {
            sum += (int) Math.pow(2, depth - 1);
            depth--;
        }
        return sum;
    }

    public Integer FindKeyIndex(int key) {
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        if (index > Tree.length-1) return null;

        if (Tree[index] != null && Tree[index] == key) return index;

        if (Tree[index] == null) {
            if (index == 0) return null;

            int parentIndex = (index - 1) / 2;
            if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            else if (index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            else if(index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Tree[parentIndex] == Tree[0])) {
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
        if (Tree[0] == null) {
            Tree[0] = key;
            return 0;
        }
        int index = 0;
        while (index < Tree.length) {
            if (Tree[index] == null) {
                Tree[index] = key;
                return index;
            } else if (key < Tree[index]) {
                index = 2 * index + 1;
            } else if (key > Tree[index]) {
                index = 2 * index + 2;
            } else if (key == Tree[index]) {
                return index;
            }
            else return index;
        }
        return -1;
    }
}
