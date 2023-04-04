package ex_3.test_version;
import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        // правильно рассчитать размер массива для дерева глубины depth:
        int tree_size = 0;
        Tree = new Integer[ tree_size ];
        for(int i = 0; i < tree_size; i++) Tree[i] = null; // заполнение дерева null-ами
    }

    // Посчитать размер массива
    public int getSize(int depth) {
        if(depth == 1) return 1;
        if(depth == 2) return 3;
        return (int)Math.pow((depth), 2) + depth;
    }

    public Integer FindKeyIndex(int key) {
        // ищем в массиве индекс ключа
        return null;
    }

    public int AddKey(int key) {
        // добавляем ключ в массив
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}
