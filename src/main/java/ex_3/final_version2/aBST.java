package ex_3.final_version2;

import java.util.Objects;

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
        return (int) Math.pow(2.0, depth + 1.0) -1;
    }

    public Integer FindKeyIndex(int key) {
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        if (index >= Tree.length) {
            return null;
        }

        if (Tree[index] != null && Tree[index] == key) {
            return index;
        }

        if (Tree[index] == null) {
            if (index == 0) {
                return null;
            }

            int parentIndex = (index - 1) / 2;
            if (isLeftChild(index, parentIndex) && isKeyInRange(key, Tree[parentIndex], Tree[0])
                    && isParentSmaller(parentIndex)) {
                return -index;
            } else if (isRightChild(index, parentIndex) && isKeyInRange(key, Tree[parentIndex], Tree[0])
                    && isParentLarger(parentIndex)) {
                return -index;
            } else {
                return null;
            }
        }

        Integer left = FindKeyHelper(index * 2 + 1, key);
        if (left != null) {
            return left;
        }

        Integer right = FindKeyHelper(index * 2 + 2, key);
        if (right != null) {
            return right;
        }

        return null;
    }

    private boolean isLeftChild(int index, int parentIndex) {
        return index == 2 * parentIndex + 1;
    }
    private boolean isRightChild(int index, int parentIndex) {
        return index == 2 * parentIndex + 2;
    }
    private boolean isKeyInRange(int key, Integer parentValue, Integer rootValue) {
        return parentValue != null && rootValue != null && parentValue < rootValue
                ? key > parentValue && key < rootValue
                : key < parentValue && key > rootValue;
    }

    private boolean isParentSmaller(int parentIndex) {
        return Tree[parentIndex] < Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]);
    }

    private boolean isParentLarger(int parentIndex) {
        return Tree[parentIndex] > Tree[0] || Objects.equals(Tree[parentIndex], Tree[0]);
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

        boolean leftChild = leftChild(parent, index);
        boolean rightChild = rightChild(parent, index);

        if(Tree[index] == null) {
            if(index == 0) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(isInLeftSubTreeLeftChild(key, parent, leftChild)) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(isInLeftSubTreeRightChild(key, parent, rightChild)) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(isInRightSubTreeLefttChild(key, parent, leftChild)) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                if(isInRightSubTreeRighttChild(key, parent, rightChild)) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
            }
        treeWalker(index * 2 + 1, key, arr);
        treeWalker(index * 2 + 2, key, arr);
        return arr[0];
    }
    private boolean isInLeftSubTreeLeftChild (int key, int parent, boolean child) {
        return child && key < Tree[parent] && Tree[parent] <= Tree[0] && key < Tree[0];
    }
    private boolean isInLeftSubTreeRightChild (int key, int parent, boolean child) {
        return child && key > Tree[parent] && Tree[parent] <= Tree[0] && key < Tree[0];
    }
    private boolean isInRightSubTreeLefttChild (int key, int parent, boolean child) {
        return child && key < Tree[parent] && Tree[parent] >= Tree[0] && key > Tree[0];
    }
    private boolean isInRightSubTreeRighttChild (int key, int parent, boolean child) {
        return child && key > Tree[parent] && Tree[parent] >= Tree[0] && key > Tree[0];
    }
    private boolean leftChild(int parent, int index) {
        return Tree[parent] != null && isLeftChild(index);
    }
    private boolean rightChild(int parent, int index) {
        return Tree[parent] != null && !isLeftChild(index);
    }
    public boolean isLeftChild(int index) {
        return ((index - 1)/2 == (index/2));
    }
}
