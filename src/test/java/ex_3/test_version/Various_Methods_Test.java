package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        treeTest.AddKey(50);
        treeTest.AddKey(25);
        treeTest.AddKey(75);
        treeTest.AddKey(10);
        treeTest.AddKey(40);
        treeTest.AddKey(100);
        treeTest.AddKey(5);
        treeTest.AddKey(15);
        treeTest.AddKey(30);
        treeTest.AddKey(45);
        treeTest.AddKey(90);
        treeTest.AddKey(120);
        System.out.println("индекс 125 = " + treeTest.AddKey(125)); // -1, т.к. нет возможности вставить ключ
        System.out.println("-1 если не удалось найти 250 " + treeTest.AddKey(250)); // -1 т.к. не нашли
        // Выведем содержимое массива
        treeTest.ShowArray();

        System.out.println("Результат FindKey 200 = " + treeTest.FindKeyIndex(200)); // null т.к. не найдено
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
          assertEquals(6, tree.AddKey(100));
          assertEquals(7, tree.AddKey(5));
          assertEquals(8, tree.AddKey(15));
          assertEquals(9, tree.AddKey(30));
          assertEquals(10, tree.AddKey(45));
          assertEquals(13, tree.AddKey(90));
          assertEquals(14, tree.AddKey(120));

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

    // Дополнительный набор тестов
    @Test
    @DisplayName("4) Пустое, незаполненное изначально дерево")
    void TestEmptyTree() throws Exception {
        int depth = 2; // 7 элементов
        aBST emptyTree = new aBST(depth);
        // Проверка размера дерева
        assertEquals(7, emptyTree.Tree.length);
        // Поиск несуществующего элемента
        assertNull(emptyTree.FindKeyIndex(100));
        // Добавили элементы
        assertEquals(emptyTree.AddKey(100), 0);
        assertEquals(emptyTree.AddKey(50), 1);
        assertEquals(emptyTree.AddKey(125), 2);
        emptyTree.ShowArray();
        // Совершили поиск элементов
        assertEquals(emptyTree.FindKeyIndex(100), 0);
        assertEquals(emptyTree.FindKeyIndex(50), 1);
        assertEquals(emptyTree.FindKeyIndex(125), 2);
        //Ищем несуществующий элемент
        assertEquals(emptyTree.FindKeyIndex(300), -6);
    }

    @Test
    @DisplayName("5) Дерево заполнено полностью, без null-ов")
    void FullTree() throws Exception {
        int depth = 2;
        aBST fullTree = new aBST(depth);
        // Заполняем дерево элементами
        assertEquals(fullTree.AddKey(100), 0);
        assertEquals(fullTree.AddKey(50), 1);
        assertEquals(fullTree.AddKey(125), 2);
        assertEquals(fullTree.AddKey(25), 3);
        assertEquals(fullTree.AddKey(75), 4);
        assertEquals(fullTree.AddKey(115), 5);
        assertEquals(fullTree.AddKey(200), 6);

        // Пытаемся добавить узел в заполненное дерево
        assertEquals(fullTree.AddKey(250), -1);
        // Найти существующий узел
        assertEquals(fullTree.FindKeyIndex(125), 2);
        // Найти несуществующий узел
        assertNull(fullTree.FindKeyIndex(300));
    }

    @Test
    @DisplayName("6) Дерево заполнено с null-ами")
    void TreeWithNulls() throws Exception {
        int depth = 2;
        aBST fullTreeNulls = new aBST(depth);
        aBST fullTreeNulls2 = new aBST(depth);
        // Заполняем дерево элементами, один null
        assertEquals(fullTreeNulls.AddKey(100), 0);
        assertEquals(fullTreeNulls.AddKey(50), 1);
        assertEquals(fullTreeNulls.AddKey(125), 2);
        assertEquals(fullTreeNulls.AddKey(25), 3);
        assertEquals(fullTreeNulls.AddKey(115), 5);
        assertEquals(fullTreeNulls.AddKey(200), 6);

        assertEquals(fullTreeNulls2.AddKey(100), 0);
        assertEquals(fullTreeNulls2.AddKey(50), 1);
        assertEquals(fullTreeNulls2.AddKey(125), 2);
        assertEquals(fullTreeNulls2.AddKey(25), 3);
        assertEquals(fullTreeNulls2.AddKey(115), 5);
        assertEquals(fullTreeNulls2.AddKey(200), 6);

        // Добавить значение, подходящее в null ячейку
        assertEquals(fullTreeNulls.AddKey(80), 4);
        // Сразу пытаемся найти добавленный ключ
        assertEquals(fullTreeNulls.FindKeyIndex(80), 4);

        // Добавить значение, не подходящее в null ячейку
        assertEquals(fullTreeNulls2.AddKey(45), -1);
        fullTreeNulls2.ShowArray();
        // Пытаемся найти недобавленный ключ
        assertNull(fullTreeNulls2.FindKeyIndex(45));
        // Пытаемся найти ключ, который подойдет в null ячейку
        assertEquals(fullTreeNulls2.FindKeyIndex(90), -4);
    }

    @Test
    @DisplayName("7) Проверка размера массива")
    void ArrayLengthCheck() throws Exception {
        int depth = 0; // задаем глубину
        aBST treeCheck = new aBST(depth);
        assertEquals(treeCheck.Tree.length, 1);

        // Пробуем другую длину
        depth = 1;
        aBST treeCheck1 = new aBST(depth);
        assertEquals(treeCheck1.Tree.length, 3);

        // Пробуем другую длину
        depth = 2;
        aBST treeCheck2 = new aBST(depth);
        assertEquals(treeCheck2.Tree.length, 7);
    }

    @Test
    @DisplayName("8) Работа с деревом из 1 элемента")
    void OneElementTree() throws Exception {
        aBST tree1 = new aBST(0);
        // Проверим размер массива
        assertEquals(tree1.Tree.length, 1);
        // Добавляем элемент
        assertEquals(tree1.AddKey(50), 0);
        // Поиск существующего элемента
        assertEquals(tree1.FindKeyIndex(50), 0);
        // Поиск несуществующего элемента
        assertNull(tree1.FindKeyIndex(100));
        // Попытаемся добавить еще один элемент
        assertEquals(tree1.AddKey(75), -1);
    }

    @Test
    @DisplayName("9) Проверка метода AddKey()")
    void AddKey_Test() throws Exception {
        // Задаем дерево
        aBST addKTree = new aBST(2);
        assertEquals(addKTree.AddKey(100), 0);
        assertEquals(addKTree.AddKey(70), 1);
        assertEquals(addKTree.AddKey(200), 2);
        assertEquals(addKTree.AddKey(50), 3);
        assertEquals(addKTree.AddKey(90), 4);
        assertEquals(addKTree.AddKey(300), 6);
        // Пытаемся добавить корректный узел
        assertEquals(addKTree.AddKey(150), 5);

        // Создаем новое дерево
        aBST addKTree2 = new aBST(2);
        assertEquals(addKTree2.AddKey(100), 0);
        assertEquals(addKTree2.AddKey(70), 1);
        assertEquals(addKTree2.AddKey(200), 2);
        assertEquals(addKTree2.AddKey(50), 3);
        assertEquals(addKTree2.AddKey(90), 4);
        assertEquals(addKTree2.AddKey(300), 6);
        // Пытаемся добавить некорректный узел
        assertEquals(addKTree2.AddKey(250), -1);

        // Создаем новое дерево
        aBST addKTree3 = new aBST(2);
        assertEquals(addKTree3.AddKey(100), 0);
        assertEquals(addKTree3.AddKey(50), 1);
        assertEquals(addKTree3.AddKey(90), 4);
        // Пробуем добавить дубль
        assertEquals(addKTree3.AddKey(90), 4);
    }

    @Test
    @DisplayName("10) Проверка с неполным деревом")
    void UnfullTree() throws Exception {
        aBST tree1 = new aBST(2);
        assertEquals(tree1.AddKey(100), 0);
        assertEquals(tree1.AddKey(50), 1);
        assertEquals(tree1.FindKeyIndex(125), -2);
    }
}