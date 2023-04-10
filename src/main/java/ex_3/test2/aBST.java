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
        // Частный случай, когда корня в дереве еще нет или он такой же как и добавляемый ключ
          if(Tree[0] == null) {
            Tree[0] = key;
            return 0; // вернули индекс корня
        }
        int index = 0;
        // Отсюда мы просто вызываем рекурсивный метод, передавая ему данные
        treeWalker(index);

        // добавляем ключ в массив
        // индекс добавленного/существующего ключа или -1 если не удалось
        return index;
    }

    // Рекурсивный обход дерева, pre-order, префиксный обход
    public void treeWalker(int index) { // от какого индекса обход и куда сложим элементы
        if(index >= this.Tree.length) return; // если мы достигли листа, у которого нет потомков
        // Работаем с текущей ячейкой массива
        list.add(this.Tree[index]);
        // обходим левое дерево
        treeWalker(2 * index + 1);
        // обходим правое дерево
        treeWalker(2 * index + 2);
    }
    // Вспомогательный метод по определению - мы правый или левый потомок?
    public boolean isLeftChild(int index) {
        return ((index - 1)/2 == (index/2)); // если родитель от index == родитель от index+1, то левый узел. иначе правый
    }

    // Тесты
    public static void main(String[] args) {
        aBST tree = new aBST(2);
        tree.masterAddKey(10, 0);
        tree.masterAddKey(8, 1);
        tree.masterAddKey(20, 2);
        tree.masterAddKey(7, 3);
        tree.masterAddKey(null, 4);
        tree.masterAddKey(15, 5);
        tree.masterAddKey(25, 6);

        tree.ShowArray();

        tree.ShowList(tree.AddKey(10));
    }
}

