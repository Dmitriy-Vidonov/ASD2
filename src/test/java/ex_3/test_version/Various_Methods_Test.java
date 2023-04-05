package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Various_Methods_Test {

    int depth = 3;
    @Test
    @DisplayName("1) Проверка расчета размера массива")
    void getSize() throws Exception {
        aBST treeTest = new aBST(depth);
        System.out.println("Рассчитаный размер массива - " + treeTest.getSize(depth));
    }

    @Test
    @DisplayName("2) Проверка заполнения массива")
    void arrayFilling() throws Exception {
        aBST treeTest = new aBST(depth);
        System.out.println("Реальный размер массива - " + treeTest.Tree.length);
        // Выведем для теста содержимое массива на экран
        treeTest.ShowArray();
        // Добавим корень
        treeTest.AddKey(100);
        treeTest.AddKey(50);
        treeTest.AddKey(150);
        treeTest.AddKey(25);
        treeTest.AddKey(200);
        treeTest.AddKey(75);
        //System.out.println("индекс 125 = " + treeTest.AddKey(125));
        System.out.println("-1 если не удалось " + treeTest.AddKey(250));
        // Выведем снова
        treeTest.ShowArray();

        System.out.println("Результат FindKey = " + treeTest.FindKeyIndex(200));
    }

    @Test
    @DisplayName("3) Проверка добавления и поиска")
    void testAddKeyAndFindKeyIndex() throws Exception{
        int depth = 3;
        aBST tree = new aBST(depth);

        // Проверка добавления ключей
        assertEquals(0, tree.AddKey(50));
        assertEquals(1, tree.AddKey(25));
        assertEquals(2, tree.AddKey(75));
        assertEquals(3, tree.AddKey(10));
        assertEquals(4, tree.AddKey(40));
        assertEquals(6, tree.AddKey(100)); // Дерево заполнено с одним null

        // Проверка поиска ключей
        assertEquals(0, tree.FindKeyIndex(50));
        assertEquals(1, tree.FindKeyIndex(25));
        assertEquals(2, tree.FindKeyIndex(75));
        assertEquals(-5, (int)tree.FindKeyIndex(60)); // Найден незаполненный слот
        assertNull(tree.FindKeyIndex(200)); // Ключ не найден

        // Проверка пустого дерева
        aBST emptyTree = new aBST(depth);
        assertNull(emptyTree.FindKeyIndex(50)); // Ключ не найден
    }
}