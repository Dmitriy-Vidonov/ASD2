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
        if(depth == 0) return 1;
        depth++;
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
        int index = 0;
        return FindKeyHelper(index, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        // Если индекс больше длины массива, возвращаем null
        if (index > Tree.length-1) return null;

        // Если элемент массива не равен null и равен ключу, возвращаем индекс
        if (Tree[index] != null && Tree[index] == key) return index;

        // Если элемент массива равен null
        if (Tree[index] == null) {
            // Если индекс равен 0, возвращаем null
            if (index == 0) return null;
            // Если ключ меньше элемента массива с индексом (index - 1) / 2
            if (key < Tree[(index - 1) / 2]) {
                // Возвращаем индекс умноженный на -1
                return index * -1;
            } else {
                // Иначе возвращаем null
                return null;
            }
        }
        // Рекурсивно вызываем функцию для левого поддерева
        Integer left = FindKeyHelper(index * 2 + 1, key);
        // Если результат не равен null, возвращаем его
        if (left != null) return left;

        // Рекурсивно вызываем функцию для правого поддерева
        Integer right = FindKeyHelper(index * 2 + 2, key);
        // Если результат не равен null, возвращаем его
        if (right != null) return right;

        // Возвращаем null
        return null;
    }

    public int AddKey(int key) {
        // Частный случай если массив пуст
        if (Tree.length == 0) return -1;
        // добавляем ключ в массив
        if (Tree[0] == null) { // если дерево без корня, т.е. нулевой индекс пуст, то добавим корень
            Tree[0] = key;
            return 0;
        }
        int index = 0;
        while (index < Tree.length) {
            if (Tree[index] == null) {
                Tree[index] = key; // вот здесь обновляем index
                return index;
            } else if (key < Tree[index]) { // для левого потомка
                index = 2 * index + 1; // здесь обновляем index
            } else if (key > Tree[index]) { // для правого потомка
                index = 2 * index + 2; // здесь обновляем index
            } else return index;
        }
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}
/*
*1-я ошибка была - типа при глубине 3 должно быть 15 элементов в массиве
* но она проистекает из недостаточности условия задачи. Не было указано что при 3 например столько, при 1 - столько
*
* 2-я ошибка была - AddKey() не возвращает корректный индекс добавленного элемента
*
*
*/