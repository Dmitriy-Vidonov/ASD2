package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddKey_Test {

    @Test
    @DisplayName("1) Дерево из 1 элемента")
    void addKey_one_element_tree() throws Exception {
        aBST one_element_tree = new aBST(0);
        // Добавили первый и единственный элемент
        assertEquals(one_element_tree.AddKey(100), 0);
        // Попытались добавить второй элемент, ловим -1 (отказ)
        assertEquals(one_element_tree.AddKey(50), -1);
        // Попытались добавить дубль
        assertEquals(one_element_tree.AddKey(100), 0);
    }

    @Test
    @DisplayName("2) Дерево из 3-х элементов")
    void addKey_3_elements_tree() throws Exception {
        aBST threeTree = new aBST(1);
        // Добавили 3 узла и проверили корректность индексов при добавлении
        assertEquals(threeTree.AddKey(10), 0);
        assertEquals(threeTree.AddKey(5), 1);
        assertEquals(threeTree.AddKey(20), 2);
        // Создать новое дерево и добавить некорректного правого потомка
        aBST threeTree2 = new aBST(1);
        assertEquals(threeTree2.AddKey(10), 0);
        assertEquals(threeTree2.AddKey(5), 1);
        assertEquals(threeTree2.AddKey(1), -1);
        // Создать новое дерево и добавить некорректного левого потомка
        aBST threeTree3 = new aBST(1);
        assertEquals(threeTree3.AddKey(10), 0);
        assertEquals(threeTree3.AddKey(20), 2);
        assertEquals(threeTree3.AddKey(30), -1);
        // Добавим корректное значение
        assertEquals(threeTree3.AddKey(5), 1);
        // Добавим дубль в заполненное дерево
        assertEquals(threeTree3.AddKey(10), 0); // вместо корня
        assertEquals(threeTree3.AddKey(5), 1); // вместо левого потомка
        assertEquals(threeTree3.AddKey(20), 2); // вместо правого потомка
    }

    @Test
    @DisplayName("3) Дерево из 7 элементов, null у левого и правого потомков")
    void AddKey_seven_tree() throws Exception {
        aBST sevenTree = new aBST(2);
        // Заполняем элементами, оставляя 2 null ячейки
        assertEquals(sevenTree.AddKey(100), 0);
        assertEquals(sevenTree.AddKey(50), 1);
        assertEquals(sevenTree.AddKey(150), 2);
        assertEquals(sevenTree.AddKey(25), 3);
        assertEquals(sevenTree.AddKey(200), 6);
        // Добавить некорректный элемент в левого потомка с null
        assertEquals(sevenTree.AddKey(30), -1);
        // Добавить некорректный элемент в правого потомка с null
        assertEquals(sevenTree.AddKey(170), -1);
        // Добавить дубль корня
        assertEquals(sevenTree.AddKey(100), 0);
        // Добавить дубль корня поддерева
        assertEquals(sevenTree.AddKey(150), 2);
        // Добавить дубль листа
        assertEquals(sevenTree.AddKey(200), 6);
        // Заполнить один из null корректно и сразу попытаться добавить дубль этого же ключа
        assertEquals(sevenTree.AddKey(125), 5);
        assertEquals(sevenTree.AddKey(125), 5);
    }

    @Test
    @DisplayName("4) Дерево из 7-ми элементов, 2 null-а у непустого узла")
    void AddKey_seven_tree_2_nulls() throws Exception {
        aBST sevenTree2nulls = new aBST(2);
        assertEquals(sevenTree2nulls.AddKey(100), 0);
        assertEquals(sevenTree2nulls.AddKey(50), 1);
        assertEquals(sevenTree2nulls.AddKey(150), 2);
        assertEquals(sevenTree2nulls.AddKey(25), 3);
        // Добавить элемент, не подходящий в созданное дерево
        assertEquals(sevenTree2nulls.AddKey(10), -1);
        // Добавить подходящий элемент
        assertEquals(sevenTree2nulls.AddKey(200), 6);
        // Добавить дубль корня
        assertEquals(sevenTree2nulls.AddKey(100), 0);
        // Добавить дубль корня поддерева
        assertEquals(sevenTree2nulls.AddKey(150), 2);
    }

    @Test
    @DisplayName("5) Заполнение дерева из 7-ми элементов")
    void AddKey_completing_tree() throws Exception {
        aBST completeTree = new aBST(2);
        assertEquals(completeTree.AddKey(100), 0);
        assertEquals(completeTree.AddKey(150), 2);
        assertEquals(completeTree.AddKey(125), 5);
        assertEquals(completeTree.AddKey(175), 6);
        assertEquals(completeTree.AddKey(50), 1);
        assertEquals(completeTree.AddKey(75), 4);
        assertEquals(completeTree.AddKey(25), 3);
        completeTree.ShowArray();
    }

    @Test
    @DisplayName("6) Заполнена только левая ветка")
    void AddKey_only_left() throws Exception {
        aBST leftTree = new aBST(3);
        assertEquals(leftTree.AddKey(200), 0);
       // assertEquals(leftTree.AddKey(100), 1);
       // assertEquals(leftTree.AddKey(50), 3);
       // assertEquals(leftTree.AddKey(25), 7);
        // Правая ветка
        assertEquals(leftTree.AddKey(300), 2);
        assertEquals(leftTree.AddKey(400), 6);
        assertEquals(leftTree.AddKey(500), 14);
    }

    @Test
    @DisplayName("7) Пробуем выйти за пределы массива")
    void AddKey_out_of_bounds() throws Exception {
        aBST outTree = new aBST(2);
        assertEquals(outTree.AddKey(1), 0);
        assertEquals(outTree.AddKey(2), 2);
        assertEquals(outTree.AddKey(3), 6);
        assertEquals(outTree.AddKey(4), -1);
    }

    @Test
    @DisplayName("8) Проверка дубликатов ключей")
    public void testAddKeyDuplicate() throws Exception {
        aBST tree = new aBST(3);
        int index1 = tree.AddKey(10);
        int index2 = tree.AddKey(10);
        assertEquals(index1, index2);
    }

    @Test
    @DisplayName("9) Добавление ключа в пустое дерево")
    public void testAddKeyEmptyTree() throws Exception {
        aBST tree = new aBST(3);
        int index = tree.AddKey(10);
        assertEquals(0, index);
        assertEquals(Integer.valueOf(10), tree.Tree[index]);
    }

    @Test
    @DisplayName("10) Добавление ключей в порядке возрастания")
    public void testAddKeyAscend() throws Exception {
        aBST tree = new aBST(3);
        int index1 = tree.AddKey(10);
        int index2 = tree.AddKey(20);
        int index3 = tree.AddKey(30);
        assertEquals(0, index1);
        assertEquals(2, index2);
        assertEquals(6, index3);
        assertEquals(Integer.valueOf(10), tree.Tree[index1]);
        assertEquals(Integer.valueOf(20), tree.Tree[index2]);
        assertEquals(Integer.valueOf(30), tree.Tree[index3]);
    }

    @Test
    @DisplayName("11) Добавление ключей в порядке убывания")
    public void testAddKeyDesc() throws Exception {
        aBST tree = new aBST(3);
        int index1 = tree.AddKey(30);
        int index2 = tree.AddKey(20);
        int index3 = tree.AddKey(10);
        assertEquals(0, index1);
        assertEquals(1, index2);
        assertEquals(3, index3);
        assertEquals(Integer.valueOf(30), tree.Tree[index1]);
        assertEquals(Integer.valueOf(20), tree.Tree[index2]);
        assertEquals(Integer.valueOf(10), tree.Tree[index3]);
    }

    @Test
    @DisplayName("12) Добавление ключа в произвольном порядке")
    public void testAddKeyRandom() throws Exception {
        aBST tree = new aBST(3);
        int index1 = tree.AddKey(20);
        int index2 = tree.AddKey(10);
        int index3 = tree.AddKey(30);
        assertEquals(0, index1);
        assertEquals(1, index2);
        assertEquals(2, index3);
        assertEquals(Integer.valueOf(20), tree.Tree[index1]);
        assertEquals(Integer.valueOf(10), tree.Tree[index2]);
        assertEquals(Integer.valueOf(30), tree.Tree[index3]);
    }

    @Test
    @DisplayName("13) Попытка выйти за предел массива")
    public void testAddKeyOut() throws Exception {
        aBST tree = new aBST(2);
        tree.AddKey(10);
        tree.AddKey(20);
        tree.AddKey(30);
        int index = tree.AddKey(40);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("14) Работа с несколькими уровнями дерева")
    public void testAddKeyFewLevels() throws Exception {
        aBST tree = new aBST(4);
        int index1 = tree.AddKey(20);
        int index2 = tree.AddKey(10);
        int index3 = tree.AddKey(30);
        int index4 = tree.AddKey(5);
        int index5 = tree.AddKey(15);
        int index6 = tree.AddKey(25);
        int index7 = tree.AddKey(35);
        assertEquals(0, index1);
        assertEquals(1, index2);
        assertEquals(2, index3);
        assertEquals(3, index4);
        assertEquals(4, index5);
        assertEquals(5, index6);
        assertEquals(6, index7);
        assertEquals(Integer.valueOf(20), tree.Tree[index1]);
        assertEquals(Integer.valueOf(10), tree.Tree[index2]);
        assertEquals(Integer.valueOf(30), tree.Tree[index3]);
        assertEquals(Integer.valueOf(5), tree.Tree[index4]);
        assertEquals(Integer.valueOf(15), tree.Tree[index5]);
        assertEquals(Integer.valueOf(25), tree.Tree[index6]);
        assertEquals(Integer.valueOf(35), tree.Tree[index7]);
    }

    @Test
    @DisplayName("15) Работа с отрицательными числами")
    public void testAddKeyMinus() throws Exception {
        aBST tree = new aBST(2);
        assertEquals(tree.AddKey(-1), 0);
        assertEquals(tree.AddKey(1), 2);
        assertEquals(tree.AddKey(0), 5);
    }

    @Test
    @DisplayName("16) Проверка корректности индекса добавленного элемента")
    public void testAddKeyAdded() throws Exception {
        aBST tree = new aBST(3);
        int index = tree.AddKey(10);
        assertEquals(0, index); // корень дерева
        index = tree.AddKey(5);
        assertEquals(1, index); // левый потомок корня
        index = tree.AddKey(15);
        assertEquals(2, index); // правый потомок корня
    }

    @Test
    @DisplayName("17) Проверка корректности индекса существующего элемента")
    public void testAddKeyExist() throws Exception {
        aBST tree = new aBST(3);
        int index = tree.AddKey(10);
        assertEquals(0, index); // корень дерева
        index = tree.AddKey(5);
        assertEquals(1, index); // левый потомок корня
        index = tree.AddKey(15);
        assertEquals(2, index); // правый потомок корня

        // добавляем существующий ключ
        index = tree.AddKey(5);
        assertEquals(1, index); // левый потомок корня
    }

    @Test
    @DisplayName("18) Дерево на 5 уровней. По убыванию, затем по возрастанию")
    public void testAddKeyDescAsc() throws Exception {
        aBST tree = new aBST(5);
        // Добавляем ряд значений по убыванию
        assertEquals(0, tree.AddKey(0));
        assertEquals(1, tree.AddKey(-1));
        assertEquals(3, tree.AddKey(-2));
        assertEquals(7, tree.AddKey(-3));
        assertEquals(15, tree.AddKey(-4));
        assertEquals(31, tree.AddKey(-5));
        // Пробуем добавить значение за рамками дерева
        assertEquals(-1, tree.AddKey(-6));
        // Пробуем добавить дубль - получаем индекс существующего элемента
        assertEquals(31, tree.AddKey(-5));

        // Добавляем ряд значений по возрастанию
        assertEquals(2, tree.AddKey(1));
        assertEquals(6, tree.AddKey(2));
        assertEquals(14, tree.AddKey(3));
        assertEquals(30, tree.AddKey(4));
        assertEquals(62, tree.AddKey(5));
        // Пробуем добавить значение за рамками дерева
        assertEquals(-1, tree.AddKey(6));
        // Пробуем добавить дубль - получаем индекс существующего элемента
        assertEquals(62, tree.AddKey(5));
    }

    @Test
    @DisplayName("19) Обновление элементов в дереве")
    public void testAddKeyUpdate() throws Exception {
        // Проверка возможности обновления элементов
        aBST tree = new aBST(2);
        // Заполнили дерево значениями
        assertEquals(0, tree.AddKey(10));
        assertEquals(1, tree.AddKey(5));
        assertEquals(2, tree.AddKey(20));
        assertEquals(3, tree.AddKey(2));
        assertEquals(4, tree.AddKey(7));
        assertEquals(5, tree.AddKey(15));
        assertEquals(6, tree.AddKey(40));
    }
}