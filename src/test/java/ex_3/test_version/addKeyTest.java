package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddKeyTest {

    @Test
    @DisplayName("1) Дерево из 1 элемента")
    void addKey_one_element_tree() {
        aBST one_element_tree = new aBST(0);
        // Добавили первый и единственный элемент
        assertEquals(0, one_element_tree.AddKey(100));
        // Попытались добавить второй элемент, ловим -1 (отказ)
        assertEquals(-1, one_element_tree.AddKey(50));
        // Попытались добавить дубль
        assertEquals(0, one_element_tree.AddKey(100));
    }

    @Test
    @DisplayName("2) Дерево из 3-х элементов")
    void addKey_3_elements_tree() {
        aBST threeTree = new aBST(1);
        // Добавили 3 узла и проверили корректность индексов при добавлении
        assertEquals(0, threeTree.AddKey(10));
        assertEquals(1, threeTree.AddKey(5));
        assertEquals(2, threeTree.AddKey(20));
        // Создать новое дерево и добавить некорректного правого потомка
        aBST threeTree2 = new aBST(1);
        assertEquals(0, threeTree2.AddKey(10));
        assertEquals(1, threeTree2.AddKey(5));
        assertEquals(-1, threeTree2.AddKey(1));
        // Создать новое дерево и добавить некорректного левого потомка
        aBST threeTree3 = new aBST(1);
        assertEquals(0, threeTree3.AddKey(10));
        assertEquals(2, threeTree3.AddKey(20));
        assertEquals(threeTree3.AddKey(30), -1);
        // Добавим корректное значение
        assertEquals(1, threeTree3.AddKey(5));
        // Добавим дубль в заполненное дерево
        assertEquals(0, threeTree3.AddKey(10)); // вместо корня
        assertEquals(1, threeTree3.AddKey(5)); // вместо левого потомка
        assertEquals(2, threeTree3.AddKey(20)); // вместо правого потомка
    }

    @Test
    @DisplayName("3) Дерево из 7 элементов, null у левого и правого потомков")
    void AddKey_seven_tree() {
        aBST sevenTree = new aBST(2);
        // Заполняем элементами, оставляя 2 null ячейки
        assertEquals(0, sevenTree.AddKey(100));
        assertEquals(1, sevenTree.AddKey(50));
        assertEquals(2, sevenTree.AddKey(150));
        assertEquals(3, sevenTree.AddKey(25));
        assertEquals(6, sevenTree.AddKey(200));
        // Добавить некорректный элемент в левого потомка с null
        assertEquals(sevenTree.AddKey(30), -1);
        // Добавить некорректный элемент в правого потомка с null
        assertEquals(sevenTree.AddKey(170), -1);
        // Добавить дубль корня
        assertEquals(0, sevenTree.AddKey(100));
        // Добавить дубль корня поддерева
        assertEquals(2, sevenTree.AddKey(150));
        // Добавить дубль листа
        assertEquals(6, sevenTree.AddKey(200));
        // Заполнить один из null корректно и сразу попытаться добавить дубль этого же ключа
        assertEquals(5, sevenTree.AddKey(125));
        assertEquals(5, sevenTree.AddKey(125));
    }

    @Test
    @DisplayName("4) Дерево из 7-ми элементов, 2 null-а у непустого узла")
    void AddKey_seven_tree_2_nulls() {
        aBST sevenTree2nulls = new aBST(2);
        assertEquals(0, sevenTree2nulls.AddKey(100));
        assertEquals(1, sevenTree2nulls.AddKey(50));
        assertEquals(2, sevenTree2nulls.AddKey(150));
        assertEquals(3, sevenTree2nulls.AddKey(25));
        // Добавить элемент, не подходящий в созданное дерево
        assertEquals(sevenTree2nulls.AddKey(10), -1);
        // Добавить подходящий элемент
        assertEquals(6, sevenTree2nulls.AddKey(200));
        // Добавить дубль корня
        assertEquals(0, sevenTree2nulls.AddKey(100));
        // Добавить дубль корня поддерева
        assertEquals(2, sevenTree2nulls.AddKey(150));
    }

    @Test
    @DisplayName("5) Заполнение дерева из 7-ми элементов")
    void AddKey_completing_tree() {
        aBST completeTree = new aBST(2);
        assertEquals(0, completeTree.AddKey(100));
        assertEquals(2, completeTree.AddKey(150));
        assertEquals(5, completeTree.AddKey(125));
        assertEquals(6, completeTree.AddKey(175));
        assertEquals(1, completeTree.AddKey(50));
        assertEquals(4, completeTree.AddKey(75));
        assertEquals(3, completeTree.AddKey(25));
        completeTree.ShowArray();
    }

    @Test
    @DisplayName("6) Заполнена только левая ветка")
    void AddKey_only_left() {
        aBST leftTree = new aBST(3);
        assertEquals(0, leftTree.AddKey(200));
        // Правая ветка
        assertEquals(2, leftTree.AddKey(300));
        assertEquals(6, leftTree.AddKey(400));
        assertEquals(14, leftTree.AddKey(500));
    }

    @Test
    @DisplayName("7) Пробуем выйти за пределы массива")
    void AddKey_out_of_bounds() {
        aBST outTree = new aBST(2);
        assertEquals(0, outTree.AddKey(1));
        assertEquals(2, outTree.AddKey(2));
        assertEquals(6, outTree.AddKey(3));
        assertEquals(outTree.AddKey(4), -1);
    }

    @Test
    @DisplayName("8) Проверка дубликатов ключей")
    void testAddKeyDuplicate() {
        aBST tree = new aBST(3);
        int index1 = tree.AddKey(10);
        int index2 = tree.AddKey(10);
        assertEquals(index1, index2);
    }

    @Test
    @DisplayName("9) Добавление ключа в пустое дерево")
    void testAddKeyEmptyTree() {
        aBST tree = new aBST(3);
        int index = tree.AddKey(10);
        assertEquals(0, index);
        assertEquals(Integer.valueOf(10), tree.Tree[index]);
    }

    @Test
    @DisplayName("10) Добавление ключей в порядке возрастания")
    void testAddKeyAscend() {
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
    void testAddKeyDesc() {
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
    void testAddKeyRandom() {
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
    void testAddKeyOut() {
        aBST tree = new aBST(2);
        tree.AddKey(10);
        tree.AddKey(20);
        tree.AddKey(30);
        int index = tree.AddKey(40);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("14) Работа с несколькими уровнями дерева")
    void testAddKeyFewLevels() {
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
    void testAddKeyMinus() {
        aBST tree = new aBST(2);
        assertEquals(0, tree.AddKey(-1));
        assertEquals(2, tree.AddKey(1));
        assertEquals(5, tree.AddKey(0));
    }

    @Test
    @DisplayName("16) Проверка корректности индекса добавленного элемента")
    void testAddKeyAdded() {
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
    void testAddKeyExist() {
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
    void testAddKeyDescAsc() {
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
    void testAddKeyUpdate() {
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

    @Test
    @DisplayName("20) Добавление ключа, который уже есть в дереве")
    void testAddKeyExistKey() {
        aBST tree = new aBST(2);
        tree.AddKey(5);
        tree.AddKey(3);
        int index = tree.AddKey(5);
        assertEquals(0, index);
    }

    @Test
    @DisplayName("21) Добавление ключа, который больше максимального, меньше минимального, после полного дерева")
    void testAddKeyMaxKey() {
        aBST tree = new aBST(1);
        tree.AddKey(5);
        tree.AddKey(3);
        tree.AddKey(10);
        // добавление больше максимального
        int index = tree.AddKey(20);
        assertEquals(-1, index);
        // добавление меньше минимального
        index = tree.AddKey(2);
        assertEquals(-1, index);
        // добавление когда дерево полное
        index = tree.AddKey(15);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("22) Добавление ключа в пустое дерево")
    void testAddKeyEmpty() {
        aBST tree = new aBST(2);
        int index = tree.AddKey(5);
        assertEquals(0, index);
    }

    @Test
    @DisplayName("23) Добавление ключа дублем в лист")
    void testAddKeyNonStandard() {
        aBST tree = new aBST(1);
        assertEquals(0, tree.AddKey(5));
        assertEquals(1, tree.AddKey(2));
        assertEquals(2, tree.AddKey(8));

        int index = tree.AddKey(4);
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("24) Проверка на дубли")
    void testAddKeyDouble() {
        aBST tree = new aBST(1);
        assertEquals(0, tree.AddKey(5));
        assertEquals(0, tree.AddKey(5));
        assertEquals(0, tree.AddKey(5));
        assertEquals(0, tree.AddKey(5));
    }

    @Test
    @DisplayName("25) Полное дерево. Добавление элемента с ключом меньше корня")
    void testAddKeylessThanRoot() {
        aBST tree = new aBST(1);
        // Создаем дерево
        assertEquals(0, tree.AddKey(5));
        assertEquals(1, tree.AddKey(2));
        assertEquals(2, tree.AddKey(8));
        // Добавляем элемент меньше дерева
        assertEquals(-1, tree.AddKey(0));
    }

    @Test
    @DisplayName("26) Полное дерево. Добавление элемента с ключом больше корня, но меньше единственного потомка")
    void testAddKeyBiggerThanRootLessThanKid() {
        aBST tree = new aBST(1);
        // Создаем дерево
        assertEquals(0, tree.AddKey(5));
        assertEquals(2, tree.AddKey(10));
        // Добавляем элемент меньше дерева
        assertEquals(-1, tree.AddKey(7));
    }

    @Test
    @DisplayName("27) Полное дерево. Добавление элемента с ключом больше корня и больше единственного потомка")
    void testAddKeyBiggerThanRootAndKid() {
        aBST tree = new aBST(1);
        // Создаем дерево
        assertEquals(0, tree.AddKey(5));
        assertEquals(2, tree.AddKey(10));
        // Добавляем элемент меньше дерева
        assertEquals(-1, tree.AddKey(15));
    }

    @Test
    @DisplayName("28) Полное дерево. Добавление элемента с ключом равным корню")
    void testAddKeySameAsRoot() {
        aBST tree = new aBST(1);
        // Создаем дерево
        assertEquals(0, tree.AddKey(5));
        assertEquals(2, tree.AddKey(10));
        assertEquals(1, tree.AddKey(2));

        assertEquals(0, tree.AddKey(5));
    }

    @Test
    @DisplayName("29) Полное дерево. Добавление элемента с ключом равным некорневому узлу")
    void testAddKeySameAsNode() {
        aBST tree = new aBST(1);
        // Создаем дерево
        assertEquals(0, tree.AddKey(5));
        assertEquals(2, tree.AddKey(10));
        assertEquals(1, tree.AddKey(2));

        assertEquals(2, tree.AddKey(10));
    }


    // containsKey - содержится ли ключ в дереве
    // если containsKey - ок, то AddKey() должен вернуть индекс существуюшего элемента
    @Test
    @DisplayName("30) Заполнение рандомными значениями")
    void TestRandAddKey() {
        aBST tree = new aBST(10);
        // Создаем Integer[] массив случайных значений
        Integer[] array = aBST.randArray(tree.Tree.length, -1000, 1000);
        boolean result = false;
        int key = -1;
        int index = -1;

        for(Integer num : array) {
            // это все ниже - в цикле
            key = num;
            index = tree.AddKey(key); // Добавили в дерево случайный ключ, сохранили в index
            if(index == -1 && tree.containsKey(key) == -1) { // если добавить не смогли и в дереве его нет
                result = true;
            }
            if(index != -1 && tree.containsKey(key) == index) { // если добавить смогли и элемент есть в дереве
                result = true;
            }
        }

        if(!result) {
            System.out.println("ошибка при key = " + key + " и index = " + index);
            fail(); // не прошли тест, если result не менялся

        }
    }
}