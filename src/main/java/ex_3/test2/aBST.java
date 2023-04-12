package ex_3.test2;
import java.util.*;
public class aBST {
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = getSize(depth);;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    // "админский" метод прямого добавления ключей в массив
    public void masterAddKey(Integer key, int index) {
        this.Tree[index] = key;
    }

    // Посчитать размер массива
    public int getSize(int depth) {
        if (depth < 0) {
            return 0;
        }
        return (int)(Math.pow(2.0, (depth + 1)) - 1);
    }

    // Вывод содержимого массива на экран
    public void ShowArray() {
        for(Integer num : this.Tree) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void ShowList(ArrayList<Integer> list) {
        for(Integer num : list) System.out.print(num + " ");
        System.out.println();
    }

    public Integer FindKeyIndex(int key)
    {
        // ищем в массиве индекс ключа
        return null; // не найден
    }

    public int AddKey1(int key) {
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

    public int AddKey(int key)
    {
        // индекс добавленного/существующего ключа или -1 если не удалось
        return treeWalker(0, key); // начинаем с нулевого индекса
    }

    // Рекурсивный обход дерева, pre-order, префиксный обход
    public int treeWalker(int index, Integer key) { // от какого индекса обход и куда сложим элементы
        if(index >= this.Tree.length) return index; // если мы достигли листа, у которого нет потомков и ничего не добавили
        // Если дубль
        if (Tree[index] != null && Tree[index] == key)
        {
            return index;
        }
        // Работаем с текущей ячейкой массива
        int parent = (index-1)/2;

        if(Tree[index] == null) {
            // Частный случай, когда корня в дереве еще нет или он такой же как и добавляемый ключ
            // Работа с корневым узлом
            if(index == 0) {
                Tree[index] = key;
                return index;
            }
            // ДЛЯ ЛЕВОЙ ВЕТКИ
            // если ячейка пуста и в нее можно добавить левого потомка
            if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                    && Tree[parent] <= Tree[0] && key < Tree[0]) {
                Tree[index] = key;
                return index;
            }
            // если ячейка пуста и в нее можно добавить правого потомка
            if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                    && Tree[parent] <= Tree[0] && key < Tree[0]) {
                Tree[index] = key;
                return index;
            }
            // ДЛЯ ПРАВОЙ ВЕТКИ
            // если ячейка пуста и в нее можно добавить левого потомка
            if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                    && Tree[parent] >= Tree[0] && key > Tree[0]) {
                Tree[index] = key;
                return index; // завершаем работу метода на текущем index
            }
            // если ячейка пуста и в нее можно добавить правого потомка
            if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                    && Tree[parent] >= Tree[0] && key > Tree[0]) {
                Tree[index] = key;
                return index;
            }
        }
        // обходим левое дерево
        treeWalker(2 * index + 1, key);
        // обходим правое дерево
        treeWalker(2 * index + 2, key);
        // Конец работы метода
        return index;
    }
    // Вспомогательный метод по определению - мы правый или левый потомок?
    public boolean isLeftChild(int index) {
        return ((index - 1)/2 == (index/2)); // если родитель от index == родитель от index+1, то левый узел. иначе правый
    }

    // Тесты
    public static void main(String[] args) {
        aBST tree = new aBST(2);
        System.out.println(tree.AddKey(10)); // 0
        System.out.println(tree.AddKey(10)); // 0
        System.out.println(tree.AddKey(8)); // 1
        System.out.println(tree.AddKey(20)); // 2
        System.out.println(tree.AddKey(7)); // 3
        System.out.println(tree.AddKey(15)); // 5
        System.out.println(tree.AddKey(25)); // 6
        System.out.println(tree.AddKey(6)); // -1
        tree.ShowArray();
    }
}

