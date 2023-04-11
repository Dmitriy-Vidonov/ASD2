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
        // Отсюда мы просто вызываем рекурсивный метод, передавая ему данные
        // индекс добавленного/существующего ключа или -1 если не удалось
        return treeWalker(0, key); // начинаем с нулевого индекса
    }

    // Рекурсивный обход дерева, pre-order, префиксный обход
    public int treeWalker(int index, Integer key) { // от какого индекса обход и куда сложим элементы
        if(index >= this.Tree.length) return -1; // если мы достигли листа, у которого нет потомков и ничего не добавили
        // Частный случай, когда корня в дереве еще нет или он такой же как и добавляемый ключ
        if(this.Tree[0] == null) {
            this.Tree[0] = key;
            return 0; // вернули индекс корня
        }
        // Работаем с текущей ячейкой массива
        int parent = (index-1)/2;
        // ДЛЯ ЛЕВОЙ ВЕТКИ
        // если ячейка пуста и в нее можно добавить левого потомка
        if(this.Tree[index] == null && isLeftChild(index) && key < this.Tree[parent]
                && this.Tree[parent] <= this.Tree[0]) {
            this.Tree[index] = key;
            return index; // завершаем работу метода на текущем index
        }
        // если ячейка пуста и в нее можно добавить правого потомка
        if(this.Tree[index] == null && !isLeftChild(index) && key > this.Tree[parent]
                && this.Tree[parent] <= this.Tree[0]) {
            this.Tree[index] = key;
            return index;
        }
        // ДЛЯ ПРАВОЙ ВЕТКИ
        // если ячейка пуста и в нее можно добавить левого потомка
        if(this.Tree[index] == null && isLeftChild(index) && key < this.Tree[parent]
                && this.Tree[parent] >= this.Tree[0]) {
            this.Tree[index] = key;
            return index; // завершаем работу метода на текущем index
        }
        // если ячейка пуста и в нее можно добавить правого потомка
        if(this.Tree[index] == null && !isLeftChild(index) && key > this.Tree[parent]
                && this.Tree[parent] >= this.Tree[0]) {
            this.Tree[index] = key;
            return index;
        }
        // если попытались добавить дубль
        if (Objects.equals(Tree[index], key)) return index;
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
        tree.AddKey(10);
        tree.AddKey(8);
     //   tree.AddKey(20);
        tree.ShowArray();
    }
}

