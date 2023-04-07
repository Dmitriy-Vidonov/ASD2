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
        return FindKeyHelper(0, key);
    }

    private Integer FindKeyHelper(int index, int key) {
        // Если индекс больше длины массива, возвращаем null
        if (index > Tree.length-1) return null;

        // Если элемент массива не равен null и равен ключу, возвращаем индекс
        if (Tree[index] != null && Tree[index] == key) return index;

        // Если элемент массива равен null
        if (Tree[index] == null) {
            // Если индекс равен 0, возвращаем null
            if (index == 0) return null; // Типа если корень = null

            // Получаем индекс родителя
            int parentIndex = (index - 1) / 2;
            // узел - правый потомок, больше родителя и меньше корня, левое поддерево - родитель меньше корня или корень
            if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            // узел - левый потомок, меньше родителя и меньше корня, левое поддерево - родитель меньше корня или корень
            else if (index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key < Tree[0]
                    && (Tree[parentIndex] < Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            // узел - правый потомок, больше родителя и больше корня, правое поддерево - родитель больше корня или корень
            else if(index == 2 * parentIndex + 2 && key > Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            // узел - левый потомок, меньше родителя и больше корня, правое поддерево - родитель больше корня или корень
            else if(index == 2 * parentIndex + 1 && key < Tree[parentIndex] && key > Tree[0]
                    && (Tree[parentIndex] > Tree[0] || Tree[parentIndex] == Tree[0])) {
                return index * -1;
            }
            else {
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
            } else if (key == Tree[index]) {
                return -1;
            }
            else return index;
        }
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}
/*
*1-я ошибка была - типа при глубине 3 должно быть 15 элементов в массиве
* Исправлена
*
* 2-я ошибка была - AddKey() не возвращает корректный индекс добавленного элемента
* Скорее всего проверялся случай добавления дубля - данный момент скорректирован.
* Плюс исправлен метод FindKeyIndex, который при обходе дерева некорректно проверял возможность вставки ключа в узел
*
* 3-я ошибка - AddKey() не возвращает корректный индекс существующего элемента
* мы что, при добавлении дубля должны не -1, а индекс этого элемента отображать??
* видимо да, скорее всего в словах "индекс существующего элемента" подразумевается что он показывает индекс дубля как бы
* стало быть надо будет под это условие все тесты переделать
*/