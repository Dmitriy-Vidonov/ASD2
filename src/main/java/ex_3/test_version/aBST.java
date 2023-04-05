package ex_3.test_version;
import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        int tree_size = getSize(depth); // вычисляем размер массива в зависимости от заданной глубины
        Tree = new Integer[tree_size];
        for(int i = 0; i < tree_size; i++) Tree[i] = null; // заполнение дерева null-ами
    }

    // Посчитать размер массива
    public int getSize(int depth) {
        int sum = 0;
        if(depth == 0) return 0;
        while(depth >= 0) {
            sum += (int) Math.pow(2, depth - 1);
            depth--;
        }
        return sum;
    }

    // Вывод содержимого массива на экран
    public void ShowArray() {
        for(Integer num : this.Tree) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public Integer FindKeyIndex(int key) {
        // ищем в массиве индекс ключа
        return null;
    }

    public int AddKey(int key) {
        // Частный случай если массив пуст
        if(Tree.length == 0) return -1;
        // добавляем ключ в массив
        if(Tree[0] == null) { // если дерево без корня, т.е. нулевой индекс пуст, то добавим корень
            Tree[0] = key;
            return 0;
        }
        int index = 0;
        while (index < Tree.length) {
            if(Tree[index] == null) {
                Tree[index] = key; // вот здесь обновляем index
                return index;
            }
            else if(key < Tree[index]) { // для левого потомка
                index = 2 * index + 1; // здесь обновляем index
            }
            else if(key > Tree[index]) { // для правого потомка
                index = 2 * index + 2; // здесь обновляем index
            }
            else return index;
        }
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}
