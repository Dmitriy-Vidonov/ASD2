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

    public int AddKey(int key)
    {
        int[] arr = {-1};
        // индекс добавленного/существующего ключа или -1 если не удалось
        return treeWalker(0, key, arr); // начинаем с нулевого индекса
    }

    // Рекурсивный обход дерева, pre-order, префиксный обход
    public int treeWalker(int index, Integer key, int arr[]) { // от какого индекса обход и куда сложим элементы
        if(index >= this.Tree.length) return arr[0]; // дерево полностью заполнено
        if (Tree[index] != null && Objects.equals(Tree[index], key))
        {
            arr[0] = index;
            return index;  // Обнаружен дубликат ключа
        }
        // Работаем с текущей ячейкой массива
        int parent = (index-1)/2;

        if(Tree[index] == null) {
            if(Tree[index] == null) {
                // Частный случай, когда корня в дереве еще нет или он такой же как и добавляемый ключ
                // Работа с корневым узлом
                if(index == 0) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                // ДЛЯ ЛЕВОЙ ВЕТКИ
                // если ячейка пуста и в нее можно добавить левого потомка
                if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                        && Tree[parent] <= Tree[0] && key < Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                // если ячейка пуста и в нее можно добавить правого потомка
                if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                        && Tree[parent] <= Tree[0] && key < Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                // ДЛЯ ПРАВОЙ ВЕТКИ
                // если ячейка пуста и в нее можно добавить левого потомка
                if(Tree[parent] != null && isLeftChild(index) && key < Tree[parent]
                        && Tree[parent] >= Tree[0] && key > Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
                // если ячейка пуста и в нее можно добавить правого потомка
                if(Tree[parent] != null && !isLeftChild(index) && key > Tree[parent]
                        && Tree[parent] >= Tree[0] && key > Tree[0]) {
                    Tree[index] = key;
                    arr[0] = index;
                    return index;
                }
            }
        }
        treeWalker(index * 2 + 1, key, arr); // рекурсивный обход левого поддерева
        treeWalker(index * 2 + 2, key, arr); // рекурсивный обход правого поддерева
        return arr[0];
    }
    // Вспомогательный метод по определению - мы правый или левый потомок?
    public boolean isLeftChild(int index) {
        return ((index - 1)/2 == (index/2)); // если родитель от index == родитель от index+1, то левый узел. иначе правый
    }
}

