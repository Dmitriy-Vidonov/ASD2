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
        assertEquals(one_element_tree.AddKey(100), -1);
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
        assertEquals(threeTree3.AddKey(10), -1); // вместо корня
        assertEquals(threeTree3.AddKey(5), -1); // вместо левого потомка
        assertEquals(threeTree3.AddKey(20), -1); // вместо правого потомка
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
        assertEquals(sevenTree.AddKey(100), -1);
        // Добавить дубль корня поддерева
        assertEquals(sevenTree.AddKey(150), -1);
        // Добавить дубль листа
        assertEquals(sevenTree.AddKey(200), -1);
        // Заполнить один из null корректно и сразу попытаться добавить дубль этого же ключа
        assertEquals(sevenTree.AddKey(125), 5);
        assertEquals(sevenTree.AddKey(125), -1);
    }
}